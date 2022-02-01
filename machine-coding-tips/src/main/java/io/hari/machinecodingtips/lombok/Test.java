package io.hari.machinecodingtips.lombok;

public class Test {
    public static void main(String[] args) {

        MyEntity myEntity = MyEntity.of("hariom", "bangalore", 12345);
        System.out.println("myEntity = " + myEntity);

        MyEntity2 myEntity2 = new MyEntity2("hariom", "bangalore", 12345);
        System.out.println("myEntity2 = " + myEntity2);

        MyEntity3 myEntity3 = MyEntity3.builder().name("hariom").address("bangalore").phone(1234).build();
        System.out.println("myEntity3 = " + myEntity3);

        //Best way : add both staticName + builder
        MyEntity4 myEntity4 = MyEntity4.of("hariom", "bangalore", 12345);
        System.out.println("myEntity4 = " + myEntity4);
        MyEntity4 myEntity41 = MyEntity4.builder().name("hariom").address("bangalore").phone(123134).build();
        System.out.println("myEntity41 = " + myEntity41);


        MyEntity5 myEntity5 = MyEntity5.create("hariom", "bangalore", 12345);
        System.out.println("MyEntity5 = " + myEntity5);
        MyEntity5 myEntity51 = MyEntity5.builder().name("hariom").address("bangalore").phone(123134).build();
        System.out.println("myEntity51 = " + myEntity51);

    }
}
