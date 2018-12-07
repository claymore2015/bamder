package com.claymore.bamder.cybersecurity.assets.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author li.zhuo
 * @create 2018/11/3 9:09 AM
 * @since 1.0.0
 */
@ApiModel("账号资产")
@Data
public class AssetsAccountResponse {
    @ApiModelProperty(value = "账号类型Id")
    private int accountTypeId;
    @ApiModelProperty("账号类型名")
    private String accountName;
    @ApiModelProperty("数量")
    private int amount;
    @ApiModelProperty("占比")
    private String percent;
}
