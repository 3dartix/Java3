package Lesson_3.DZ.task4;

import com.sun.security.ntlm.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        new MainServer();
    }
}

class MainServer{
    ServerSocket server = null;
    Socket socket = null;

    private DataInputStream in; //входящий поток
    private DataOutputStream out; //исходящий поток

    public MainServer(){
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");



            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                break;
            }


            FileOutputStream fos = new FileOutputStream("dz/task4/receivedFile.my");
            while (true) {
                if(in.read() == -1) {
                    break;
                }
                System.out.println(in.read());
            }


            System.out.println("все");
            fos.close();

            ObjectInputStream ios = new ObjectInputStream( new FileInputStream("dz/task4/receivedFile.my"));
            try {
                obj ob = (obj) ios.readObject();
                System.out.println(ob.id + "\n" + ob.name);
                ios.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Файл получен, закрываем");
            out.writeUTF("/ok");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
