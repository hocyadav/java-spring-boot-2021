package task;

import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author Hariom Yadav
 * @create 5/25/2021
 */
public class MySimpleTask extends Task {//b1 create task class : curl -X POST http://localhost:8081/tasks/task1

    public MySimpleTask() {
        super("task1");//b2. set task name
    }

    @Override
    public void execute(Map<String, List<String>> map, PrintWriter printWriter) throws Exception {
        //task logic
        System.out.println("My task - this operation is executed from outside via POST command!!");
    }
}
