package io.hari.javareactiveframework.mono;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

public class MonoFromSupplier_Imp {

    public static void main(String[] args) {
        //todo : it will only create pipeline, subscribe then only pipeline will execute
        getNameString();
        getNameString();

        //todo : execute pipeline + running on main thread , i.e. blocking operation / sequential operation
        getNameString()//create pipeline
                .subscribe(Util.getOnNext());

        //todo : execute pipeline + running on different thread, i.e. non blocking operation / async operation
        getNameString()
                .subscribeOn(Schedulers.single());

        //todo : execute pipeline + running on different thread, i.e. non blocking operation / async operation + block to get result
        String s = getNameString()
                .subscribeOn(Schedulers.single())
                .block();
        System.out.println(s);

    }

    public static Mono<String> getNameString() {
        System.out.println("entered getNameString..");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            sleep(4);
            return Faker.instance().name().fullName();
        }).map(String::toUpperCase);
    }

    @SneakyThrows
    public static void sleep(int timeout){
        TimeUnit.SECONDS.sleep(timeout);
    }
}
