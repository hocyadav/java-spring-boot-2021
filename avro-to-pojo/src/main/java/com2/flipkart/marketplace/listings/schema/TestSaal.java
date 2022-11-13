package com2.flipkart.marketplace.listings.schema;

import com.flipkart.marketplace.listings.saal_schema.datatypes.money;
import com.flipkart.marketplace.listings.saal_schema.saal_listing.*;
import com.flipkart.marketplace.listings.saal_schema.services_listing;
import com.sun.tools.javah.Gen;

import java.util.Arrays;
import java.util.Optional;

public class TestSaal {
    public static void main(String[] args) {

        services_listing servicesListing = services_listing.newBuilder()
                .setSellerIdentifier(seller_identifier.newBuilder()
                        .setSkuId("sku12")
                        .setProductId("p123")
                        .build())
                .setPrice(price.newBuilder()
                        .setMrp(money.newBuilder().setAmount(123).setCurrency("INR").build())
                        .setSellingPrice(money.newBuilder().setAmount(123).setCurrency("INR").build())
                        .build())
                .setTax(tax.newBuilder().setHsn("hsn123").build())
                .setOrderPolicy(order_policy.newBuilder().setIndependentSellable(false).build())
                .setFulfilment(fulfilment.newBuilder()
                        .setRegionRestriction("bangalore")
                        .setServicesFulfilledBy("flipkart")
                        .build())
                .setAddressLabel(address_label.newBuilder()
                        .setCountryOfOrigin(Arrays.asList("IND","US"))
                        .setImporterDetails(Arrays.asList("importer 1", "importer 2"))
                        .setPackerDetails(Arrays.asList("packer 1", "packer 2"))
                        .build())
                .build();
        System.out.println("servicesListing = " + servicesListing);

        String s = fun(new GenericEntity(null));
    }

    private static String fun(GenericEntity genericEntity) {
        return null;
    }

    private Optional<tax> createTax(final GenericEntity values) {
        return hoodooDataExtractor.apply(ListingProperties.HSN, values)
                .map(HoodooData::getValue)
                .map(hsn -> tax.newBuilder()
                        .setHsn(hsn)
                        .build());
    }


}
