package io.hari.java9to16feature.FilesReadWrite;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadWrite {
    @SneakyThrows
    public static void main(String[] args) {
        //read
        Path path = Paths.get("src/main/resources/sample.txt");
        String readString = Files.readString(path);
        System.out.println("readString = " + readString);

        String modifiedStr = readString.replace("line", "line -- -- ");
        //write
        Path path1 = Paths.get("src/main/resources/sample2.txt");//create new file if not present
        Files.writeString(path1, modifiedStr);
    }
}
