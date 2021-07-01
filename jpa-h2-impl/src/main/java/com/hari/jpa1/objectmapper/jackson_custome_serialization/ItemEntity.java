package com.hari.jpa1.objectmapper.jackson_custome_serialization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntity {
    public int id;
    public String itemName;
    public User owner;
}
