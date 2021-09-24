package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.elasticsearch.ElasticsearchComponent;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 * 1 docker install steps : 3min -> https://youtu.be/LtooWDUL1Js
 * 2 kafka install 2min using docker compose -> see docker-compose yml file for zookeeper and kafka ->  $docker-compose up
 * 3 : add dependency for kafka connector
 * 3.a add camel-kafka application properties for apache camel (where kafka is runing url+port): camel.component.kafka.brokers=localhost:9092
 */
//@Component
public class ESSenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        CamelContext context = getContext();

        ElasticsearchComponent elasticsearchComponent = new ElasticsearchComponent();
        elasticsearchComponent.setHostAddresses("localhost:9200");
        context.addComponent("elasticsearch-rest", elasticsearchComponent);

//        from("file:files/input2").routeId("ES-sender-route-1")
//                .log("${body}")
//                .to("log:my-camel-topic-log--");
//        .to("elasticsearch://my_index");

//        from("direct:search")
//        from("file:files/input2").routeId("ES-sender-route-1")
//                .to("elasticsearch-rest://elasticsearch?operation=Search&indexName=twitter&indexType=tweet&useScroll=true&scrollKeepAliveMs=30000");
//                .to("elasticsearch-rest://local?operation=Search&indexName=twitter&indexType=tweet&useScroll=true&scrollKeepAliveMs=30000");
//                .to("elasticsearch-rest://my-local-cluster?operation=INDEX&indexName=messages&indexType=doc&hostAddresses=localhost:9200");
//        .to("elasticsearch-rest://my-local-cluster?operation=Index&indexName=twitter");//working

        //my-local-cluster

//        <to id="elastic_search_camel"
//        uri="elasticsearch://my-local-cluster?operation=INDEX&amp;indexName=messages&amp;indexType=doc&amp;ip=localhost&amp;port=9300" />

//                <to id="elastic_search_camel"
//        uri="elasticsearch-rest://my-local-cluster?operation=INDEX&amp;indexName=messages&amp;indexType=doc&amp;hostAddresses=localhost:9200" />


        from("timer:first-timer-3") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .log("${body}")
                .transform().constant("my constant 3")
                .to("elasticsearch-rest://my-local-cluster?operation=Index&indexName=twitter");

    }
}
