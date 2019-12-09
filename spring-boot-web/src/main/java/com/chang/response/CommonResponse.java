package com.chang.response;

import com.chang.config.InternationalizationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class CommonResponse<T> {

    private int errCode;

    @JsonSerialize(using = InternationalizationSerializer.class)
    private String errMsg;

    private T data;

    public boolean isSuccess() {
        return this.errCode == 0;
    }
}
