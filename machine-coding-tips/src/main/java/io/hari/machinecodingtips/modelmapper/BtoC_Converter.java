package io.hari.machinecodingtips.modelmapper;


import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class BtoC_Converter extends AbstractConverter<EntityB, EntityC> {//make model mapper plggable
    // step 0: add maven + create converter class / adding feature,
    // step 1: add converter class to modelmapper object
    // step 2: now use map(type1, type2.class)

    @Override
    protected EntityC convert(EntityB entityB) {
        return EntityC.builder().fullName(entityB.getName()).fullNumber(entityB.getNumber()).build();
    }
}
