package io.hari.annotationlombokothers.lombok.cleanup;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * https://projectlombok.org/features/Cleanup
 * https://stackoverflow.com/questions/45442193/what-is-the-proper-way-to-use-lombok-cleanup-here
 * https://nullbeans.com/how-to-use-the-lombok-cleanup-annotation/
 *
 * m1: use CleanUp (use this)
 * m2: use try with resource
 */
public class MyCleanUp {
    @SneakyThrows
    public static void main(String[] args) {
        @Cleanup InputStream in = new FileInputStream("/Users/yadav.hariom/hy_git_code/java-spring-boot-2021/annotation-lombok-others/src/main/resources/application.properties");
        @Cleanup OutputStream out = new FileOutputStream("");

        byte[] bytes = new byte[10000];
        while (true) {
            int read = in.read(bytes);
            if (read == -1) break;
            else out.write(bytes, 0, read);
        }
    }
}
