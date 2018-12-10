package com.claymore.bamder.cybersecurity.assets.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author li.zhuo
 * @create 2018/11/3 9:03 AM
 * @since 1.0.0
 */
@ApiModel(value = "单位资产")
@Data
public class AssetUnitResponse {
    @ApiModelProperty("单位Id")
    private String unitId;
    @ApiModelProperty("单位名称")
    private String unitName;
    @ApiModelProperty("涉密终端数量")
    private long assetsType1;
    @ApiModelProperty("办公终端数量")
    private long assetsType2;
    @ApiModelProperty("非密数据库数量")
    private long assetsType3;
    @ApiModelProperty("门户网站")
    private long assetsType4;
    @ApiModelProperty("检测器")
    private long assetsType5;
}
