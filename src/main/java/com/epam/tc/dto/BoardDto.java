package com.epam.tc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class BoardDto {

    public String id;
    public String name;
    public String desc;
    public String descData;
    public String closed;
    public String idOrganization;
    public String pinned;
    public String url;
    public String shortUrl;
}
