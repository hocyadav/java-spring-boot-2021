
```java
//step 1: entity type 1, entity type 2

//step 2: converter class
@Component
public class BtoC_Converter extends AbstractConverter<EntityB, EntityC> {//make model mapper plggable
    @Override
    protected EntityC convert(EntityB entityB) {
        return EntityC.builder().fullName(entityB.getName()).fullNumber(entityB.getNumber()).build();
    }
}
// step 3 
ModelMapper modelMapper = new ModelMapper();
modelMapper.addConverter(btoCConverter);
```