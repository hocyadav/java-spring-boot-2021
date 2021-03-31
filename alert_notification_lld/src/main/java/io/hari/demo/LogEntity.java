package io.hari.demo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
public class LogEntity {
    LocalDateTime dateTime;
    String type;
    String data;
}
