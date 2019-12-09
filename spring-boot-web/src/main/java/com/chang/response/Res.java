package com.chang.response;

import com.chang.config.InternationalizationSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
//@JsonSerialize(using = ResSerializer.class)
public class Res {

    @JsonSerialize(using = InternationalizationSerializer.class)
    private OrderBsEnum orderBs;

    private OrderBsEnum orderBsOri;

    @JsonProperty(value = "deeeeeee")
    private BigDecimal de;

    private String flag;

}
