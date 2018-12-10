package com.claymore.bamder.cybersecurity.gjts.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiGjqmEntity;
import com.claymore.bamder.cybersecurity.gj.respository.ChuzhiGjqmRepository;
import com.claymore.bamder.cybersecurity.gjts.domain.request.GjtsRequest;
import com.claymore.bamder.cybersecurity.gjts.domain.response.GjtsResponse;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmEyiProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmTrojanProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmUnknownIpsEntity;
import com.claymore.bamder.cybersecurity.gjts.repository.EyiProsRepository;
import com.claymore.bamder.cybersecurity.gjts.repository.GjtsBugProRepository;
import com.claymore.bamder.cybersecurity.gjts.repository.TrojanProRepository;
import com.claymore.bamder.cybersecurity.gjts.repository.UnknownIpsRepository;
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

    @Autowired
    private EyiProsRepository eyiProsRepository;

    @Autowired
    private TrojanProRepository trojanProRepository;

    @Autowired
    private GjtsBugProRepository gjtsBugProRepository;

    @Autowired
    private UnknownIpsRepository unknownIpsRepository;


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


        //恶意程序总量，总数
        List<AlarmGjqmEyiProsEntity> eyiPros = eyiProsRepository.findByDay(staticDate);
        long prosTotal = eyiPros.stream().collect(Collectors.summingLong(AlarmGjqmEyiProsEntity::getAlarmCount));
        int prosNum = eyiPros.stream().collect(Collectors.groupingBy(AlarmGjqmEyiProsEntity::getName)).size();
        response.setProsTotal(prosTotal);
        response.setProsNum(prosNum);

        //木马攻击总量，总数
        List<AlarmGjqmTrojanProsEntity> trojans = trojanProRepository.findByDay(staticDate);
        Long trojanTotal = trojans.stream().collect(Collectors.summingLong(AlarmGjqmTrojanProsEntity::getAlarmCount));
        int trojanNum = trojans.stream().collect(Collectors.groupingBy(AlarmGjqmTrojanProsEntity::getName)).size();
        response.setTrojanTotal(trojanTotal);
        response.setTrojanNum(trojanNum);

        //漏洞攻击总量，总数
        List<AlarmGjqmBugProsEntity> bugs = gjtsBugProRepository.findByDay(staticDate);
        long bugTotal = bugs.stream().collect(Collectors.summingLong(AlarmGjqmBugProsEntity::getAlarmCount));
        int bugNum = bugs.stream().collect(Collectors.groupingBy(AlarmGjqmBugProsEntity::getName)).size();
        response.setBugTotal(bugTotal);
        response.setBugNum(bugNum);

        //未知攻击总量
        List<AlarmGjqmUnknownIpsEntity> unknowns = unknownIpsRepository.findByDay(staticDate);
        long unknownsTotal = unknowns.stream().collect(Collectors.summingLong(AlarmGjqmUnknownIpsEntity::getAlarmCount));
        response.setUnknownTotal(unknownsTotal);

        //确定泄密事件
        long xmEvents = gjqmEntities.stream().collect(Collectors.summingLong(e -> e.getGjqm1D1() + e.getGjqm1D2() + e.getGjqm1D3() +
        e.getGjqm2D1() + e.getGjqm2D2() + e.getGjqm2D3() + e.getGjqm3D1() + e.getGjqm3D2() + e.getGjqm3D3() +
        e.getGjqm4D1() + e.getGjqm4D2() + e.getGjqm4D3() + e.getGjqm5D1() + e.getGjqm5D2() + e.getGjqm5D3()));
        response.setXmEventNum(xmEvents);



        return new BaseResponse<>(response);
    }
}
