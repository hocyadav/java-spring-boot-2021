package io.hari.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {//step 4 . add serializable
    String name;
    String address;
}
