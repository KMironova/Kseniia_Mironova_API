package com.epam.tc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ListDto {

    private String id;
    private String idBoard;
    private String name;
    private String color;
    private String limits;
}
