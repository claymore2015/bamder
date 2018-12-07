package com.claymore.bamder.cybersecurity.assets.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.common.domain.CyberRequest;
import com.claymore.bamder.common.domain.PageResult;
import com.claymore.bamder.cybersecurity.assets.domain.AssetsCityResponse;
import com.claymore.bamder.cybersecurity.assets.domain.AssetsDashboardResponse;
import com.claymore.bamder.cybersecurity.assets.domain.AssetsDistrictResponse;
import com.claymore.bamder.cybersecurity.assets.entity.*;
import com.claymore.bamder.cybersecurity.assets.repository.*;
import com.claymore.bamder.cybersecurity.assets.support.AssetsSupport;
import com.claymore.bamder.cybersecurity.dict.constant.DictConstant;
import com.claymore.bamder.cybersecurity.dict.entity.DictEntity;
import com.claymore.bamder.cybersecurity.dict.repository.DictRepository;
import com.claymore.bamder.cybersecurity.unit.entity.UnitsEntity;
import com.claymore.bamder.cybersecurity.unit.repository.UnitsRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/3 9:20 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private AssetsSurvayRepository assetsSurvayRepository;

    @Autowired
    private AssetsUnitRepository assetsUnitRepository;

    @Autowired
    private AssetsDataRepository assetsDataRepository;

    @Autowired
    private AssetsAccountRepository assetsAccountRepository;

    @Autowired
    private AssetsFileRepository assetsFileRepository;

    @Autowired
    private UnitsRepository unitsRepository;

    @Autowired
    private DictRepository dictRepository;

    @PostMapping("/dashboard")
    @ApiOperation(value = "资产分布dashboard页所有数据", produces = "POST", response = AssetsDashboardResponse.class)
    public BaseResponse<AssetsDashboardResponse> dashboard(@RequestBody CyberRequest request){
        AssetsDashboardResponse  response = new AssetsDashboardResponse();

        //query survey
        List<AssetsSurvayEntity> assetsSurveyEntities = assetsSurvayRepository.findByProvinceAndCityAndDistrict(request.getProvinceId(), request.getCityId(), request.getDistrictId());
        List<DictEntity> assetsTypes = dictRepository.findByKey(DictConstant.ASSETS_TYPE);
        //assemble
        response.setAssetsSurvayResponses(AssetsSupport.transfer2SurvayResponseList(assetsSurveyEntities, assetsTypes));

        //query unit
        List<AssetsUnitEntity> assetsUnits = assetsUnitRepository.findByProvinceAndCityAndDistrict(request.getProvinceId(), request.getCityId(), request.getDistrictId());
        List<UnitsEntity> units = unitsRepository.findAll();
        //assemble
        response.setAssertUnitResponses(AssetsSupport.transfer2UnitResponseList(assetsUnits, units));


        //query account
        List<AssetsAccountEntity> assetAccounts = assetsAccountRepository.findByProvinceAndCityAndDistrict(request.getProvinceId(), request.getCityId(), request.getDistrictId());
        int totalAccount = assetAccounts.stream().map(AssetsAccountEntity::getCount).reduce(0, Integer::sum).intValue();
        List<DictEntity> assetsAccountTypes = dictRepository.findByKey(DictConstant.ASSETS_ACCOUNT_TYPE);
        //assemble
        response.setAssetsAccountResponses(AssetsSupport.transfer2AccountResponseList(assetAccounts, totalAccount, assetsAccountTypes));


        //query Data
        List<AssetsDataEntity> assetsData = assetsDataRepository.findByProvinceAndCityAndDistrict(request.getProvinceId(), request.getCityId(), request.getDistrictId());
        List<DictEntity> assetsDataTypes = dictRepository.findByKey(DictConstant.ASSETS_DATA_TYPE);
        //assemble
        response.setAssetDataResponses(AssetsSupport.transfer2DataResponseList(assetsData, assetsDataTypes));


        //query file
        List<AssetsFileEntity> assetsFiles = assetsFileRepository.findByProvinceAndCityAndDistrict(request.getProvinceId(), request.getCityId(), request.getDistrictId());
        int totalFiles = assetsFiles.stream().map(AssetsFileEntity::getCount).reduce(0, Integer::sum).intValue();
        List<DictEntity> assetsFileTypes = dictRepository.findByKey(DictConstant.ASSETS_FILE_TYPE);
        //assemble
        response.setAssetsFileResponses(AssetsSupport.transfer2FileResponseList(assetsFiles, totalFiles, assetsFileTypes));


        return new BaseResponse<>(response) ;
    }


    @Autowired
    private AssetsRepository assetsRepository;

    @GetMapping("/{id}")
    @ApiOperation(value = "query assets type with id", consumes = "GET", response = AssetsTypeEntity.class)
    public BaseResponse<AssetsEntity> findById(@PathVariable("id") Long id) {
        return new BaseResponse<>(assetsRepository.findById(id).get());
    }

    @PostMapping
    public BaseResponse create(@RequestBody AssetsEntity assetsEntity) {
        assetsRepository.save(assetsEntity);
        return BaseResponse.instance();
    }

    @PutMapping
    public BaseResponse update(@RequestBody AssetsEntity updatedAssets) {
        assetsRepository.save(updatedAssets);
        return BaseResponse.instance();
    }

    @GetMapping("/{pageSize}/{pageNo}")
    public BaseResponse<PageResult<AssetsEntity>> page(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo) {
        Page<AssetsEntity> page = assetsRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
        PageResult<AssetsEntity> result = new PageResult<AssetsEntity>();
        result.setContent(page.getContent());
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setTotalRecords(page.getTotalElements());
        return new BaseResponse(result);
    }

    @GetMapping("/type")
    @ApiOperation("查询资产类型")
    public BaseResponse<List<DictEntity>> assetsType() {
        List<DictEntity> assetsTypes = dictRepository.findByKey(DictConstant.ASSETS_TYPE);
        return new BaseResponse<>(assetsTypes);
    }

    @PostMapping("/city")
    @ApiOperation("地图根据省查询各个市的资产数量")
    public BaseResponse<List<AssetsCityResponse>> cityMap(@RequestBody CyberRequest cyberRequest) {
        List<AssetsSurvayEntity> surveyEntities = assetsSurvayRepository.findByProvinceAndCityAndDistrict(cyberRequest.getProvinceId(), null
                , null);
        return new BaseResponse<>(AssetsSupport.transfer2CityResponseList(surveyEntities));
    }

    @PostMapping("/district")
    @ApiOperation("地图根据省查询各个市的资产数量")
    public BaseResponse<List<AssetsDistrictResponse>> districtMap(@RequestBody CyberRequest cyberRequest) {
        List<AssetsSurvayEntity> surveyEntities = assetsSurvayRepository.findByProvinceAndCityAndDistrict(cyberRequest.getProvinceId(), cyberRequest.getCityId()
                , null);
        return new BaseResponse<>(AssetsSupport.transfer2DistrictResponseList(surveyEntities));
    }

}
