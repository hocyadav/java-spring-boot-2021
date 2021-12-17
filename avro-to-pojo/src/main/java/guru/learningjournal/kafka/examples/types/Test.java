package guru.learningjournal.kafka.examples.types;

import io.hari.entity.Address;
import io.hari.entity.MyAddress;
import io.hari.entity.Student;
import org.apache.avro.Schema;

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


    }
}
