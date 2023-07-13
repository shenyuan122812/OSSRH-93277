package org.jtar.assertion;

import lombok.Data;

@Data
public class AssertionDto {

    private  String assertionField;

    private  String jsonPath;
    private  String type;

    private  Object value;
    private  String assertionType;
}
