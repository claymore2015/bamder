package com.claymore.bamder.cybersecurity.assets.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author li.zhuo
 * @create 2018/12/9 5:25 PM
 * @since 1.0.0
 */
@ApiModel
@Data
public class ImportantAssetsResponse {
    @ApiModelProperty("重要资产类型ID")
    private int importantAssetsType;
    @ApiModelProperty("重要资产类型名")
    private String importantAssetTypeName;
    @ApiModelProperty("重要资产名称")
    private String importantAssetName;
}
