package task;

import io.dropwizard.servlets.tasks.PostBodyTask;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author Hariom Yadav
 * @create 5/25/2021
 * https://www.dropwizard.io/en/latest/manual/core.html#tasks
 */
public class MyPostBodyTask extends PostBodyTask {//b1 create task class : curl -X POST http://localhost:8081/tasks/task1

    public MyPostBodyTask() {
        super("task2");//b2. set task name
    }

    @Override
    public void execute(Map<String, List<String>> map, String postBody, PrintWriter printWriter) throws Exception {
        System.out.println("My post task");
//        printWriter.write(postBody);//correct way : https://www.dropwizard.io/en/latest/manual/core.html#tasks
        printWriter.write("My post task");//this set body of post request, since here we are not sending any post body so hardcoding
        printWriter.flush();
    }
}
