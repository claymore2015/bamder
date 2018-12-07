package com.claymore.bamder.cybersecurity.assets.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author li.zhuo
 * @create 2018/12/2 5:26 PM
 * @since 1.0.0
 */
@ApiModel
@Data
public class AssetsDistrictResponse {
    @ApiModelProperty("区域Id")
    private String distrcitId;
    private int assertType1;
    private int assertType2;
    private int assertType3;
    private int assertType4;
    private int assertType5;
}
