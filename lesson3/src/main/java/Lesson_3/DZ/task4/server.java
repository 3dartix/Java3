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

    private DataInputStream dis; //входящий поток

    public MainServer(){
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");


            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                dis = new DataInputStream(socket.getInputStream());
                break;
            }


            FileOutputStream fos = new FileOutputStream("dz/task4/receivedFile.my");
            byte[] buffer = new byte[4096];
            int filesize = 15123; //
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;

            while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                System.out.println("read " + totalRead + " bytes.");
                fos.write(buffer, 0, read);
            }

            fos.close();
            System.out.println("все");


            ObjectInputStream ios = new ObjectInputStream( new FileInputStream("dz/task4/receivedFile.my"));
            try {
                obj ob = (obj) ios.readObject();
                System.out.println(ob.id + "\n" + ob.name);
                ios.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Файл получен, закрываем");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
