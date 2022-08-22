package io.hari.javareactiveframework;

import org.apache.kafka.common.protocol.types.Field;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class MyTodo {
    /**
     *
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {

        jobScheduler("delta event");//see video 1hr + impl 40min
        jobSchedulerHelper();//now data present in kafka // no impl req , only es impl 40min search, 1hr impl es n test
        processKafkaData_Kafka_to_Flux();//see video / git and copy code - 1hr

        //total 5hr


    }

    private static void processKafkaData_Kafka_to_Flux() {
        Flux<String> kafkaToFlux = Flux.fromIterable(List.of("job 1 list1", "j1 list2", "j2 list1"));

        kafkaToFlux
                .window(Duration.ofSeconds(10L))
                .flatMap(stringFlux -> {
                    Flux<String> windowSize1 = stringFlux;
                    Mono<List<String>> listMono = windowSize1.collectList();
                    listMono
                            .flatMap(strings -> {
                                List<String> strings1 = strings;
                                Mono<String> rsponseClsorSpcms = call_cls_spcms(strings1);
                                return rsponseClsorSpcms;
                            });


                    Flux<Mono<String>> monoFlux = windowSize1
                            .flatMap(s -> {
                                String s1 = s;//this is list
                                Mono<String> callCls_spcms = callCls_Spcms(s1);
                                return Mono.just(callCls_spcms);
                            });
                    return monoFlux;
                });

    }

    private static Mono<String> call_cls_spcms(List<String> strings1) {//but in actual it will be list of list(window size)
        return Mono.empty();
    }

    public static Mono<String> callCls_Spcms(String s1) {
        //input is list,
        //partition into 2 part cls and spcms
        if (s1.contains("cls type")) {
            //call cls api
            //if error retry 3 time total
        } else {
            //call sp cms api
            //if error retry 3 time total
        }
        return Mono.just("response entity - contain response from cls or spcms");
    }

    private static void jobSchedulerHelper() {
        //execute/run job in background
        String job = "job1";
        String aspects = "";
        Integer callEsAndGetCOunt = 100;// client call to ES

        List<String> lidList;
        if (callEsAndGetCOunt > 100) {
            lidList = List.of("get from ES limit 10", "limit next 10");
        } else {
            lidList = List.of("single call no limit");
        }
        String sendToKafkaTopic = "lidList";
    }

    private static void jobScheduler(String event) {
        Mono<String> mono = Mono.fromCallable(() -> event);

        mono
                .flatMap(s -> {
                    //read time
                    //create job object
                    return Mono.just("job object");
                })
                .flatMap(job -> {
                    //schedule job
                    return Mono.fromCallable(() -> "scheculer obj and schedule job internally it save to db");
                });




    }


}
