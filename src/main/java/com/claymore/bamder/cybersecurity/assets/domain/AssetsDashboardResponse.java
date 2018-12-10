package com.claymore.bamder.cybersecurity.assets.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/3 8:51 AM
 * @since 1.0.0
 */
@ApiModel
@Data
public class AssetsDashboardResponse {
    private List<AssetsSurvayResponse> assetsSurvayResponses;
    private List<AssetUnitResponse> assertUnitResponses;
    private List<AssetsAccountResponse> assetsAccountResponses;
    private List<AssetDataResponse> assetDataResponses;
    private List<AssetsFileResponse> assetsFileResponses;
    private List<ImportantAssetsResponse> importantAssetsResponses;
}
