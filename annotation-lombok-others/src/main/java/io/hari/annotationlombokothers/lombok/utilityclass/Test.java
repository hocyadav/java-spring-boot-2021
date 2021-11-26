package io.hari.annotationlombokothers.lombok.utilityclass;

import io.hari.annotationlombokothers.lombok.utilityclass.MyUtilityClass;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Test implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Test.run");
        //utility class - static so directly call no need to create bean
        MyUtilityClass.foo();//public method become static
        //public field become static
        System.out.println("rollNo = " + MyUtilityClass.rollNo);

    }
}
