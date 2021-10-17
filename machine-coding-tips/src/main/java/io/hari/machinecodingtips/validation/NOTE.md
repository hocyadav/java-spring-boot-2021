#### pending : 
- GroupSequence, https://www.baeldung.com/javax-validation-groups
- add all javax validation inside my entity class
---
```java
//step 1
public interface StringValidation {// validation group marker interface
}

//step 2
public class EntityA {
    @NotBlank(groups = {StringValidation.class})//then it will be not validated by @valid only also we need to add @validated group name, see ServiceNew impl
    String name;

//step 3
@Service
@Validated
public class MyServiceNewStyle {//1. add @Validated class level & method level, 2. at method level also add groups name

    //m1
    @Validated({StringValidation.class})
    public void stringMethod(@Valid EntityA entityA) {
        System.out.println("NEW : number validation done : entityA = " + entityA);
    }
    
```
---

#### validate client json input (use case k8s validate user input yaml)- explore
- https://json-schema.org/learn/miscellaneous-examples.html

---

