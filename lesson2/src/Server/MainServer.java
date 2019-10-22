package Server;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;

    public MainServer() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        Scanner console = new Scanner(System.in);

        //создаем таблицу с историей

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String str = console.nextLine();
                    BroadcastMessage("Server: "+ str);
                }
            }
        }).start();

        try {
            AuthService.Connect();

//            String res = AuthService.getNickByLoginAndPass("login1", "pass2");
//            System.out.println(res);

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AuthService.Disconnect();
    }

    public void BroadcastMessage(String msg){
        for (ClientHandler el: clients) {
            el.SendMessage(msg);
        }
    }

    public void BroadcastMessage(ClientHandler client, String msg){
        for (ClientHandler el: clients) {
            if (!el.cheeckBlackList(client.nick)) {
                el.SendMessage(msg);
            }
        }
    }

    public void BroadcastClientsList() {
        StringBuilder sb = new StringBuilder(); //позволяет изменять строку, а не создает новую(для многоп. прилож)
        sb.append("/clientList ");
        for (ClientHandler o : clients) {
            sb.append(o.nick + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.SendMessage(out);
        }
    }

    public void SendMessageToUser(String to, String msg){
        for (ClientHandler client: clients) {
            if(to.equalsIgnoreCase(client.nick)){
                client.SendMessage(msg);
            }
        }
    }

    public void SendMessageToUser(String from, String to, String msg){
        for (ClientHandler client: clients) {
            if(to.equalsIgnoreCase(client.nick)){
                client.SendMessage("From " + from + ": " + msg);
            }
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        BroadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        BroadcastClientsList();
    }

    public boolean isNickOnline (String nick) {
        boolean result = false;
        for (ClientHandler client: clients) {
            System.out.println(client.nick + "   " + nick);
            if (nick.equalsIgnoreCase(client.nick)){
                result = true;
                break;
            }
        }
        return result;
    }

}