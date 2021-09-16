package pattern.gateway.impl;

import pattern.gateway.ICreator;
import pattern.gateway.MyEntity_1;

public class MyCreator1 implements ICreator {
    private String request1;
    @Override
    public MyEntity_1 creatorMyEntity() {
        return MyEntity_1.builder().request1(request1).build();
    }
}
