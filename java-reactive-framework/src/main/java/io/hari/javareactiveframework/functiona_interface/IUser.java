package io.hari.javareactiveframework.functiona_interface;

import java.util.function.Predicate;

public interface IUser {
    default Predicate<User> getWisconsin_() {
        return user -> user.name.equals("Wisconsin");
    }

//    default Predicate<User> getWisconsin_(User user2) {
//        return user -> user2.name.equals("Wisconsin");
//    }
}
