package com.claymore.bamder.cybersecurity.gj.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.cybersecurity.event.entity.EventsInfoEntity;
import com.claymore.bamder.cybersecurity.event.repository.EventsInfoRepository;
import com.claymore.bamder.cybersecurity.event.support.EventSupport;
import com.claymore.bamder.cybersecurity.gj.domain.district.GjCityResponse;
import com.claymore.bamder.cybersecurity.gj.domain.district.GjDistrictResponse;
import com.claymore.bamder.cybersecurity.gj.domain.event.request.EventRequest;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.EventResponse;
import com.claymore.bamder.cybersecurity.gj.domain.gj.request.GjStatisticsRequest;
import com.claymore.bamder.cybersecurity.gj.domain.gj.request.SjgjczRequest;
import com.claymore.bamder.cybersecurity.gj.domain.gj.response.GjStatisticsResponse;
import com.claymore.bamder.cybersecurity.gj.domain.gj.response.SjgjczListResponse;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiGjqmEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiMbsjEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiTxzdEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiWgxwEntity;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiGjqmRepository;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiMbsjRepository;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiTxzdRepository;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiWgxwRepository;
import com.claymore.bamder.cybersecurity.gj.support.GjEventSupport;
import com.claymore.bamder.cybersecurity.gj.support.GjSupport;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author li.zhuo
 * @create 2018/10/29 下午11:28
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/gj/dashboard")
@Api
public class GjDashboardController {

    @Autowired
    private ChuzhiWgxwRepository wgxwRepository;

    @Autowired
    private ChuzhiGjqmRepository gjqmRepository;

    @Autowired
    private ChuzhiTxzdRepository txzdRepository;

    @Autowired
    private ChuzhiMbsjRepository mbsjRepository;

    @Autowired
    private EventsInfoRepository eventsInfoRepository;


    @PostMapping(value = "/statistic")
    @ApiOperation(value = "分别返回告警统计，包括了告警总量、告警级别分布、告警处置概况，告警处置率五个部分数据", produces = "POST",
    response = GjStatisticsResponse.class)
    public BaseResponse<GjStatisticsResponse> gjStatistic(@RequestBody @Valid GjStatisticsRequest request) {
        GjStatisticsResponse response = new GjStatisticsResponse();

        //query
        Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
        List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiTxzdEntity> txzdEntities = txzdRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiMbsjEntity> mbsjEntities = mbsjRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);

        response.setGjzlResponse(GjSupport.transfer2GjzlResponse(wgxwEntities, gjqmEntities, txzdEntities, mbsjEntities));
        response.setGjjbfbResponse(GjSupport.transfer2GjjbfbResponse(wgxwEntities, gjqmEntities, txzdEntities, mbsjEntities));
        response.setGjczlResponse(GjSupport.transfer2GjczlResponse(wgxwEntities, gjqmEntities, txzdEntities, mbsjEntities, response.getGjzlResponse()));

        return new BaseResponse<>(response);
    }


    @PostMapping(value = "/timeTrend")
    @ApiOperation(value = "基于事件到告警处置情况", produces = "POST")
    public BaseResponse<SjgjczListResponse> sjgjcz(@RequestBody @Valid SjgjczRequest request) {

        List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictBetweenDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), request.getStartDate(), request.getEndDate());
        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByProvinceAndCityAndDistrictBetweenDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), request.getStartDate(), request.getEndDate());
        List<ChuzhiTxzdEntity> txzdEntities = txzdRepository.findByProvinceAndCityAndDistrictBetweenDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), request.getStartDate(), request.getEndDate());
        List<ChuzhiMbsjEntity> mbsjEntities = mbsjRepository.findByProvinceAndCityAndDistrictBetweenDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), request.getStartDate(), request.getEndDate());

        SjgjczListResponse responseList = GjSupport.transfer2SjgjczListResponse(wgxwEntities, gjqmEntities, txzdEntities, mbsjEntities);

        return new BaseResponse<>(responseList);
    }


    @PostMapping(value = "/event")
    @ApiOperation(value = "事件信息，包括事件数量，转换关系，事件时间轴", produces = "POST")
    public BaseResponse<EventResponse> event(@RequestBody @Valid EventRequest request){
        EventResponse response = new EventResponse();

        Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
        List<EventsInfoEntity> eventsInfo = eventsInfoRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiTxzdEntity> txzdEntities = txzdRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
        List<ChuzhiMbsjEntity> mbsjEntities = mbsjRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);

        response.setPoints(GjEventSupport.generateEventPoints());
        response.setWgxwTransfer(GjEventSupport.transferToWgwxPoint(eventsInfo, wgxwEntities));
        response.setGjqmTransfer(GjEventSupport.transferToGjqmPoint(eventsInfo, gjqmEntities));
        response.setTxzdTransfer(GjEventSupport.transferToTxzdPoint(eventsInfo, txzdEntities));
        response.setWgxwTransfer(GjEventSupport.transferToMbsjPoint(eventsInfo, mbsjEntities));
        response.setTotalTransfer(GjEventSupport.transferToTotalPoint(response.getWgxwTransfer(), response.getMbsjTransfer(), response.getTxzdTransfer(), response.getGjqmTransfer()));
        response.setEvents(eventsInfo.stream().map(EventSupport::transfer2Event).collect(Collectors.toList()));
        return new BaseResponse<>(response);
    }

    @PostMapping(value = "/district")
    @ApiOperation(value = "获取所有区的处置数据", produces = "POST")
    public BaseResponse<List<GjDistrictResponse>> district(@RequestBody @Valid GjStatisticsRequest request) {
        List<GjDistrictResponse> responses = Lists.newArrayList();

        Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
        List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), null, staticDate);

        Map<String, List<ChuzhiWgxwEntity>> wgxmMap = wgxwEntities.stream().collect(Collectors.groupingBy(ChuzhiWgxwEntity::getDistrict));
        List<GjDistrictResponse> cityWgxw = wgxmMap.entrySet().stream().map(entry -> GjSupport.wgxwMapToDistrict(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), null, staticDate);
        Map<String, List<ChuzhiGjqmEntity>> gjqmMap = gjqmEntities.stream().collect(Collectors.groupingBy(ChuzhiGjqmEntity::getDistrict));
        List<GjDistrictResponse> cityGjqm = gjqmMap.entrySet().stream().map(entry -> GjSupport.gjqmMapToDistrict(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiTxzdEntity> txzdEntities = txzdRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), null, staticDate);
        Map<String, List<ChuzhiTxzdEntity>> txzdMap = txzdEntities.stream().collect(Collectors.groupingBy(ChuzhiTxzdEntity::getDistrict));
        List<GjDistrictResponse> cityTxzd = txzdMap.entrySet().stream().map(entry -> GjSupport.txzdMapToDistrict(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiMbsjEntity> mbsjEntities = mbsjRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), null, staticDate);
        Map<String, List<ChuzhiMbsjEntity>> mbsjMap = mbsjEntities.stream().collect(Collectors.groupingBy(ChuzhiMbsjEntity::getDistrict));
        List<GjDistrictResponse> cityMbsj = mbsjMap.entrySet().stream().map(entry -> GjSupport.mbsjMapToDistrict(entry.getKey(), entry.getValue())).collect(Collectors.toList());


        List<GjDistrictResponse> gjCityRespons = GjSupport.mergeDistrict(cityWgxw, cityGjqm, cityTxzd, cityMbsj);
        return new BaseResponse<>(gjCityRespons);
    }

    @PostMapping(value = "/city")
    @ApiOperation(value = "获取所有市的处置数据", produces = "POST")
    public BaseResponse<List<GjCityResponse>> city(@RequestBody @Valid GjStatisticsRequest request) {
        List<GjCityResponse> responses = Lists.newArrayList();
        Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
        List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
        Map<String, List<ChuzhiWgxwEntity>> wgxmMap = wgxwEntities.stream().collect(Collectors.groupingBy(ChuzhiWgxwEntity::getCity));
        List<GjCityResponse> cityWgxw = wgxmMap.entrySet().stream().map(entry -> GjSupport.wgxwMapToCity(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
        Map<String, List<ChuzhiGjqmEntity>> gjqmMap = gjqmEntities.stream().collect(Collectors.groupingBy(ChuzhiGjqmEntity::getCity));
        List<GjCityResponse> cityGjqm = gjqmMap.entrySet().stream().map(entry -> GjSupport.gjqmMapToCity(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiTxzdEntity> txzdEntities = txzdRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
        Map<String, List<ChuzhiTxzdEntity>> txzdMap = txzdEntities.stream().collect(Collectors.groupingBy(ChuzhiTxzdEntity::getCity));
        List<GjCityResponse> cityTxzd = txzdMap.entrySet().stream().map(entry -> GjSupport.txzdMapToCity(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<ChuzhiMbsjEntity> mbsjEntities = mbsjRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
        Map<String, List<ChuzhiMbsjEntity>> mbsjMap = mbsjEntities.stream().collect(Collectors.groupingBy(ChuzhiMbsjEntity::getCity));
        List<GjCityResponse> cityMbsj = mbsjMap.entrySet().stream().map(entry -> GjSupport.mbsjMapToCity(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        List<GjCityResponse> gjCityRespons = GjSupport.mergeCity(cityWgxw, cityGjqm, cityTxzd, cityMbsj);
        return new BaseResponse<>(gjCityRespons);
    }


}
