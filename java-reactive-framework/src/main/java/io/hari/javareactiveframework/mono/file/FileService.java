package io.hari.javareactiveframework.mono.file;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    //read : execute only when someone subscribe (i.e. we are not doing any work): so use supplier
    //write + delete : 1. not return type, 2. so once operation is done just send notification

    public static final Path PATH = Paths.get("src/main/resources");

    public static Mono<String> monoRead(String fileName) {//dont do any operation, only do when someone subscribe
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public static Mono<Void> monoWrite(String fileName, String content) {//1. no return type so mono<void>, 2. get notify once file content is written so Runnable
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    public static Mono<Void> monoDelete(String fileName) {//1. no return type so mono<void>, 2. get notifiy once file content is written so Runnable
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    @SneakyThrows
    public static String readFile(String fileName) {
        return Files.readString(PATH.resolve(fileName));
    }

    @SneakyThrows
    public static void writeFile(String fileName, String content) {//1. no return type, just writing data to file , so blocking operation
        Files.writeString(PATH.resolve(fileName), content);
    }

    @SneakyThrows
    public static void deleteFile(String fileName) {//nothing returning , just doing operation
        Files.delete(PATH.resolve(fileName));
    }
}
