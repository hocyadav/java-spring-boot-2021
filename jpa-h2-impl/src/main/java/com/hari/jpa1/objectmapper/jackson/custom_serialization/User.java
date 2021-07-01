package com.hari.jpa1.objectmapper.jackson.custom_serialization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    public int id;
    public String name;
}
