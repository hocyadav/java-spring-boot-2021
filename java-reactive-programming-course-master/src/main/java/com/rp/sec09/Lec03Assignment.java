package com.rp.sec09;

import com.rp.courseutil.Util;
import com.rp.sec09.helper.BookOrder;
import com.rp.sec09.helper.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec03Assignment {

    public static void main(String[] args) {

        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );

        bookStream()
                .filter(book -> allowedCategories.contains(book.getCategory())) //OPTIONAL: some filtering
                .buffer(Duration.ofSeconds(5))                                  //GROUP: Flux<Type> --> Flux<List<Type>>
                .map(list -> revenueCalculator(list))                           //STREAM: List --> Stream/Stream group by --> Map<Key, Value>
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static RevenueReport revenueCalculator(List<BookOrder> books){
        Map<String, Double> map = books.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::getCategory,//key : field
                        Collectors.summingDouble(BookOrder::getPrice)//value: list OR some operation on list value
                ));
        return new RevenueReport(map);
    }

    private static Flux<BookOrder> bookStream(){
        return Flux.interval(Duration.ofMillis(200))
                    .map(i -> new BookOrder());
    }


}
