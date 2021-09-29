package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 19/06/21
 *
 * file simple language : https://camel.apache.org/components/latest/languages/file-language.html
 * this is camel simple language :  https://camel.apache.org/components/latest/languages/simple-language.html
 */
//@Component
public class SimpleLanguageSDK extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        routeWithChoice();
        routeWithChoiceAndBodyTypeAsString();
    }

    private void routeWithChoice() {
        from("file:files/input").routeId("File_Input_Route_OMPRAKASH")//we can add route id else camel will create some random route name
                .log("${body}")
                .choice()
                .when(simple("${file:ext} ends with 'txt'"))
                //other processing logic
                .log("File ends with TXT")
                .otherwise()
                .log("File NOT ends with TXT")
                .end()
                .to("file:files/output")
                .log("${body}");
    }

    private void routeWithChoiceAndBodyTypeAsString() {
        from("file:files/input").routeId("File_Input_Route_HARIOM")//we can add route id else camel will create some random route name
                .log("file absolute path : ${file:absolute.path}")
                .convertBodyTo(String.class)//converting input body to string type
                .choice()
                    .when(simple("${file:ext} ends with 'txt'"))
                        .log("File ends with TXT")
                    .when(simple("${body} contains 'omprakash yadav'"))//working but best practise first convert body to string type
                        .log("body contains omprakash yadav")
                    .otherwise()
                        .log("File NOT ends with TXT")
                .end()
                .to("file:files/output")
                .log("${messageHistory}");//camel simple language
    }
}
