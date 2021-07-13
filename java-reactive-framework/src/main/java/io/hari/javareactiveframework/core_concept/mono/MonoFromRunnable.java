package io.hari.javareactiveframework.core_concept.mono;

import reactor.core.publisher.Mono;

import static io.hari.javareactiveframework.core_concept.mono.MonoFromSupplier_Imp.sleep;

public class MonoFromRunnable {
    public static void main(String[] args) {

        //todo : blocking operation + get complete signal + do operation
        Mono.fromRunnable(testConsumingProcess())
                .subscribe(Util.getOnNext(),
                        Util.getOnError(),
                        () -> {
                            //after getting complete signal, do operation
                            System.out.println("process is done, sending emails..");
                        });

    }


    //notify once process is completed and the signal is received by the onComplete channel
    public static Runnable testConsumingProcess() {
        return () -> {
            sleep(2);
            System.out.println("Operation completed");
        };
    }
}
