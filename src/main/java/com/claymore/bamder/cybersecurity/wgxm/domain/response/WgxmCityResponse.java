package com.claymore.bamder.cybersecurity.wgxm.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author li.zhuo
 * @create 2018/12/2 3:39 PM
 * @since 1.0.0
 */
@ApiModel
@Data
public class WgxmCityResponse {
    @ApiModelProperty("城市ID")
    private String cityId;
    @ApiModelProperty("违规行为数量")
    private long wgxwNumber;
    @ApiModelProperty("确定泄密数量")
    private long xmsjNumber;
    @ApiModelProperty("涉密终端")
    private boolean smzd;
    @ApiModelProperty("办公终端")
    private boolean bgzd;
    @ApiModelProperty("非密数据库")
    private boolean fmsjk;

    @ApiModelProperty("门户网站")
    private boolean mhwz;
    @ApiModelProperty("http")
    private boolean http;
    @ApiModelProperty("即时通信")
    private boolean jstx;
    @ApiModelProperty("网盘")
    private boolean wp;
    @ApiModelProperty("邮件")
    private boolean yj;
    @ApiModelProperty("ftp")
    private boolean ftp;
    @ApiModelProperty("其他")
    private boolean qt;

}
