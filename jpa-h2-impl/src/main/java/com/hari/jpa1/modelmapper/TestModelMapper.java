package com.hari.jpa1.modelmapper;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

class Parent{}
@Data
class ChildA extends Parent{
    String name;
}
class ChildB extends Parent{}

abstract class ParentDTO{}
@Data
class ChildADTO extends ParentDTO{
    String name;
}
class ChildBDTO extends ParentDTO{}

public class TestModelMapper {
    @Test
    public void foo(){
        ModelMapper modelMapper = new ModelMapper();
        ChildA childA = new ChildA();
        childA.setName("hari");
        System.out.println("childA = " + childA);

        //set type mapping : childA to parentDTO then give me ParentDTO child class i.e. ChildADTO
        TypeMap<ChildA, ParentDTO> typeMap = modelMapper.typeMap(ChildA.class, ParentDTO.class);
        typeMap.setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), ChildADTO.class));
        //test above type mapping , we ask for parentDTO but we will get ChildADTO object
        TypeMap<ChildA, ParentDTO> typeMap1 = modelMapper.getTypeMap(ChildA.class, ParentDTO.class);
        ParentDTO parentDTO = typeMap1.map(childA);
        System.out.println("parentDTO = " + parentDTO);

        //m2 ??
        ChildADTO childADTO = modelMapper.map(childA, ChildADTO.class);
        System.out.println("childADTO = " + childADTO);


    }
}
