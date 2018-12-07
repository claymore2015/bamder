package com.claymore.bamder.cybersecurity.assets.support;

import com.claymore.bamder.common.util.PercentUtil;
import com.claymore.bamder.cybersecurity.assets.domain.*;
import com.claymore.bamder.cybersecurity.assets.entity.*;
import com.claymore.bamder.cybersecurity.dict.entity.DictEntity;
import com.claymore.bamder.cybersecurity.unit.entity.UnitsEntity;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author li.zhuo
 * @create 2018/11/24 3:01 PM
 * @since 1.0.0
 */
public class AssetsSupport {

    public static List<AssetsSurvayResponse> transfer2SurvayResponseList(List<AssetsSurvayEntity> survayEntities, List<DictEntity> assetsTypes) {
        List<AssetsSurvayResponse> responseList = Lists.newArrayList();
        Map<Integer, List<AssetsSurvayEntity>> collect = survayEntities.stream().collect(Collectors.groupingBy(AssetsSurvayEntity::getAssetsType));
        collect.entrySet().forEach(e -> {
            AssetsSurvayResponse response = new AssetsSurvayResponse();
            response.setAssetsTypeId(e.getKey());
            response.setAmount(e.getValue().stream().map(a -> a.getCount()).reduce(Integer::sum).orElse(0));
            response.setRunningAmount(e.getValue().stream().map(a -> a.getCountS1()).reduce(Integer::sum).orElse(0));
            response.setStopAmount(e.getValue().stream().map(a -> a.getCountS2()).reduce(Integer::sum).orElse(0));
            fillAssetsTypeName(response, assetsTypes);
            responseList.add(response);
        });
        return responseList;
    }

    /*public static AssetsSurvayResponse transfer2SurvayResponse(AssetsSurvayEntity survayEntitiy) {
        AssetsSurvayResponse response = new AssetsSurvayResponse();
        response.setAmount(survayEntitiy.getCount());
        response.setAssetsTypeId(survayEntitiy.getAssetsType());
        response.setRunningAmount(survayEntitiy.getCountS1());
        response.setStopAmount(survayEntitiy.getCountS2());
        return response;
    }*/

    public static AssetsSurvayResponse fillAssetsTypeName(AssetsSurvayResponse response, List<DictEntity> assetsTypes) {
        Optional<DictEntity> any = assetsTypes.stream().filter(assetType -> assetType.getValue().equalsIgnoreCase(String.valueOf(response.getAssetsTypeId()))).findAny();
        if (any.isPresent()) {
            response.setAssetsTypeName(any.get().getDesc());
        }
        return response;
    }

    public static AssetDataResponse fillAssetDataTypeName(AssetDataResponse response, List<DictEntity> assetsTypes) {
        Optional<DictEntity> any = assetsTypes.stream().filter(assetType -> assetType.getValue().equalsIgnoreCase(String.valueOf(response.getAssetDataTypeId()))).findAny();
        if (any.isPresent()) {
            response.setAssetDataTypeName(any.get().getDesc());
        }
        return response;
    }

    public static AssetsAccountResponse fillAssetAccountTypeName(AssetsAccountResponse response, List<DictEntity> assetsTypes) {
        Optional<DictEntity> any = assetsTypes.stream().filter(assetType -> assetType.getValue().equalsIgnoreCase(String.valueOf(response.getAccountTypeId()))).findAny();
        if (any.isPresent()) {
            response.setAccountName(any.get().getDesc());
        }
        return response;
    }

    public static AssetsFileResponse fillAssetFileTypeName(AssetsFileResponse response, List<DictEntity> assetsTypes) {
        Optional<DictEntity> any = assetsTypes.stream().filter(assetType -> assetType.getValue().equalsIgnoreCase(String.valueOf(response.getFileTypeId()))).findAny();
        if (any.isPresent()) {
            response.setFileTypeName(any.get().getDesc());
        }
        return response;
    }

    public static List<AssetUnitResponse> transfer2UnitResponseList(List<AssetsUnitEntity> assetUnitEntities, List<UnitsEntity> unitsEntities) {
        List<AssetUnitResponse> responses = Lists.newArrayList();
        Map<String, List<AssetsUnitEntity>> unitGroup = assetUnitEntities.stream().collect(groupingBy(AssetsUnitEntity::getUnit));
        unitGroup.forEach((s, unitEntityList) -> {
            AssetUnitResponse response = new AssetUnitResponse();
            response.setUnitId(s);
            unitEntityList.forEach(assetUnitEntity -> {
                if (assetUnitEntity.getAssetsType() == 1) {
                    response.setAssetsType1((long) assetUnitEntity.getCount());
                } else if (assetUnitEntity.getAssetsType() == 2) {
                    response.setAssetsType2((long) assetUnitEntity.getCount());
                } else if (assetUnitEntity.getAssetsType() == 3) {
                    response.setAssetsType3((long) assetUnitEntity.getCount());
                } else if (assetUnitEntity.getAssetsType() == 4) {
                    response.setAssetsType4((long) assetUnitEntity.getCount());
                } else if (assetUnitEntity.getAssetsType() == 5) {
                    response.setAssetsType5((long) assetUnitEntity.getCount());
                }
                //response.setUnitName();
                Optional<UnitsEntity> first = unitsEntities.stream().filter(unit -> unit.getUnitId() == Long.valueOf(s)).findFirst();
                if (first.isPresent()) {
                    response.setUnitName(first.get().getUnitName());
                }
                responses.add(response);
            });
        });
        return responses;
    }

    public static List<AssetDataResponse> transfer2DataResponseList(List<AssetsDataEntity> assetsDataEntities, List<DictEntity> assetsDataTypes) {
        /*List<AssetDataResponse> responseList = Lists.newArrayList();
        Map<Integer, List<AssetsDataEntity>> collect = assetsDataEntities.stream().collect(Collectors.groupingBy(AssetsDataEntity::getAssetsDataType));
        collect.entrySet().forEach(e -> {
            AssetDataResponse response = new AssetDataResponse();
            response.setAssetDataTypeId(e.getKey());
            response.set
            responseList.add(response);
        });*/
        return  assetsDataEntities.stream().map(AssetsSupport::transfer2DataResponse).map(assetDataResponse -> fillAssetDataTypeName(assetDataResponse, assetsDataTypes)).collect(Collectors.toList());
    }

    public static AssetDataResponse transfer2DataResponse(AssetsDataEntity dataEntity) {
        AssetDataResponse response = new AssetDataResponse();
        response.setAssetDataTypeId(dataEntity.getAssetsDataType());
        response.setCumulateCount(dataEntity.getCumulateCount().doubleValue());
        response.setWeekAvgCount(dataEntity.getWeekAvgCount().doubleValue());
        response.setWeekCount(dataEntity.getWeekCount().doubleValue());
        return response;
    }

    public static List<AssetsAccountResponse> transfer2AccountResponseList(List<AssetsAccountEntity> accountEntities, int totalCount, List<DictEntity> accountsType) {
        List<AssetsAccountResponse> responseList = Lists.newArrayList();
        Map<Integer, List<AssetsAccountEntity>> collect = accountEntities.stream().collect(Collectors.groupingBy(AssetsAccountEntity::getAccountType));
        collect.entrySet().forEach(e -> {
            AssetsAccountResponse response = new AssetsAccountResponse();
            response.setAccountTypeId(e.getKey());
            response.setAmount(e.getValue().stream().map(a -> a.getCount()).reduce(Integer::sum).orElse(0));
            fillAssetAccountTypeName(response, accountsType);
            response.setPercent(PercentUtil.longDivideFormat((long)response.getAmount(), (long)totalCount));
            responseList.add(response);
        });
        return responseList;
    }

/*    public static AssetsAccountResponse transfer2AccountResponse(AssetsAccountEntity accountEntity, int totalCount) {
        AssetsAccountResponse response = new AssetsAccountResponse();
        response.setAccountTypeId(accountEntity.getAccountType());
        response.setAmount((long) accountEntity.getCount());
        response.setPercent(PercentUtil.format(new BigDecimal(accountEntity.getCount()).divide(new BigDecimal(totalCount), 2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        return response;
    }*/

    public static List<AssetsFileResponse> transfer2FileResponseList(List<AssetsFileEntity> fileEntities, int totalCount, List<DictEntity> filesType) {
        return fileEntities.stream().map(entity -> tansfer2FileReponse(entity, totalCount)).map(assetsFileResponse -> fillAssetFileTypeName(assetsFileResponse, filesType)).collect(Collectors.toList());
    }

    public static AssetsFileResponse tansfer2FileReponse(AssetsFileEntity entity, int totalCount) {
        AssetsFileResponse response = new AssetsFileResponse();
        response.setCount((long) entity.getCount());
        response.setFileTypeId(entity.getFileType());
        response.setPercent(PercentUtil.format(new BigDecimal(entity.getCount()).divide(new BigDecimal(totalCount), 2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        return response;
    }


    public static List<AssetsCityResponse> transfer2CityResponseList(List<AssetsSurvayEntity> surveyEntities) {
        Map<String, List<AssetsSurvayEntity>> collect = surveyEntities.stream().collect(Collectors.groupingBy(AssetsSurvayEntity::getCity));
        return collect.entrySet().stream().map(e -> transfer2AssetsCityResponse(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    public static AssetsCityResponse transfer2AssetsCityResponse(String city, List<AssetsSurvayEntity> entities) {
        AssetsCityResponse response = new AssetsCityResponse();
        response.setCityId(city);
        response.setAssertType1(entities.stream().filter(e -> e.getAssetsType() == 1).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType2(entities.stream().filter(e -> e.getAssetsType() == 2).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType3(entities.stream().filter(e -> e.getAssetsType() == 3).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType4(entities.stream().filter(e -> e.getAssetsType() == 4).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType5(entities.stream().filter(e -> e.getAssetsType() == 5).map(e -> e.getCount()).reduce(0, Integer::sum));
        return response;
    }



    public static List<AssetsDistrictResponse> transfer2DistrictResponseList(List<AssetsSurvayEntity> surveyEntities) {
        Map<String, List<AssetsSurvayEntity>> collect = surveyEntities.stream().collect(Collectors.groupingBy(AssetsSurvayEntity::getDistrict));
        return collect.entrySet().stream().map(e -> transfer2AssetsDistrictResponse(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    public static AssetsDistrictResponse transfer2AssetsDistrictResponse(String district, List<AssetsSurvayEntity> entities) {
        AssetsDistrictResponse response = new AssetsDistrictResponse();
        response.setDistrcitId(district);
        response.setAssertType1(entities.stream().filter(e -> e.getAssetsType() == 1).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType2(entities.stream().filter(e -> e.getAssetsType() == 2).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType3(entities.stream().filter(e -> e.getAssetsType() == 3).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType4(entities.stream().filter(e -> e.getAssetsType() == 4).map(e -> e.getCount()).reduce(0, Integer::sum));
        response.setAssertType5(entities.stream().filter(e -> e.getAssetsType() == 5).map(e -> e.getCount()).reduce(0, Integer::sum));
        return response;
    }
}
