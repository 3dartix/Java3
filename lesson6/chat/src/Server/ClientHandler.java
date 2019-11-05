package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in; //входящий поток
    private DataOutputStream out; //исходящий поток
    private MainServer server;

    public String nick;
    public String id;

    public List<String> blackList;

    public ClientHandler(MainServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            blackList = new ArrayList<>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) { //начинается ли строка с символа

                                LogSave.addLog("client try auth: " + socket.getRemoteSocketAddress());

                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                System.out.println(server.isNickOnline(tokens[1]));
                                if (currentNick != null && !server.isNickOnline(currentNick)) {
                                    SendMessage("/authok" + " " + currentNick);

                                    LogSave.addLog("client auth ok: " + socket.getRemoteSocketAddress());

                                    nick = currentNick;

                                    id = AuthService.getIdByNick(nick);
                                    LogSave.addLog("client received a nick: " + socket.getRemoteSocketAddress());
                                    blackList = AuthService.getArrBlackList(id);

                                    server.subscribe(ClientHandler.this);

                                    // история
                                    ArrayList<String> history = AuthService.GetHistoryFromBD();
                                    history.forEach(el -> {
                                        server.SendMessageToUser(nick, el);
                                    });

                                    break;
                                } else {
                                    LogSave.addLog("entered an invalid login or password: " + socket.getRemoteSocketAddress());
                                    SendMessage("неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            LogSave.addLog("client sent a message: " + socket.getRemoteSocketAddress());
                            if (str.startsWith("/")){
                                if (str.equalsIgnoreCase("/end")) {
                                    SendMessage("/clientClose");

                                    LogSave.addLog("received a symbol /end: " + socket.getRemoteSocketAddress());

                                    break;
                                } else if (str.startsWith("/w")){
                                    String[] tokens = str.split(" ", 3); //сплитаем на 3 части

                                    LogSave.addLog("received a symbol /w: " + socket.getRemoteSocketAddress());

                                    server.SendMessageToUser(nick, tokens[1], tokens[2]);
                                }  else if (str.startsWith("/blacklist")){
                                    String[] tokens = str.split(" "); //сплитаем на 3 части
                                    out.writeUTF("Пользователь " + tokens[1] + " заблокирован!");

                                    LogSave.addLog("received a symbol /blacklist: user " + tokens[1] +" blocked" + socket.getRemoteSocketAddress());

                                    AddUserToBlackList(tokens[1]); //блокируем пользователя
                                }
                            } else {
                                //server.BroadcastMessage(str);
                                server.BroadcastMessage(ClientHandler.this, nick + ": " + str);

                                //отправляем сообщение в историю
                                AuthService.AddMessageToHistory(nick, str);

                                System.out.println(nick + ": " + str);

                            }
                        }

                    } catch (SQLException e) {
                        LogSave.addLog(e.getMessage() + socket.getRemoteSocketAddress(), Level.SEVERE);
                        e.printStackTrace();
                    } catch (IOException e) {
                        LogSave.addLog(e.getMessage() + socket.getRemoteSocketAddress(), Level.SEVERE);
                        e.printStackTrace();
                    } finally {
                        try {
                            LogSave.addLog("Closed DataOutputStream: " + socket.getRemoteSocketAddress());
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            LogSave.addLog("Closed DataInputStream: " + socket.getRemoteSocketAddress());
                            in.close();
                        } catch (IOException e) {
                            LogSave.addLog("closing DataInputStream fail " + e.getMessage()+ " " +  socket.getRemoteSocketAddress(), Level.SEVERE);
                            e.printStackTrace();
                        }
                        try {
                            LogSave.addLog("Closed DataInputStream: " + socket.getRemoteSocketAddress());
                            socket.close();
                        } catch (IOException e) {
                            LogSave.addLog("closing socket fail: " + e.getMessage()+ " " + socket.getRemoteSocketAddress(), Level.SEVERE);
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            LogSave.addLog("Other error: " + e.getMessage()+ " " + socket.getRemoteSocketAddress(), Level.SEVERE);
            e.printStackTrace();
        }
    }

    public void SendMessage (String msg){
        try{
            out.writeUTF(msg + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean cheeckBlackList(String name){
        System.out.println(blackList.size());
        return blackList.contains(name);
    }
    
    public void AddUserToBlackList(String user) throws SQLException {
        if(!cheeckBlackList(user)){
            blackList.add(user);
            AuthService.addUserToBlackList(id, user);
        }

    }

}
