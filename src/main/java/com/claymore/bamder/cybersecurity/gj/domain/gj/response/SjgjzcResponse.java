package com.claymore.bamder.cybersecurity.gj.domain.gj.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author li.zhuo
 * @create 2018/10/29 下午11:04
 * @since 1.0.0
 * 时间以及告警处置数量
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class SjgjzcResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "时间")
    private Date sj;
    @ApiModelProperty(value = "告警处置数量")
    private Long czNumber;
}
