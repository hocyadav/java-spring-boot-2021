package com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization.impl_stdserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization.ItemEntity;
import com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization.User;

import java.io.IOException;

/**
 * https://www.baeldung.com/jackson-custom-serialization
 * https://www.baeldung.com/jackson
 */
public class ItemSerializer extends StdSerializer<ItemEntity> {//0 StdSerializer<MyEntity>

    public ItemSerializer() {
        this(null);
    }

    public ItemSerializer(Class<ItemEntity> t) {
        super(t);
    }

    @Override
    public void serialize(ItemEntity item,
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
