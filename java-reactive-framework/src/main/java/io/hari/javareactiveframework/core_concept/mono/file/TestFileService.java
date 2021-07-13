package io.hari.javareactiveframework.core_concept.mono.file;

import io.hari.javareactiveframework.core_concept.mono.Util;

public class TestFileService {
    public static void main(String[] args) {
        //todo : read file
        FileService.monoRead("file1.txt")
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());

        //todo : error channel , no file
        FileService.monoRead("file123.txt")
                .subscribe(Util.getOnNext(), Util.getOnError(), () -> System.out.println("COMPLETE : file read successfully"));

        // TODO:write file : it will not create new file since no one subscribe
        FileService.monoWrite("file2.txt", "this is file 2");

        // TODO:write file : create file since subscribe is present
        FileService.monoWrite("file2.txt", "this is file 2")
                .subscribe(Util.getOnNext(), Util.getOnError(), () -> System.out.println("COMPLETE : file created/write successfully"));

        //todo : delete file, it will not
        FileService.monoDelete("file1.txt");

        //todo : delete file, it will delete since subscribe is present
        FileService.monoDelete("file1.txt")
                .subscribe(Util.getOnNext(), Util.getOnError(), () -> System.out.println("COMPLETE : file deleted"));
    }
}
