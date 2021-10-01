package com.epam.tc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LabelDto {
    private String id;
    private String idBoard;
    private String name;
    private String color;
}
