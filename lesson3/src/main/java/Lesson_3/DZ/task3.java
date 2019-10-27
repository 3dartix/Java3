package Lesson_3.DZ;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class task3 {
    String path;
    RandomAccessFile raf;
    int symbolCount = 1800;
    int curNumberPage = 0;

    public task3 (String path) throws IOException {
        this.path = path;
        OpenFile();
        ShowPage();
    }

    void OpenFile() throws IOException {
        try {
            raf = new RandomAccessFile(path, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetPage(int number) throws IOException {
        curNumberPage = number - 1;
        ShowPage();
    }

    public void Close() throws IOException {
        raf.close();
    }

    void ShowPage() throws IOException {
        raf.seek(curNumberPage * symbolCount);

        for (int i = 0; i < symbolCount; i++) {
            if(raf.read() == -1){
                System.out.println("\n\nКнига закончена.");
                break;
            }
            System.out.print((char)raf.read());
        }
    }
}
