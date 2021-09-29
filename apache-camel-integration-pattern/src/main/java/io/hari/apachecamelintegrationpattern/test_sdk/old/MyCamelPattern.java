package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Hariom Yadav
 * @since 20/06/21
 */
//@Component
public class MyCamelPattern extends RouteBuilder {

    @Autowired
    MySplitterLogicClass mySplitterLogicClass;

    @Override
    public void configure() throws Exception {
        //1 pipeline is default pattern
//        pipelinePattern();

        //2 choice pattern/content based routing : using choice() when() otherwise()

        //3. multicast : send input to multiple endpoint
//        multicastPattern();

        //3. splitter pattern : add csv dependency -> split file into rows -> send to multipl
//        spiltPattern_CSVFileAsSingleRead();
//        spiltPattern_CSVFileToRows();
//        splitPattern_AnyInputMessageToOwnSplitter();
        splitPattern_usingBean();
    }

    private void splitPattern_usingBean() {
        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(method(mySplitterLogicClass))
                .to("log:my-log-split");//send to kafka, db etc..
    }

    private void splitPattern_AnyInputMessageToOwnSplitter() {
        from("file:files/csv")//input : msg1,msg2,msg3 -> split using ,
                .convertBodyTo(String.class)//similar to transform()
                .split(body(), ",")//split using own delimiter
                .to("log:my-log-split");//send to kafka, db etc..
    }

    private void spiltPattern_CSVFileToRows() {
        from("file:files/csv")
                .unmarshal().csv()//pom dependency
                .split(body())
                .to("log:my-log-csv");//send to kafka, db etc..
    }

    private void spiltPattern_CSVFileAsSingleRead() {
        from("file:files/csv")
                .split(body())
                .to("log:my-log-csv");//send to kafka, db etc..
    }

    private void multicastPattern() {
        from("timer:my-pattern-timer")
                .multicast()
                .to("log:my-log-endpoint-1", "log:my-log-endpoint-2", "log:my-log-endpoint-3");
    }

    private void pipelinePattern() {
        from("timer:my-pattern-timer")
                .pipeline()//this is default , no need to add
                .log("test 1")
                .log("test 2")
                .log("test 3")
                .to("log:my-log-pattern");
    }

}

@Component
class MySplitterLogicClass {

//    public List<String> splitLogic() { //working
//        return List.of("ABC", "DEF", "GHI");
//    }
    public List<String> splitLogic(String body) { //working
        return List.of("ABC", "DEF", "GHI");
    }
}
