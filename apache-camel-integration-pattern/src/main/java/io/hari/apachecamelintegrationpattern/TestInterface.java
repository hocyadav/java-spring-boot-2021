package io.hari.apachecamelintegrationpattern;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

@Data @Builder
class InputPayload {
    String name;
    Integer roll;
    Integer phone;
}

abstract class Product{ }

@Data
class ProductA extends  Product{
    String name;
    Integer roll;

    public ProductA(InputPayload inputPayload) {
        name = inputPayload.getName();
        roll = inputPayload.getRoll();
    }
}

@Data
class ProductB extends Product{
    String name;
    Integer phone;

    public ProductB(InputPayload inputPayload) {
        name = inputPayload.getName();
        phone = inputPayload.getPhone();
    }
}

class Creator{
    public Product create(String type, InputPayload inputPayload){
        if (type.equals("one")) return new ProductA(inputPayload);
        else return new ProductB(inputPayload);
    }
}


public class TestInterface {
    @Test
    public void test(){
        Creator creator = new Creator();

        InputPayload inputRequest = InputPayload.builder().name("hariom").roll(123).phone(34345345).build();

        Product product = creator.create("one", inputRequest);
        Product two = creator.create("two", inputRequest);
        System.out.println("product = " + product);
        System.out.println("two = " + two);
    }
}
