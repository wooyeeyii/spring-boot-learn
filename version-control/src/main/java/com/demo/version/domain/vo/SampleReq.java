package com.demo.version.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "请求样本", description = "请求样本")
public class SampleReq {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "名字")
    private String name;

}
