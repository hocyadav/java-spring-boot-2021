package io.hari.apachecamelintegrationpattern;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * https://www.baeldung.com/java-filechannel
 */
public class SampleTest {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("SampleTest.main");

        String file = "files/input/second_file.csv";
        RandomAccessFile reader = new RandomAccessFile(file, "r");
        FileChannel channel = reader.getChannel();

//        FileInputStream fin= new FileInputStream(file);
//        FileChannel channel = fin.getChannel();


//        ByteBuffer buff = ByteBuffer.allocate(50);
//        int noOfBytesRead = channel.read(buff);
//        String fileContent = new String(buff.array(), StandardCharsets.UTF_8);


        ByteBuffer buff = ByteBuffer.allocate(50);
        int noOfBytesRead = channel.read(buff, 0);
        System.out.println("channel position = " + channel.position());
        String fileContent = new String(buff.array(), StandardCharsets.UTF_8);
        System.out.println("channel position = " + channel.position());

        System.out.println("fileContent = \n" + fileContent);

        System.out.println("----------------------------");
        extracted();


    }

    private static void extracted() throws IOException {
        String file = "files/input/second_file.csv";
        try (RandomAccessFile reader = new RandomAccessFile(file, "r");
             FileChannel channel = reader.getChannel();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            System.out.println("channel position- = " + channel.position());
            System.out.println("bufferSize = " + bufferSize);
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);

            while (channel.read(buff) > 0) {
                System.out.println("channel position- = " + channel.position());
                out.write(buff.array(), 0, buff.position());
                buff.clear();
            }
            String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
            System.out.println("fileContent = \n" + fileContent);
        }
    }
}
