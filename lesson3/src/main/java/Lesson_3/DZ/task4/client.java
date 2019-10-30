package Lesson_3.DZ.task4;

import java.io.*;
import java.net.Socket;

public class client {
    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    Socket socket;
    DataInputStream in; //входящий поток
    DataOutputStream out; //исходящий поток

    public client () throws IOException {
        socket = new Socket(IP_ADRESS, PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        //сеарилиац.
//        obj obj = new obj(1, "Bob");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dz/task4/obj.my"));
//        oos.writeObject(obj);
//        oos.close();

        FileInputStream fis = new FileInputStream("dz/task4/obj.my");
        while (fis.read() != -1){
            out.write(fis.read());
            System.out.println(fis.read());
        }
        //out.write(-1);
        fis.close();

        while (true){
            System.out.println("Ждем...");
            String str = in.readUTF();
            if(str.startsWith("/ok")) {
                break;
            }
        }

        in.close();
        out.close();
        socket.close();

    }

}
