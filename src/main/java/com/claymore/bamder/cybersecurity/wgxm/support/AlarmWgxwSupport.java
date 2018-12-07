package com.claymore.bamder.cybersecurity.wgxm.support;

import com.claymore.bamder.common.util.PercentUtil;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.EventPoint;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiWgxwEntity;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.SourObjNumber;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.UnitNumber;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxmResponse;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.WgxmTimeTrendResponse;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwEntity;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwTop10Entity;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @author li.zhuo
 * @create 2018/11/28 10:58 PM
 * @since 1.0.0
 */
public class AlarmWgxwSupport {

    public static WgxmResponse transfer2WgxmResponse(List<AlarmWgxwEntity> alarmWgxwEntities, List<AlarmWgxwTop10Entity> top10Entities) {

        WgxmResponse response = new WgxmResponse();

        Long total = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        Long level1 = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 1).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        Long level2 = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 2).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        Long level3 = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 3).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        Long level4 = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 4).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        Long level5 = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 5).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);
        String level1Per = PercentUtil.format(new BigDecimal(level1).divide(new BigDecimal(total), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        String level2Per = PercentUtil.format(new BigDecimal(level2).divide(new BigDecimal(total), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        String level3Per = PercentUtil.format(new BigDecimal(level3).divide(new BigDecimal(total), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        String level4Per = PercentUtil.format(new BigDecimal(level4).divide(new BigDecimal(total), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        String level5Per = PercentUtil.format(new BigDecimal(level5).divide(new BigDecimal(total), 2, BigDecimal.ROUND_HALF_UP).doubleValue());


        Long trans = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getTrans()).reduce(0L, Long::sum);
        Long file = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getFile()).reduce(0L, Long::sum);
        Long efile = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getEfile()).reduce(0L, Long::sum);
        Long keyword = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getKeyword()).reduce(0L, Long::sum);
        Long encrypt = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getEncrypt()).reduce(0L, Long::sum);
        Long zip = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getZip()).reduce(0L, Long::sum);
        Long pic = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getPic()).reduce(0L, Long::sum);
        Long bfile = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getBfile()).reduce(0L, Long::sum);
        Long store = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getStore()).reduce(0L, Long::sum);
        Long outline = alarmWgxwEntities.stream().map(alarmWgxwEntity -> alarmWgxwEntity.getOutline()).reduce(0L, Long::sum);
        String transPer = PercentUtil.longDivideFormat(trans, total);
        String filePer = PercentUtil.longDivideFormat(file, total);
        String efilePer = PercentUtil.longDivideFormat(efile, total);
        String keywordPer = PercentUtil.longDivideFormat(keyword, total);
        String encryptPer = PercentUtil.longDivideFormat(encrypt, total);
        String zipPer = PercentUtil.longDivideFormat(zip, total);
        String picPer = PercentUtil.longDivideFormat(pic, total);
        String bfilePer = PercentUtil.longDivideFormat(bfile, total);
        String storePer = PercentUtil.longDivideFormat(store, total);
        String outlinePer = PercentUtil.longDivideFormat(outline, total);


        response.setTotal(total);
        response.setLevel1(level1);
        response.setLevel2(level2);
        response.setLevel3(level3);
        response.setLevel4(level4);
        response.setLevel5(level5);
        response.setLevel1Percent(level1Per);
        response.setLevel2Percent(level2Per);
        response.setLevel3Percent(level3Per);
        response.setLevel4Percent(level4Per);
        response.setLevel5Percent(level5Per);


        response.setTrans(trans);
        response.setTransPercent(transPer);
        response.setFile(file);
        response.setFilePercent(filePer);
        response.setEfile(efile);
        response.setEfilePercent(efilePer);
        response.setKeyword(keyword);
        response.setKeywordPercent(keywordPer);
        response.setEncrypt(encrypt);
        response.setEncryptPercent(encryptPer);
        response.setZip(zip);
        response.setZipPercent(zipPer);
        response.setPic(pic);
        response.setPicPercent(picPer);
        response.setBfile(bfile);
        response.setBfilePercent(bfilePer);
        response.setStore(store);
        response.setStorePercent(storePer);
        response.setOutline(outline);
        response.setOutlinePercent(outlinePer);

        response.setSourObjNumberList(transfer2Top10Source(top10Entities));
        response.setUnitNumberList(transfer2Unit10(top10Entities));
        return response;
    }

    public static List<SourObjNumber>  transfer2Top10Source(List<AlarmWgxwTop10Entity> top10Entities) {
        List<SourObjNumber> collect = top10Entities.stream().collect(Collectors.groupingBy(e -> e.getSourObjSub(), Collectors.summingLong(e -> e.getCount()))).entrySet().stream().map(entry -> new SourObjNumber(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return collect.stream().sorted(comparing(SourObjNumber::getNumber).reversed()).limit(10).collect(Collectors.toList());
    }

    public static List<UnitNumber> transfer2Unit10(List<AlarmWgxwTop10Entity> top10Entities) {
        List<UnitNumber> collect = top10Entities.stream().collect(Collectors.groupingBy(e -> e.getUnit(), Collectors.summingLong(e -> e.getCount()))).entrySet().stream().map(entry -> new UnitNumber(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return collect.stream().sorted(comparing(UnitNumber::getNumber).reversed()).limit(10).collect(Collectors.toList());
    }

    public static List<WgxmTimeTrendResponse> transfer2TimeTrendResponse(List<ChuzhiWgxwEntity> wgxwEntities) {
        Map<Date, List<ChuzhiWgxwEntity>> collect = wgxwEntities.stream().collect(Collectors.groupingBy(ChuzhiWgxwEntity::getDay));
        List<WgxmTimeTrendResponse> responses = Lists.newArrayList();
        collect.entrySet().forEach(e -> {
            WgxmTimeTrendResponse response = new WgxmTimeTrendResponse();
            response.setSj(e.getKey());
            response.setLevel1(e.getValue().stream().map(v -> v.getWgxw1()).reduce(0L, Long::sum));
            response.setLevel2(e.getValue().stream().map(v -> v.getWgxw2()).reduce(0L, Long::sum));
            response.setLevel3(e.getValue().stream().map(v -> v.getWgxw3()).reduce(0L, Long::sum));
            response.setLevel4(e.getValue().stream().map(v -> v.getWgxw4()).reduce(0L, Long::sum));
            response.setLevel5(e.getValue().stream().map(v -> v.getWgxw5()).reduce(0L, Long::sum));
            responses.add(response);
        });
        return responses;
    }

    public static List<EventPoint> generatePoints() {
        List<EventPoint> points = Lists.newArrayList();
        EventPoint point1 = new EventPoint();
        point1.setPointId(1);
        point1.setPointName("违规告警");
        EventPoint point2 = new EventPoint();
        point2.setPointId(2);
        point2.setPointName("非密内容");
        EventPoint point3 = new EventPoint();
        point3.setPointId(3);
        point3.setPointName("涉密内容");
        EventPoint point4 = new EventPoint();
        point4.setPointId(4);
        point4.setPointName("泄密事件");
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        return points;
    }

}
