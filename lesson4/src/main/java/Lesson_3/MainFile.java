package Lesson_3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class MainFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path path1 = Paths.get("123/4");

        System.out.println(path1);


//        File file = new File("123/4");
//        file.mkdirs();

//        File file = new File("123");
//
//        String[] str = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("t");
//            }
//        });
//
//        for (String o: str) {
//            System.out.println(o);
//        }

//        File file1 = new File("123/test.txt");
//        File file2 = new File("123/test1.txt");
//
//        boolean res = file1.renameTo(file2);
//        System.out.println(res);

//        File file3 = new File("123/test123.txt");
//
//        try {
//            file3.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //file1.ex

//        long t = System.currentTimeMillis();
//
//        try (FileInputStream in = new FileInputStream("123/test1.txt")) {
//            int x;
//            byte[] arr = new byte[512];
//
//            while ((x = in.read(arr)) != -1) {
//                System.out.println(new String(arr,0,x, "UTF-8"));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(System.currentTimeMillis() - t);

//        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("123/test1.txt"), "UTF-8")) {
//
//            int x;
//
//            while ((x = isr.read()) != -1) {
//                System.out.print((char)x);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String fileName = "123/test1.txt";
//
//        BufferedReader br = null;
//        FileReader fr = null;
//
//        try {
//            fr = new FileReader(fileName);
//            br = new BufferedReader(fr);
//
//            String currentLine;
//
//            while ((currentLine = br.readLine()) != null) {
//                System.out.println(currentLine);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//        PipedInputStream in = null;
//        PipedOutputStream out = null;
//
//        in = new PipedInputStream();
//        out = new PipedOutputStream();
//        try {
//            out.connect(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < 100; i++) {
//            out.write(i);
//        }
//
//        int x;
//
//        while ((x = in.read()) != -1) {
//            System.out.print(x);
//        }
//
//        in.close();
//        out.close();


//        ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("123/test1.txt"));
//        ali.add(new FileInputStream("123/test2.txt"));
//        ali.add(new FileInputStream("123/test3.txt"));
//
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//
//        int x;
//        while ((x = in.read()) != -1) {
//            System.out.print((char)x);
//        }
//
//        in.close();

//        try (RandomAccessFile raf = new RandomAccessFile(("123/test1.txt"),"r")) {
//            raf.seek(1);
//            int x;
//            while ((x = raf.read()) != -1) {
//                System.out.println((char)x);
//            }
//            System.out.println((char) raf.read());
//        } catch (IOException e) {
//
//        }

        // сеарилизация
//        Student s = new Student(1, "Bob1");
//        Book book = new Book("Jungle Book");
//        s.book = book;
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser"));
//        oos.writeObject(s);
//        oos.close();

        // десеарилизация
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud.ser"));
//        Student s2 = (Student) ois.readObject();
//        ois.close();
//        s2.info();


    }
}
