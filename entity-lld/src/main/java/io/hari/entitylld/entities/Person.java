package io.hari.entitylld.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Builder
@ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Name name;
    PhoneNumber phoneNum;
    @Builder.Default
    Address address = new Address();
    @Builder.Default
    WorkDetail workDetails = new WorkDetail();
    PersonPhoto photo;
    DateOfBirth dateOfBirth;
    @Transient
    Integer age;
    Note notes;
    @OneToMany
    List<Group> groups;

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class Name {
        @NotNull
        String firstName;
        String lastName;
        @Builder.Default
        List<String> alias = new LinkedList<>();
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class PhoneNumber {
        @Builder.Default
        List<Contact> contactCollection = new LinkedList<>();

        @Builder
        @ToString
        @NoArgsConstructor @AllArgsConstructor
        public static class Contact {
            BigInteger number;
            @Builder.Default
            ContactType type = ContactType.personal;

            @ToString
            public static enum ContactType {
                work("work"),
                personal("personal"),
                custom("");

                String type;

                ContactType(String type) {
                    this.type = type;
                }
            }
        }
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class Address {
        String address;
        AddressType type;

        @ToString
        public static enum AddressType {
            home,
            office;
        }
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class Note {
        String note;
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class WorkDetail {
        String designation;
        Company company;

        @Builder
        @ToString
        @NoArgsConstructor @AllArgsConstructor
        public static class Company {
            String name;
            Address address;
            PhoneNumber phoneNumber;
        }
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class PersonPhoto {
        String photoUrl;
    }

    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class DateOfBirth {
        @Past
        LocalDate date;
    }
}
