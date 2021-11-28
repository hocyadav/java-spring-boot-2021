package io.hari.javareactiveframework.functiona_interface;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter @Setter @ToString
class User {
    String name, role;
    User(String a, String b) {
        name = a;
        role = b;
    }

    public static void main(String args[]) {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "admin"));
        users.add(new User("Peter", "member"));
        users.add(new User("Wisconsin", "admin"));

        List admins = myProcess(users, (User u) -> u.getRole().equals("admin"));//pass list + predicate as argument
        System.out.println(admins);

        List admins2 = myAdminProcess(users);//pass list + predicate as hardcoded
        System.out.println(admins2);

        //multiple predicate
        //https://stackoverflow.com/questions/24553761/how-to-apply-multiple-predicates-to-a-java-util-stream

        Predicate<User> nonNullPredicate    = Objects::nonNull;
        Predicate<User> nameNotNull         = user -> user.name != null;
        Predicate<User> teamWIPredicate     = user -> user.name.equals("Wisconsin");

        //mix of and, or
        Predicate<User> fullPredicate = nonNullPredicate
                                        .and(nameNotNull)
                                        .or(teamWIPredicate);

        List<User> teams2 = users.stream().filter(fullPredicate)
                .collect(Collectors.toList());
        System.out.println("teams2 = " + teams2);

        //all and
        Predicate<User> fullPredicate2 = nonNullPredicate
                                        .and(nameNotNull)
                                        .and(teamWIPredicate);

        List<User> teams23 = users.stream().filter(fullPredicate2)
                .collect(Collectors.toList());
        System.out.println("teams23 = " + teams23);

        List<User> list = users.stream()
                .filter(nonNullPredicate.and(nameNotNull).or(teamWIPredicate))//inside filter we can add multiple predicates - awesome - machine coding
                .collect(Collectors.toList());
        System.out.println("list = " + list);

        //todo : move predicate inside Entity class and use inside filter


    }

    public static List<User> myProcess(List<User> users, Predicate<User> adminCheckPredicate) {//predicate arg can be hardcoded inside entity class
        List<User> result = new ArrayList<>();
        for (User user : users)
            if (adminCheckPredicate.test(user)) result.add(user);
        return result;
    }

    public static List<User> myAdminProcess(List<User> users) {//predicate arg can be hardcoded inside entity class
        Predicate<User> adminCheckPredicate = u -> u.getRole().equals("admin");//this can be further extracted as method + this predicate can be complex logic on entity , may be multiple checks
        //check for multiple predicate combine and create one predicate

        List<User> result = new ArrayList<>();
        for (User user : users)
            if (adminCheckPredicate.test(user)) result.add(user);
        return result;
    }
}
