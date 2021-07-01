package com.hari.jpa1.objectmapper.jackson.custom_serialization.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hari.jpa1.objectmapper.jackson.custom_serialization.OrderEntity;
import com.hari.jpa1.objectmapper.jackson.custom_serialization.User;

import java.io.IOException;

public class OrderEntitySerializer extends StdSerializer<OrderEntity> {//0 StdSerializer<MyEntity> -> add annotation @JsonSerialize(using = OrderEntitySerializer.class)

    public OrderEntitySerializer() {
        this(null);
    }

    public OrderEntitySerializer(Class<OrderEntity> t) {
        super(t);
    }

    @Override
    public void serialize(OrderEntity item,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        User user = item.owner;

        jsonGenerator.writeStartObject();//1
        jsonGenerator.writeNumberField("id", item.getId());
        jsonGenerator.writeStringField("itemName", item.getItemName());
        jsonGenerator.writeNumberField("owner", user.getId());
        jsonGenerator.writeEndObject();//2
    }
}
