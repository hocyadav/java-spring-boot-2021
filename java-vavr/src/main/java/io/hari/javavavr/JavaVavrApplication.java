package io.hari.javavavr;

import io.vavr.API;
import io.vavr.Lazy;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.function.Supplier;

public class JavaVavrApplication {

    @Test
    public void optionalTest1() {
        Option<String> option = API.Option("54");
        System.out.println("option = " + option);

        Optional<String> javaOptional = option.toJavaOptional();
        System.out.println("javaOptional = " + javaOptional);
    }

    //OPTIONAL : Success/NOT empty or Failure/Empty, Success == value, Failure == null
    //TRY : Success or Failure, Success == value, Failure == Exception
    @Test
    public void optionalTest2() {
        List<Option<Integer>> optionList = API.List(
                API.Option(1),
                API.Option(2),
                API.Option(3),
                API.Option(null)
        );
        java.util.List<Option<Integer>> javaOptional = optionList.asJava();
        System.out.println("javaOptional = " + javaOptional);

        List<Integer> list = optionList
                .filter(integers -> integers.isDefined())
                .map(integers -> integers.get());//dont use get() not a good practice
        System.out.println("list = " + list);

        List<Option<Integer>> options = optionList
                .filter(integers -> integers.isDefined())
                .map(integers -> integers);//
        System.out.println("options = " + options);
    }

    @Test
    public void optionalTest3() {
        List<Option<Integer>> optionList = API.List(
                API.Option(1),
                API.Option(2),
                API.Option(3),
                API.Option(null)
        );
        System.out.println("optionList = " + optionList);

//		List is Iterable, Option inside is Iterable, so we can use flatmap to remove Iterable
        List<Integer> listAfterFlatMap = optionList.flatMap(integers -> integers);//it also remove null
        System.out.println("listAfterFlatMap = " + listAfterFlatMap);
    }


    @Test
    public void tryTest1() {
        //OLD style
        //m1: use try-catch, or use sneaky throw
        try {
            URI uri1 = new URI("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //Vavr : it can handle checked exceotion
        Try.of(() -> new URI(""))
                .map(uri -> uri.toString())
                .filter(s -> true)
                .getOrElseThrow(() -> new RuntimeException("my exception"));
//				.getOrElse("other");
    }

    @Test
    public void tryTest2() {
        Try.of(() -> new URI(""))
                .recoverWith(URISyntaxException.class, Try.of(() -> new URI("valid url")))//if we know how to handle some type of exception then
                .map(uri -> uri.toString())
                .filter(s -> true)
                .getOrElse("other");
    }


    @Test
    public void lazyTest1() {
        //java Supplier / "expression of our will"
        Supplier<Integer> integerSupplier = () -> {
            System.out.println("computing");
            return 43;
        };

        integerSupplier.get();
        integerSupplier.get();
        integerSupplier.get();
//computing
//computing
//computing
    }

    @Test
    public void lazyTest2() {
        //java Supplier / "expression of our will"
        Supplier<Integer> integerSupplier = () -> {
            System.out.println("computing");
            return 43;
        };
        Lazy<Integer> integerLazy = Lazy.of(integerSupplier);
//        integerLazy.map(...)..//we can use stream over Lazy, since it impl Value and Value impl Iterable

        integerLazy.get();
        integerLazy.get();
        integerLazy.get();
//computing
    }

}
