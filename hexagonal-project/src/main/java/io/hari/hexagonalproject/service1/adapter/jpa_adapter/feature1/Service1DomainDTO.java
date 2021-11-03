package io.hari.hexagonalproject.service1.adapter.jpa_adapter.feature1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Service1Domain")//domain name not the dto name
public class Service1DomainDTO {//rule 1st create DTO -> then Repo -> then Adapter (call Repo or Service layer)
}
