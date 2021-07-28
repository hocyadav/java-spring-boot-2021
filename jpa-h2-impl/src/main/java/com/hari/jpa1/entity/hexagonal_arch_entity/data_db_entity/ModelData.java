package com.hari.jpa1.entity.hexagonal_arch_entity.data_db_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "model_table")
public class ModelData {
    @Id @GeneratedValue
    Integer id;

    String name;
}
