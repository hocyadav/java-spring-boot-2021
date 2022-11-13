package io.hari.machinecodingtips.oneentity_onerepo_map.entity;

import com.github.javafaker.Faker;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Predicate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
@ToString(callSuper = false)
public class MyEntity {
    Long globalId;

    @NotNull
    int localPKId;

    @NotEmpty
    String address;

    @Email
    String email;

    WrapperEntity wrapperEntity;

    //metadata + helper function

    public static MyEntity fakerData(){
        Faker faker = new Faker();

        return MyEntity.builder()
                .globalId(faker.random().nextLong(100))
                .localPKId(faker.random().nextInt(100))
                .address(faker.address().streetAddress())
                .email("email@gmail.com")
                .wrapperEntity(WrapperEntity.builder()
                        .wrapperName(faker.name().firstName())
                        .collection(List.of("hari","om",faker.name().firstName(),faker.name().firstName()))
                        .build())
                .build();
    }

    public boolean filter1core(){
        return globalId == 1l;
    }

//    public void foo(){//1. create new predicate, 2. extract to method, 3. delete this method
//        Predicate<MyEntity> predicate = myPredicateCheckGlobalId();
//    }

    public static Predicate<MyEntity> myPredicateCheckGlobalId() {//we need predicate obj, so make predicate as static method easily we can create obj
        return new Predicate<>() {
            @Override
            public boolean test(MyEntity myEntity) {
                return myEntity.globalId == 1l;
            }
        };
    }
}
