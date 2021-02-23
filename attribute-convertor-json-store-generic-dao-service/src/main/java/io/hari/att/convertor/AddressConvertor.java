package io.hari.att.convertor;

import io.hari.att.entity.Address;

import javax.persistence.AttributeConverter;

public class AddressConvertor implements AttributeConverter<Address, String> {
    @Override
    public String convertToDatabaseColumn(Address address) {
        return address.getPincode();
    }

    @Override
    public Address convertToEntityAttribute(String s) {
        return Address.builder().pincode(s).landMark("tulsi theater").state("karnataka").build();
    }
}
