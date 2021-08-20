package io.hari.apachecamelintegrationpattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

//abstract class ClassY<T> {
//    private T abs;
//
//    public ClassY(T abs) {
//        this.abs = abs;
//    }
//}
//
//@Data @NoArgsConstructor @ToString(callSuper = true)
//class ClassC extends ClassY<String> {
//    String name;
//    public ClassC(String abs) {
//        super(abs);
//    }
//}

@Data
@NoArgsConstructor
@ToString
abstract class Iclass {
    private String abs;

    public Iclass(String abs) {
        this.abs = abs;
    }
}

@Data
@ToString(callSuper = true)
class ClassA extends Iclass {
    String name;

//    public ClassA(String abs) {//working
//        super(abs);
//    }
    public ClassA() {//working
        super("input_a");
    }
}

@Data
@NoArgsConstructor
@ToString(callSuper = true)
class ClassB extends Iclass {
    Integer roll;

    public ClassB(String abs) {
        super(abs);
    }
}

public class SampleGenricCode {
    @Test
    public void test() {
        System.out.println("SampleGenricCode.test");

//        ClassA classA = new ClassA("input_a");//m1: working
        ClassA classA = new ClassA();//m2. add from constructor
        classA.setName("hariom");

//        ClassB classB = new ClassB("input_b");//m1 working
        ClassB classB = new ClassB();//m2 working using setter, add from setter
        classB.setAbs("input_b");
        classB.setRoll(123);

//        fun(classA, "input_a");
//        fun(classB, "input_b");

        fun_2(classA);
        fun_2(classB);

    }

    private void fun(Iclass input, String in) {
        Iclass input1 = input;
        if (in.equals("input_a")) {
            ClassA cast = ClassA.class.cast(input1);
            System.out.println("cast A= " + cast);
        }
        if (in.equals("input_b")) {

            ClassB cast = ClassB.class.cast(input1);
            System.out.println("cast B= " + cast);
        }
    }

    private void fun_2(Iclass input) {//passing interface/abstract
        Iclass input1 = input;
        String in = input.getAbs();

        if (in.equals("input_a")) {
            ClassA cast = ClassA.class.cast(input1);
            System.out.println("cast A= " + cast);
            fun_3(cast);//send class obje and receiver will store in interface and then it will convert back to class obj
        }
        if (in.equals("input_b")) {
            ClassB cast = ClassB.class.cast(input1);
            System.out.println("cast B= " + cast);
            fun_3(cast);
        }
    }

    private void fun_3(Iclass input1) {//passing interface/abstract
        String in = input1.getAbs();

        if (in.equals("input_a")) {
            ClassA cast = ClassA.class.cast(input1);
            System.out.println("cast A--= " + cast);
        }
        if (in.equals("input_b")) {

            ClassB cast = ClassB.class.cast(input1);
            System.out.println("cast B--= " + cast);
        }
    }
}
