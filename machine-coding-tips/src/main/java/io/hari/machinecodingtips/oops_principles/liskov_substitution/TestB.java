package io.hari.machinecodingtips.oops_principles.liskov_substitution;

class ClassA {
    public void oldLogic(){
        //some code
    }
}

class ClassB extends ClassA {
    //it has all feature / properties that classA has
    //it can also alter classA feature by overiding (this is liskov substituion) , if this class override and change then it should not change old logic,
    // coz classA was using many places in code, in simple word if we pass instance of classB wherever classA is used then it should not break or not create any bug : https://www.youtube.com/watch?v=-RNkWv-d4zM

    @Override
    public void oldLogic() {
        super.oldLogic();
    }
}


class ServiceClass {
    public void foo(ClassA classA) {//if we pass classB instance then method body should work
        classA.oldLogic();
    }
}

public class TestB {
    public static void main(String[] args) {

    }
}
