package com.claymore.bamder.cybersecurity.gjts.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/2 10:52 PM
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@ApiModel
public class GjtsResponse {
    @ApiModelProperty(value = "攻击态势")
    private Long gjtsTotal;
    @ApiModelProperty(value = "恶意程序感染")
    private Long prosTotal;
    @ApiModelProperty(value = "木马攻击总量")
    private Long trojanTotal;
    @ApiModelProperty(value = "漏洞利用总量")
    private Long bugTotal;
    @ApiModelProperty(value = "未知攻击总量")
    private Long unknownTotal;

    @ApiModelProperty(value = "恶意程序个数")
    private Long prosNum;
    @ApiModelProperty(value = "木马个数")
    private Long trojanNum;
    @ApiModelProperty(value = "漏洞个数")
    private Long bugNum;
    @ApiModelProperty(value = "确定泄密事件")
    private Long xmEventNum;


    private List<GjSoureResponse> gjSoureResponses;
    private List<GjIpResponse> gjIpResponses;
    private List<TargetIpResponse> targetIpResponses;
    //TODO traget area
    //TODO 图上数据

    @ApiModelProperty("无风险")
    private Long level1;
    @ApiModelProperty("一般级")
    private Long level2;
    @ApiModelProperty("关注级")
    private Long level3;
    @ApiModelProperty("严重级")
    private Long level4;
    @ApiModelProperty("紧急级")
    private Long level5;


    private List<GjActionResponse> gjActionResponses;
    private List<EyiProsResponse> eyiProsResponses;
    private List<UnknownDistributionResponse> unknownDistributionResponses;
    private List<TimeTrendResponse> timeTrendResponses;

}
