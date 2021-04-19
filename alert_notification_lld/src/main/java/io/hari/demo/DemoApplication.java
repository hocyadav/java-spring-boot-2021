package io.hari.demo;

import io.hari.demo.config.AppConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    AppConfig config;

    LocalDateTime localDateTime;
    Long timeout;
	private long waitTimeAfterNotify = 10L;//read from config

    @Override
    public void run(String... args) throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("dateTime = " + dateTime);//set this time in log file

        Queue<LogEntity> warningQueue = new LinkedList<>();
        Queue<LogEntity> infoQueue = new LinkedList<>();
        Queue<LogEntity> blockerQueue = new LinkedList<>();

        //m1
//		final List<String> strings = Files.readAllLines(Paths.get("file.log"));
//		System.out.println("strings = " + strings);

        //m2
        final Stream<String> lines = Files.lines(Paths.get("file.log"));
        lines.forEach(line -> {
            System.out.println("single log = " + line);
            final String[] s = line.split(" ");

            final LocalDateTime logTime = LocalDateTime.parse(s[0]);
            final String type = s[1];
            final String logData = s[2];

            final LogEntity data = LogEntity.builder().dateTime(logTime).type(type).data(logData).build();
            if (type.equals("warning")) {
                saveDataToQueue(warningQueue, data);
            } else if (type.equals("block")) {
                saveDataToQueue(blockerQueue, data);
            } else if (type.equals("info")) {
                saveDataToQueue(infoQueue, data);
            }
        });
        System.out.println("infoQueue = " + infoQueue);
        System.out.println("warningQueue = " + warningQueue);
        System.out.println("blockerQueue = " + blockerQueue);
    }

    @SneakyThrows
    private void saveDataToQueue(Queue<LogEntity> queue, LogEntity current) {
        skipLogic(queue, current);
        System.out.println("current.type = " + current.type);
        final LogEntity peek = queue.peek();
        if (peek != null) {
			System.out.println("peek = " + peek);
            System.out.println("current = " + current);
            final Duration durationBetween = Duration.between(peek.getDateTime(), current.getDateTime());
            System.out.println("peek current - durationBetween sec = " + durationBetween.getSeconds());

			if (durationBetween.getSeconds() >= waitTimeAfterNotify) {
				notifyUser(current);
			}
        }
        queue.add(current);
    }

	private void notifyUser(LogEntity current) throws InterruptedException {
		System.out.println("notification " + current);
		localDateTime = localDateTime.now();
		timeout = 100L;
		Thread.sleep(1000);
	}

	private void skipLogic(Queue<LogEntity> queue, LogEntity current) {
        if (localDateTime != null && timeout != null) {
            final long seconds = Duration.between(localDateTime, LocalDateTime.now()).getSeconds();
            System.out.println("seconds = " + seconds);
            System.out.println("timeout = " + timeout);
            System.out.println("localDateTime = " + localDateTime);
            if (seconds <= timeout) {
//				queue.remove();
                queue.add(current);
            }
        }
    }
}
