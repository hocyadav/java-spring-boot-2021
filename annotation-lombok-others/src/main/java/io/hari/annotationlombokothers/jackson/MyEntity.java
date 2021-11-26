package io.hari.annotationlombokothers.jackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * https://dzone.com/articles/jackson-property-custom-naming-strategy
 */
@Data @Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)//{"my_name":"hariom"}//*
//@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)//{"my-name":"hariom"}
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)//{"myname":"hariom"}
//@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)//{"myName":"hariom"} //* i think this is default one
//@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)//{"MyName":"hariom"}
//if no JsonNaming: output {"myName":"hariom"}
//if JsonNaming : output {"my_name":"hariom"}
public class MyEntity {

    //    @JsonProperty("field_name_override") //this will override above JsonNaming, it will always print field_name_override
    String myName;

    String emailId;
}
//advance explore
// https://www.baeldung.com/jackson-advanced-annotations
