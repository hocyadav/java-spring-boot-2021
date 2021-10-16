package io.hari.machinecodingtips.modelmapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {
    private final BtoC_Converter btoCConverter;//step 1

    public void foo(){
        EntityB entityB = EntityB.builder().name("hariom").number(1234).build();
        System.out.println("entityB = " + entityB);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(btoCConverter);//step 2

        EntityC entityC = modelMapper.map(entityB, EntityC.class);
        System.out.println("entityC = " + entityC);
    }
}
