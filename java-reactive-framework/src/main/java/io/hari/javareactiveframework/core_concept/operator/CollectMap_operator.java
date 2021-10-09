package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class CollectMap_operator {
    public static void main(String[] args) {
        Flux<String> input1 = Flux.just("a", "b", "c", "z", "a");

        Flux<String> input2 = input1//1
                .sort();//2 operator

        input2//3
                .subscribe(s -> System.out.println("s = " + s));//4

        //todo : collect List : values store in list
        //useful when we want to convert flux -> list , since "flux" is "list" only
        Mono<List<String>> input3 = input2//1
                .collectList();//2
        input3//3
                .subscribe(strings -> System.out.println("data as list = " + strings));

        //todo : collect Map : values store in map value location, key is anything using Function
        //flux --> list --> map<flux/list value, flux/list value> --> map<k, v> k : actual flux/list value or own modified for this we have used Function, v: 1st flux / list value
        Mono<Map<String, String>> input4 = input1
                .collectMap(input -> {
                    //case 1
                    if (input == "a") return "key1";
                    if (input == "b") return "key2";
                    if (input == "c") return "key3";
                    if (input == "d") return "key4";
                    return "default-key";

                    //case 2
//            return input;
                });
        input4.subscribe(stringStringMap -> System.out.println("data as map = " + stringStringMap));
    }
}
