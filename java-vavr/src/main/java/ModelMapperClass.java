import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

//@Component//optional
public class ModelMapperClass extends AbstractConverter<Type1, Type2> {//this is same as Function interface in java no difference
    @Override
    protected Type2 convert(Type1 type1) {
        return Type2.builder().full(type1.getName()).build();
    }
}
