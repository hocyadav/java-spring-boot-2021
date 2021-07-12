package io.hari.javareactiveframework.flux;

import com.github.javafaker.Faker;
import io.hari.javareactiveframework.mono.Util;
import org.slf4j.MDC;
import reactor.core.publisher.Flux;

public class FluxFixTakeUsingGenerate {

    public static void main(String[] args) {

//        generate1_infiniteLoop();
        /**
         * infinite data...
         */

//        generate2_infiniteLoop_n_completeSignal();
        /**
         * emitting :
         * Received = Tanzania, United Republic of
         * Completed signal..
         */

//        generate2_infiniteLoop_n_errorSignal();
        /**
         * emitting :
         * Received = Oman
         * ERROR = oops !!
         */
//        generate2_infiniteLoop_n_errorSignal2();
        /**
         * emitting :
         * ERROR = oops !!
         */

        generate3_infiniteLoop_controlUsing_Take();
        /**
         * emitting :
         * Received = Botswana
         * emitting :
         * Received = Ghana
         * Completed signal..
         */

    }

    private static void generate2_infiniteLoop_n_errorSignal2() {
        Flux.generate(synchronousSink -> {//infinite loop
            System.out.println("emitting : ");
            synchronousSink.error(new RuntimeException("oops !!"));//stop after emitting above 1
        })
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());
    }

    private static void generate2_infiniteLoop_n_errorSignal() {
        Flux.generate(synchronousSink -> {//infinite loop
            System.out.println("emitting : ");
            synchronousSink.next(Faker.instance().country().name());
//            synchronousSink.complete();//stop after emitting above 1
            synchronousSink.error(new RuntimeException("oops !!"));//stop after emitting above 1
        })
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());
    }

    private static void generate3_infiniteLoop_controlUsing_Take() {
        Flux.generate(synchronousSink -> {//infinite loop + once it will get cancel signal from downstream then it will send complete signal and stop emitting
            System.out.println("emitting : ");
            synchronousSink.next(Faker.instance().country().name());
        })
                .take(2)//send cancel signal to upstream after 2, and send complete signal to downstream after 2
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());
    }

    private static void generate2_infiniteLoop_n_completeSignal() {
        Flux.generate(synchronousSink -> {//infinite loop
            System.out.println("emitting : ");
            synchronousSink.next(Faker.instance().country().name());
            synchronousSink.complete();//stop after emitting above 1
        })
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());
    }

    private static void generate1_infiniteLoop() {
        Flux.generate(synchronousSink -> {//infinite loop
            System.out.println("emitting : ");
            synchronousSink.next(Faker.instance().country().name());
        })
                .subscribe(Util.getOnNext(), Util.getOnError(), Util.getOnComplete());
    }
}
