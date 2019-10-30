package Lesson_3.DZ.task4;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        try {
            new client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
