package io.hari.machinecodingtips.oneentity_onerepo_map.repo;

import com.github.javafaker.Faker;
import io.hari.machinecodingtips.oneentity_onerepo_map.entity.MyEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class MyEntityRepo {
    static HashMap<Integer, MyEntity> map = new HashMap<>();
    static Faker faker = new Faker();

    static {
        MyEntity myEntity = MyEntity.fakerData();
        map.put(myEntity.getLocalPKId(), myEntity);
    }

    public HashMap<Integer, MyEntity> getAll(){
        return map;
    }

    public Collection<MyEntity> getAllCollection(){
        return List.of(
                MyEntity.fakerData(),
                MyEntity.fakerData(),
                MyEntity.fakerData(),
                MyEntity.fakerData()
        );
    }

    //repo method using entity helper methods
    public List<MyEntity> foo(){
        return getAllCollection()
                .stream()
                .filter(MyEntity::filter1core)
                .collect(Collectors.toList());
    }
    public List<MyEntity> foo2(){
        Predicate<MyEntity> myEntityPredicate = MyEntity.myPredicateCheckGlobalId();//1st get predicate obj, then pass to filter
        return getAllCollection()
                .stream()
//                .filter(myEntity -> MyEntity.myPredicateCheckGlobalId().test(myEntity))//test? , m1
                .filter(myEntityPredicate)//test, m2 https://stackabuse.com/java-8-streams-guide-to-the-filter-method/
                .collect(Collectors.toList());
    }

}
