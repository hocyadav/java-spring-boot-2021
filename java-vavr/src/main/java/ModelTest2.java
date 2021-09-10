import org.modelmapper.ModelMapper;

public class ModelTest2 {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Type1 type1 = Type1.builder().name("hari").build();
        System.out.println("type1 = " + type1);
//        Type2 type2 = modelMapper.map(type1, Type2.class);
//        System.out.println("type2 = " + type2);

        ModelMapperClass modelMapperClass= new ModelMapperClass();
        Type2 convert = modelMapperClass.convert(type1);
        System.out.println("convert = " + convert);

    }
}
