package wf;

public class TestOrchInterfacePattern {
    public static void main(String[] args) {
        Class1 class1 = new Request1();
        Class1 class2 = new Request2();

        Workflow<Class1> workflow = new Workflow<>();
        workflow.setRequest(class1);
        System.out.println("workflow = " + workflow);

        Workflow<Request1> cast = Workflow.class.cast(workflow);//working
        System.out.println("cast = " + cast);
    }
}
