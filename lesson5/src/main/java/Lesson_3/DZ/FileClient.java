package Lesson_3.DZ;

import java.io.*;
import java.net.Socket;
public class FileClient {
    private Socket s;
    public FileClient(String host, int port, String file) {
        try {
            s = new Socket(host, port);
            sendFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendFile(String file) throws IOException {
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }
        fis.close();
        dos.close();
    }
    public static void main(String[] args) throws IOException {
        Studens s = new Studens(1, "Bob");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser"));
        oos.writeObject(s);
        oos.close();
        String filePath = "stud.ser";
        new FileClient("localhost", 1988, filePath);
    }
}