package Lesson_3.DZ;

import java.io.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class task2 {

    public void MergeFiles(String...paths) throws IOException {
        File file = new File("dz/task2/result");
        file.mkdirs();

        //создаем потоки
        ArrayList<InputStream> arrInputStream = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            arrInputStream.add(new FileInputStream(paths[i]));
        }
        //объединяем потоки в один
        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(arrInputStream));

        //создаем обратный поток и записываем объед. потоки в файл
        FileOutputStream out = new FileOutputStream("dz/task2/result/result.txt");
        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
        }
        out.close();
    }
}
