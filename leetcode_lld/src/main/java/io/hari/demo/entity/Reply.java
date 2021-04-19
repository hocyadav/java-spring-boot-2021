package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
@Entity
public class Reply extends BaseEntity{
    Long userId;
    String reply;
}
