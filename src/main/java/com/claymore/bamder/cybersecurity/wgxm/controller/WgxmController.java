package com.claymore.bamder.cybersecurity.wgxm.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiWgxwEntity;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiWgxwRepository;
import com.claymore.bamder.cybersecurity.wgxm.domain.request.TimeTrendRequest;
import com.claymore.bamder.cybersecurity.wgxm.domain.request.WgxmRequest;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxmCityResponse;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxmResponse;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxmTimeTrendResponse;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxwEventResponse;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwEntity;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwTop10Entity;
import com.claymore.bamder.cybersecurity.wgxm.repository.AlarmWgxwRepository;
import com.claymore.bamder.cybersecurity.wgxm.repository.AlarmWgxwTop10Repository;
import com.claymore.bamder.cybersecurity.wgxm.support.AlarmWgxwSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/28 10:34 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/wgxm")
@Api
public class WgxmController {

   @Autowired
   private AlarmWgxwRepository alarmWgxwRepository;

   @Autowired
   private AlarmWgxwTop10Repository alarmWgxwTop10Repository;

   @Autowired
   private ChuzhiWgxwRepository wgxwRepository;

   @PostMapping("/dashboard")
   @ApiOperation(value = "违规泄密态势dashboard")
   public BaseResponse<WgxmResponse> dashboard(WgxmRequest request) {
       Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
       List<AlarmWgxwEntity> alarmWgxwEntities = alarmWgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
       List<AlarmWgxwTop10Entity> top10Entities = alarmWgxwTop10Repository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
       return new BaseResponse(AlarmWgxwSupport.transfer2WgxmResponse(alarmWgxwEntities, top10Entities));
   }

   @PostMapping("/trend")
   @ApiOperation(value = "违规泄密态势时间趋势")
   public BaseResponse<List<WgxmTimeTrendResponse>> timeTrend(TimeTrendRequest request) {
       List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictBetweenDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), request.getStartTime(), request.getEndTime());
       return new BaseResponse<>(AlarmWgxwSupport.transfer2TimeTrendResponse(wgxwEntities));
   }

   @PostMapping("/event")
   @ApiOperation(value = "违规泄密态势事件")
   public BaseResponse<WgxwEventResponse> event(WgxmRequest request) {
       Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
       List<ChuzhiWgxwEntity> wgxwEntities = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), request.getCityId(), request.getDistrictId(), staticDate);
       //TODO NEED FIX, 违规泄密态势的事件统计
       return null;
   }

   @PostMapping("/city")
   @ApiOperation(value = "地图城市数据")
   public BaseResponse<List<WgxmCityResponse>> cityMap(WgxmRequest request) {
       Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
       List<ChuzhiWgxwEntity> chuzhi = wgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
       List<AlarmWgxwEntity> wgxwEntities = alarmWgxwRepository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
       List<AlarmWgxwTop10Entity> wgxwTop10Entities = alarmWgxwTop10Repository.findByProvinceAndCityAndDistrictAndDay(request.getProvinceId(), null, null, staticDate);
       return null;
   }

}
