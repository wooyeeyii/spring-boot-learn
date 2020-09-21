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
@Schema(name = "回复样本", description = "样本")
public class SampleRsp {

    @Schema(description = "名字")
    private String name;

    @Schema(description = "性别")
    private Integer sex;
}
