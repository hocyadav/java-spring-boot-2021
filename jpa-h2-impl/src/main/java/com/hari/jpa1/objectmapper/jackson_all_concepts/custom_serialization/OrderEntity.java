package com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hari.jpa1.objectmapper.jackson_all_concepts.custom_serialization.impl_stdserializer.OrderEntitySerializer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize(using = OrderEntitySerializer.class)
public class OrderEntity {
    public int id;
    public String itemName;
    public User owner;
}
