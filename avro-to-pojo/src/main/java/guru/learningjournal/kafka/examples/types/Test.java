package guru.learningjournal.kafka.examples.types;

import io.hari.entity.MyAddress;
import io.hari.entity.Student;

public class Test {
    public static void main(String[] args) {
//        Student student = Student.newBuilder().setName("hariom").setPhoneNumber(9887766).build();
//        Schema studentSchema = student.getSchema();
//        System.out.println("studentSchema = " + studentSchema);
//        System.out.println("student = " + student);
//
//        Address address = Address.newBuilder().setStreet("street 1").setPinCode(123).setCountry("India").build();
//        Schema addressSchema = address.getSchema();
//        System.out.println("\naddressSchema = " + addressSchema);
//        System.out.println("address = " + address);

        Student student1 = Student.newBuilder()
                .setAddress(MyAddress.newBuilder().setStreet("street 1").setPinCode(123).setCountry("India").build())
                .setName("hari")
                .setPhoneNumber(9887766)
                .build();
        System.out.println("student1 = " + student1);
        System.out.println("student1 = " + student1.toString());

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue(student1.toString(), new TypeReference(Map<String, Object>){});
//        final Map<String, Object> facets = objectMapper.readValue(student1, new TypeReference<Map<String, Object>>() {});



    }
}
