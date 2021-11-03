package com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization.impl_stdserializer.ItemSerializer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestCustomJacksonSerialization {

    @SneakyThrows
    @Test
    public void test() {
        ItemEntity item = ItemEntity.builder().itemName("item1").id(123).owner(User.builder().id(007).name("Hariom yadav").build()).build();
        String writeValueAsString = new ObjectMapper().writeValueAsString(item);
        System.out.println("writeValueAsString = " + writeValueAsString);//output json
    }


    @SneakyThrows
    @Test
    public void test2() {
        ItemEntity item = ItemEntity.builder().itemName("item1").id(123).owner(User.builder().id(234).name("Hariom yadav").build()).build();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = getSimpleModule();
        objectMapper.registerModule(simpleModule);

        String writeValueAsString = objectMapper.writeValueAsString(item);
        System.out.println("writeValueAsString = " + writeValueAsString);//output json
    }

    private SimpleModule getSimpleModule() {
        SimpleModule simpleModule = new SimpleModule();
        ItemSerializer itemSerializer = new ItemSerializer();//my serializer
        simpleModule.addSerializer(ItemEntity.class, itemSerializer);
        return simpleModule;
    }

    @SneakyThrows
    @Test
    public void test3() {
        OrderEntity item = OrderEntity.builder().itemName("item1").id(123).owner(User.builder().id(234).name("Hariom yadav").build()).build();
        String writeValueAsString = new ObjectMapper().writeValueAsString(item);
        System.out.println("writeValueAsString = " + writeValueAsString);//output json
    }


}
