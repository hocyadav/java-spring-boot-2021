package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import com.rp.sec04_operators.helper.OrderService;
import com.rp.sec04_operators.helper.UserService;

import java.io.BufferedReader;

public class Lec12FlatMap {

    public static void main(String[] args) {

        BufferedReader reader;


        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // mono / flux
               // .filter(p -> p > 10)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }
}
