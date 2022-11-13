package com.flipkart.marketplace.listings.schema;

import com.flipkart.marketplace.listings.schema.datatypes.money;
import com.flipkart.marketplace.listings.schema.reverse.listing.*;

public class Test {
    public static void main(String[] args) {
        reverse_listing reverseListing = reverse_listing.newBuilder()
                .setPrice(price.newBuilder()
                        .setBuyingPrice(money.newBuilder().setAmount(299).setCurrency("INR").build())
                        .build())
                .setSellerIdentifier(seller_identifier.newBuilder()
                        .setProductId("123")
                        .setSkuId("1231")
                        .build())
                .setBuyingCriteria(buying_criteria.newBuilder()
                        .setOrigin("origin")
                        .setTenure(buying_criteria_tenure.newBuilder().setStart(1).setEnd(2).setUnit("INR").build())
                        .setGrade("grade")
                        .build())
                .setOrderPolicy(order_policy.newBuilder()
                        .setIndependentSellable(true)
                        .build())
                .setTax(tax.newBuilder().setHsn("hsn").build())
                .build();
        System.out.println("reverseListing = " + reverseListing);

    }
}
