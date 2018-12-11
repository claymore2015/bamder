package com.claymore.bamder.cybersecurity.wgxm.support;

import com.claymore.bamder.common.util.PercentUtil;
import com.claymore.bamder.cybersecurity.wgxm.domain.response.SourObjNumber;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwEntity;
import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwTop10Entity;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class AlarmWgxwSupportTest {

    @Test
    public void testCollect() {

        List<AlarmWgxwEntity> alarmWgxwEntities = Lists.newArrayList();
        AlarmWgxwEntity e1 = new AlarmWgxwEntity();
        e1.setAlarmLevel(1);
        e1.setTotalCount(100L);
        AlarmWgxwEntity e2 = new AlarmWgxwEntity();
        e2.setAlarmLevel(1);
        e2.setTotalCount(100L);
        AlarmWgxwEntity e3 = new AlarmWgxwEntity();
        e3.setAlarmLevel(1);
        e3.setTotalCount(101L);

        alarmWgxwEntities.add(e1);
        alarmWgxwEntities.add(e2);
        alarmWgxwEntities.add(e3);

        Long collect = alarmWgxwEntities.stream().filter(alarmWgxwEntity -> alarmWgxwEntity.getAlarmLevel() == 1).map(alarmWgxwEntity -> alarmWgxwEntity.getTotalCount()).reduce(0L, Long::sum);

        System.out.println(collect);
    }

    @Test
    public void testPercent() {
        BigDecimal a = new BigDecimal(50);
        BigDecimal b = new BigDecimal(100);
        String format = PercentUtil.format(a.divide(b, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(format);
    }


    @Test
    public void sored() {
        List<AlarmWgxwTop10Entity> top10Entities = Lists.newArrayList();
        AlarmWgxwTop10Entity a1 = new AlarmWgxwTop10Entity();
        a1.setSourObjSub("a");
        a1.setCount(100L);
        AlarmWgxwTop10Entity a2 = new AlarmWgxwTop10Entity();
        a2.setSourObjSub("a");
        a2.setCount(102L);
        AlarmWgxwTop10Entity a3 = new AlarmWgxwTop10Entity();
        a3.setSourObjSub("b");
        a3.setCount(100L);
        AlarmWgxwTop10Entity a4 = new AlarmWgxwTop10Entity();
        a4.setSourObjSub("c");
        a4.setCount(101L);

        top10Entities.add(a1);
        top10Entities.add(a2);
        top10Entities.add(a3);
        top10Entities.add(a4);

        List<SourObjNumber> collect = top10Entities.stream().collect(Collectors.groupingBy(e -> e.getSourObjSub(), Collectors.summingLong(e -> e.getCount()))).entrySet().stream().map(entry -> new SourObjNumber(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        List<SourObjNumber> collect1 = collect.stream().sorted(comparing(SourObjNumber::getNumber).reversed()).limit(2).collect(Collectors.toList());
        System.out.println(collect1.size());
        System.out.println(collect.size());
        System.out.println(collect1.get(0).getSourceObjSubject());
        System.out.println(collect1.get(1).getSourceObjSubject());
        System.out.println(collect1.get(0).getNumber());
        System.out.println(collect1.get(1).getNumber());
    }

}
