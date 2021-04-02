package io.hari.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
@Entity
public class CabLock extends BaseEntity{
    Long cabId;
    @Enumerated(EnumType.STRING)
    CabLockStatus lockStatus;
    LocalDateTime lockTime;
    Long timeout;

    @JsonIgnore
    public synchronized boolean isLockTimeout() {
        final Duration between = Duration.between(lockTime, LocalDateTime.now());
        if (between.getSeconds() >= timeout) return true;
        return false;
    }
}
