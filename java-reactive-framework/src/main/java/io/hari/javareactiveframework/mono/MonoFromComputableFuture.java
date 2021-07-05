package io.hari.javareactiveframework.mono;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static io.hari.javareactiveframework.mono.MonoFromSupplier_Imp.sleep;

public class MonoFromComputableFuture {
    public static void main(String[] args) {

        //todo : running in different thread (non blocking operation)
        Mono.fromFuture(getNameFuture())
                .subscribe(Util.getOnNext());

        sleep(4);//to see result
    }

    public static CompletableFuture<String> getNameFuture() {
        return CompletableFuture.supplyAsync(() -> {//run in different thread + take some time to complete
            System.out.println(Thread.currentThread().getName());
            sleep(2);
            return Faker.instance().name().fullName();
        });
    }
}
