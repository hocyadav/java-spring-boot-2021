package io.hari.javareactiveframework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hariom Yadav
 * @since 12/06/21
 */
@Data
@AllArgsConstructor
@Builder
public class Game {
    String name;
    Integer inventory;
}
