package ait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompareAppl {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments or no arguments!");
            return;
        }
        System.out.println("File 1: " + args[0]);
        System.out.println("File 2: " + args[1]);

        try (FileInputStream fileInput1 = new FileInputStream(args[0]);
             FileInputStream fileInput2 = new FileInputStream(args[1])) {

            boolean equal = compareFiles(fileInput1, fileInput2);

            if (equal) {
                System.out.println("The files are the same!");
            } else {
                System.out.println("The files are different!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean compareFiles(FileInputStream file1, FileInputStream file2) throws IOException {
        int byteFromFile1 = file1.read();
        int byteFromFile2 = file2.read();

        while (byteFromFile1 != -1 && byteFromFile2 != -1) {
            if (byteFromFile1 != byteFromFile2) {
                return false;
            }
            byteFromFile1 = file1.read();
            byteFromFile2 = file2.read();
        }
        return byteFromFile1 == -1 && byteFromFile2 == -1;
    }
}
