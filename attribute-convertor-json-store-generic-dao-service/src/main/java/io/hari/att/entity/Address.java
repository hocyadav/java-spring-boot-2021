package io.hari.att.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Address {
    String landMark;

    String pincode;

    String state;
}
