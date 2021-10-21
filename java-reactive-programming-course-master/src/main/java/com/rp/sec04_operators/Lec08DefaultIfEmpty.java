package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                //defaultIfEmpty : try to write in one line
                .filter(i -> i > 10).defaultIfEmpty(-100)//send single hardcoded value, type will be same as above pipeline data
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 12);
    }


}
