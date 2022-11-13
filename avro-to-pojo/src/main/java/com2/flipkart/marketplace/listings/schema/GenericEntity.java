package com2.flipkart.marketplace.listings.schema;

import lombok.NonNull;
import lombok.Value;

import java.util.Map;

@Value
public class GenericEntity {
        @NonNull
        Map<String, String> data;
}
