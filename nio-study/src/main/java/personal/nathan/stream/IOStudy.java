package personal.nathan.stream;

import java.io.*;

/**
 * Created by za-zhangwei002 on 19-4-9.
 */
public class IOStudy {

    public static void main(String[] args) {
        String filePath = "/home/nathan/develop/doc/english_story";
//        printWithIs(filePath);
        printWithOs(filePath);
    }

    public static void printWithIs(String filePath) {
        try (FileInputStream fileInputStream =
                     new FileInputStream(
                             new File(filePath))) {
//            byte[] readBytes = new byte[8];
            int hasRead;
            StringBuilder sb = new StringBuilder();
            while((hasRead = fileInputStream.read()) != -1) {
                sb.append((char) hasRead);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printWithOs(String filePath) {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(
                             new File(filePath))) {
            fileOutputStream.write(new byte[1024]);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
