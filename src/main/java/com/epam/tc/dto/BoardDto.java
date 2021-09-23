package com.epam.tc.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BoardDto {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("descData")
    @Expose
    public String descData;
    @SerializedName("closed")
    @Expose
    public String closed;
    @SerializedName("idOrganization")
    @Expose
    public String idOrganization;
    @SerializedName("pinned")
    @Expose
    public String pinned;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("shortUrl")
    @Expose
    public String shortUrl;
}
