package com.claymore.bamder.cybersecurity.gjts.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiGjqmEntity;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiGjqmRepository;
import com.claymore.bamder.cybersecurity.gjts.domain.request.GjtsRequest;
import com.claymore.bamder.cybersecurity.gjts.domain.response.GjtsResponse;
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
import java.util.stream.Collectors;

/**
 * @author li.zhuo
 * @create 2018/11/3 8:30 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping("gjts")
public class GjtsController {

    @Autowired
    private ChuzhiGjqmRepository gjqmRepository;

    @PostMapping("/dashboard")
    @ApiOperation(value = "攻击态势dashboard页面所有数据", produces = "POST", response = GjtsResponse.class)
    public BaseResponse<GjtsResponse> dashboard(@RequestBody @Valid GjtsRequest request) {
        GjtsResponse response = new GjtsResponse();
        Date staticDate = new DateTime(new Date()).minusDays(request.getDays()).withTimeAtStartOfDay().toDate();
        List<ChuzhiGjqmEntity> gjqmEntities = gjqmRepository.findByDay(staticDate);
        response.setLevel1(gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm1())));
        response.setLevel2(gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm2())));
        response.setLevel3(gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm3())));
        response.setLevel4(gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm4())));
        response.setLevel5(gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm5())));
        return new BaseResponse<>(response);
    }
}
