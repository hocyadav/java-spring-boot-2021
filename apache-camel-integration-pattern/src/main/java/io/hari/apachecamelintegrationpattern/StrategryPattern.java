package io.hari.apachecamelintegrationpattern;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.junit.Test;

@SuperBuilder @Data @ToString
class CommonRequest {
    String name;

    void commonReqFun() {
        System.out.println("default");
    }

    //extra part for different req
    ExtraRequestFields newFields;

    void extraReqFun() {
        newFields.fly();
    }
    public CommonRequest(ExtraRequestFields newFields) {
        this.newFields = newFields;
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }
}

@Data @SuperBuilder @ToString(callSuper = true)
class Request1 extends CommonRequest {
    @Override
    void commonReqFun() {
        System.out.println("ClassReq1.fun1");
    }
}

@Data @SuperBuilder @ToString(callSuper = true)
class Request2 extends CommonRequest {
    @Override
    void commonReqFun() {
        System.out.println("ClassReq2.fun1");
    }

    public Request2(ExtraRequestFields newFields) {
        super(newFields);
    }

    @Override
    void extraReqFun() {
        this.newFields.fly();
    }

}

interface ExtraRequestFields {
    void fly();
}

@Data @Builder @ToString
class ExtraRequestFieldImpl implements ExtraRequestFields {
    @Override
    public void fly() {
        System.out.println("fly ---> --> -> ");
    }
}

public class StrategryPattern {
    @Test
    public void test() {
        //if workflow_name == listing, then create req1 type
        Request1 request1 = Request1.builder().build();
        request1.setName("request 1");
        request1.commonReqFun();
//        request1.extraReqFun();//fail

        CommonRequest req1 = CommonRequest.builder().name("req 1").build();
        System.out.println("req1 = " + req1);

        //if workflow_name == state, then req2
        ExtraRequestFieldImpl extra = ExtraRequestFieldImpl.builder().build();
        Request2 request2 = Request2.builder().newFields(extra).build();
        request2.setName("request 2");
        request2.commonReqFun();
        request2.extraReqFun();

        CommonRequest req2 = CommonRequest.builder().name("req 2").newFields(extra).build();
        System.out.println("req2 = " + req2);
        //fail
//        Request2 req21 = (Request2) req2;
//        System.out.println("req21 = " + req21);
        //sol: right side actual obje and left side interface , and pass that interface to argument
        //and when required then convert to req 2 , for this use workflow name
        CommonRequest build = Request2.builder().name("req 2").newFields(extra).build();
        System.out.println("build = " + build);
        Request2 cast = Request2.class.cast(build);
        System.out.println("cast = " + cast);

        CommonRequest commonRequest2 = CommonRequest.convertInstanceOfObject(cast, CommonRequest.class);
        System.out.println("commonRequest2 = " + commonRequest2);

        Request2 request22 = CommonRequest.convertInstanceOfObject(build, Request2.class);
        System.out.println("request22 = " + request22);


        //convert to common type
        CommonRequest commonRequest = request1 instanceof CommonRequest ? ((CommonRequest) request1) : null;
        System.out.println("request1 = " + request1);
        System.out.println("commonRequest = " + commonRequest);

        CommonRequest commonRequest1 = request2 instanceof CommonRequest ? ((CommonRequest) request2) : null;
        System.out.println("request2 = " + request2);
        System.out.println("commonRequest1 = " + commonRequest1);

        //convert to impl type
        Request2 request21 = Request2.class.cast(commonRequest1);
        System.out.println("request21 = " + request21);
    }
}
