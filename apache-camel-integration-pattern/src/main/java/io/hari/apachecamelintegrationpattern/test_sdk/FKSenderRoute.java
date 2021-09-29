package io.hari.apachecamelintegrationpattern.test_sdk;

import com.google.common.primitives.Bytes;
import io.hari.apachecamelintegrationpattern.test_sdk.messages.GorMessage;
import lombok.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 * 1 docker install steps : 3min -> https://youtu.be/LtooWDUL1Js
 * 2 kafka install 2min using docker compose -> see docker-compose yml file for zookeeper and kafka ->  $docker-compose up
 * 3 : add dependency for kafka connector
 * 3.a add camel-kafka application properties for apache camel (where kafka is runing url+port): camel.component.kafka.brokers=localhost:9092
 */
@Component
@RequiredArgsConstructor
public class FKSenderRoute extends RouteBuilder {
        public static final String FILES_INPUT = "files/input";
    public static final String NOOP_TRUE = "&noop=true";
    //    public static final String FILES_INPUT = "/Users/yadav.hariom/hy_git_code/java-spring-boot-2021/apache-camel-integration-pattern/files/input";
//    public static final String FILES_INPUT = "/Users/yadav.hariom/hy_git_code/GOR_goreplay/gor-tester-master/gor_log";
//    @Value("${log_file_input_dir}")
//    public String FILES_INPUT;
    private final MyFKProcessor myFKProcessor;

    @Override
    public void configure() throws Exception {
        from("file:" + FILES_INPUT).routeId("reader-dir-route-1")
//                .log("initial line input from log : ${body}")
                .bean("getMyTransformBeanClass2", "getCurrentTimeMethodName2")
//                .log("log --to--> entity object : ${body}")
                .process(myFKProcessor)
                .to("log:read-from-log-1");

        //*read log
        //*convert to object Sting type
        //*create index request + create new index
        //*send to ES index
        //*run jar local and push data to ES
        //*make src log dir config
        //run docker image via k8s
        //add volume concept
    }
}

@Component
class GetMyTransformBeanClass2 {
    @SneakyThrows
    public Entity getCurrentTimeMethodName2(String input) {
        String emojiStringSplitter = new String("\uD83D\uDC35\uD83D\uDE48\uD83D\uDE49");
        System.out.println("emojiStringSplitter = " + emojiStringSplitter);

//        System.out.println("input ============= \n" + input);
//        System.out.println("input ============= \n");
        String lines[] = input.split("\\r?\\n");

        System.out.println("----------===");
        List<StringBuilder> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(lines).forEach(s -> {
            System.out.println("s = " + s);
            if (!s.equalsIgnoreCase(emojiStringSplitter)) {
                stringBuilder.append(s + "\n");
            } else {
                list.add(new StringBuilder(stringBuilder.toString()));
                stringBuilder.setLength(0);
            }
        });
//        System.out.println("inside for loop ----");
//        System.out.println("list size = " + list.size());
//        list.forEach(stringBuilder1 -> {
//            System.out.println(stringBuilder1.toString());
//        });
//        System.out.println("inside for loop end----");
//        System.out.println("----------===");

//        extracted();
//        extracted1();
//        extracted(context, emojiCharValue);
        return Entity.builder().name("dummy").stringBuilderList(list).build();
    }

    private void extracted1() {
        String regex = "[^\\p{L}\\p{N}\\p{P}\\p{Z}]";
        Pattern pattern = Pattern.compile(
                regex,
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher("context");
        String noEmojiString = matcher.replaceAll("9887700499");
        String[] noEmojiSplitString = noEmojiString.split("9887700499");
        Arrays.stream(noEmojiSplitString).forEach(s -> {
                    System.out.println("-------------------");
                    System.out.println("no emojit split string = \n" + s);
                    System.out.println("-------------------");
                });
    }

    private void extracted() {
        String context = "You can eat water too! ";
        char[] emojiCharValue = Character.toChars(0x1F349);
        String emojiString = new String(emojiCharValue);

        context += emojiString + " hariom yadav " + " sample " + emojiString + " sample 2";
        System.out.println("context = " + context);
        //split emoji
        String[] split = context.split(emojiString);
        System.out.println("split.length = " + split.length);
        Arrays.stream(split).forEach(s ->
                System.out.println("split = " + s)
        );
    }

    private void extracted(String context, char[] emojiCharValue) {
        //compare emoji
        int compareToIgnoreCase = new String(emojiCharValue).compareToIgnoreCase(new String(emojiCharValue));
        System.out.println("compareToIgnoreCase = " + compareToIgnoreCase);


        System.out.println("emojiCharValue length = " + emojiCharValue.length);
        for (char c : emojiCharValue) {
            System.out.println("c = " + c);
            System.out.println("\\u" + Integer.toHexString('÷' | 0x10000).substring(1));
            System.out.println("\\u" + Integer.toHexString(c | 0x10000).substring(1));
        }

        System.out.println("\uD83C\uDF49");
//        context.split("\uD83C\uDF49")

        String text = "la conférence, commencera à 10 heures ?";
        String regex = "[^\\p{L}\\p{N}\\p{P}\\p{Z}]";
        Pattern pattern = Pattern.compile(
                regex,
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll("");
        System.out.println("result = " + result);

        Matcher matcher1 = pattern.matcher(context);
        String replacement = "====____====____";
        String replaceAll = matcher1.replaceAll(replacement);
        System.out.println("replaceAll = " + replaceAll);

        String[] split1 = replaceAll.split(replacement);
        System.out.println(split1.length);
        Arrays.stream(split1).forEach(s -> {
            System.out.println("s = replacement : " + s);
        });
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Entity {
    String name;
    List<StringBuilder> stringBuilderList;
}
