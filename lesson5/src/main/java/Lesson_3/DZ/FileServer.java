package Lesson_3.DZ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread {
    private ServerSocket ss;
    public FileServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                try {
                    saveFile(clientSock);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFile(Socket clientSock) throws IOException, ClassNotFoundException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream("stud2.ser");
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
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud2.ser"));
        Studens s2 = (Studens)ois.readObject();
        ois.close();
        s2.info();
        fos.close();
        dis.close();
    }
    public static void main(String[] args) {
        FileServer fs = new FileServer(1988);
        fs.start();
    }
}