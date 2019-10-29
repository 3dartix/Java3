package Lesson_3.DZ.task4;

import java.io.*;
import java.net.Socket;

public class client {
    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    Socket socket;
    DataInputStream in; //входящий поток
    DataOutputStream dos; //исходящий поток

    public client () throws IOException {
        socket = new Socket(IP_ADRESS, PORT);
        in = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());

        //сеарилиац.
//        obj obj = new obj(1, "Bob");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dz/task4/obj.my"));
//        oos.writeObject(obj);
//        oos.close();

        FileInputStream fis = new FileInputStream("dz/task4/obj.my");
        byte[] buffer = new byte[4096];
        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }
        fis.close();
        dos.close();

        in.close();
        socket.close();

    }

}
