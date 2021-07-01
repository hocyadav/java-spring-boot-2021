package com.hari.jpa1.objectmapper.jackson_custome_serialization.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hari.jpa1.objectmapper.jackson_custome_serialization.ItemEntity;
import com.hari.jpa1.objectmapper.jackson_custome_serialization.User;

import java.io.IOException;

/**
 * https://www.baeldung.com/jackson-custom-serialization
 * https://www.baeldung.com/jackson?__cf_chl_managed_tk__=91b047bc11b89e5f0ebfa94b53256a250c44230b-1625141584-0-AfTWVlNTvzP79bN4Hl383uDhJfl_hKqi3hS_3WM0_CLAMKzACbuolXvTh0uP4ZqcyrHGn-N3z85r2K7jaaJnjw0udyUv4SVbotLn1LCfVqel8ZI-fuq0m7TyR4VT1hfrAaRPr8XlhlQx69p7tVzCXqrEBOOYbUDp9weQbEUPPYrj3a3nB1-BCZgVn9sWLzOVKK4qqaoDjzMIOqbKcKQ7QeVTYkN7-cAqhzkXE1reG-9ZL_kuojq-1TcNTdc2yXi3IBItytd0SZYLtVzQuluenSOWoqRhYfrkFbq9qYWUXivL3AzsWaLTHlJlLU9KtXVyQQiqNjRrmIIrTlkFU9gm26hI2SrlVjhkSEANcn-u2sPXu-pyUXj4gyrOWuXFbimYYNWChT3Fb3l8AHYBRb4L61M8c_jCbzlqDGI_OpLTRbkckU_Z0BW8AtrRAlyX8XSmRWI1O482_htDWRne-A9WBIPh2O8M5EczFJ814wYFAZVtlPk35VQMwZG1JNqn9MmCgKQBCqx5OQCdV-LdMw_fU39-pBCstbmO_J1Q7vPldQaJdDttbtWIlVdpAVUNuKpDy_4vBWn3RLJ1vsvHU_9BKtJ6vAgb9CKHTx241_2HnuMIefvYipQEZot08l6JAQpHFgJd4gD-5lH2ogDNaJrBRPco7BZoglt3KeSxZsxfcqfu
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
