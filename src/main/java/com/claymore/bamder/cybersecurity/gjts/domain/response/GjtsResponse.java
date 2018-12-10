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
    private int prosNum;
    @ApiModelProperty(value = "木马个数")
    private int trojanNum;
    @ApiModelProperty(value = "漏洞个数")
    private int bugNum;
    @ApiModelProperty(value = "确定泄密事件")
    private long xmEventNum;


    private List<GjSoureResponse> gjSoureResponses;
    private List<GjIpResponse> gjIpResponses;
    private List<TargetIpResponse> targetIpResponses;

    @ApiModelProperty("无风险")
    private long level1;
    @ApiModelProperty("一般级")
    private long level2;
    @ApiModelProperty("关注级")
    private long level3;
    @ApiModelProperty("严重级")
    private long level4;
    @ApiModelProperty("紧急级")
    private long level5;


    private List<GjActionResponse> gjActionResponses;
    private List<EyiProsResponse> eyiProsResponses;
    private List<UnknownDistributionResponse> unknownDistributionResponses;
    private List<TimeTrendResponse> timeTrendResponses;

}
