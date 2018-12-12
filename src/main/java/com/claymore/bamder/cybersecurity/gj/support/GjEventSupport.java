package com.claymore.bamder.cybersecurity.gj.support;

import com.claymore.bamder.cybersecurity.event.entity.EventsInfoEntity;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.EventPoint;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.PointTransfer;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiGjqmEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiMbsjEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiTxzdEntity;
import com.claymore.bamder.cybersecurity.gj.entity.ChuzhiWgxwEntity;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author li.zhuo
 * @create 2018/12/1 5:11 PM
 * @since 1.0.0
 */
public class GjEventSupport {

    public static List<EventPoint> generateEventPoints() {
        List<EventPoint> points = Lists.newArrayList();
        EventPoint point1 = new EventPoint();
        point1.setPointId(1);
        point1.setPointName("告警");
        points.add(point1);

        EventPoint point2 = new EventPoint();
        point2.setPointId(2);
        point2.setPointName("正常内容");
        points.add(point2);

        EventPoint point3 = new EventPoint();
        point3.setPointId(3);
        point3.setPointName("异常内容");
        points.add(point3);

        EventPoint point4 = new EventPoint();
        point4.setPointId(4);
        point4.setPointName("窃密/泄密事件");
        points.add(point4);

        return points;
    }

    public static List<PointTransfer> transferToWgwxPoint(List<EventsInfoEntity> eventsInfo, List<ChuzhiWgxwEntity> wgxwEntities) {
        List<PointTransfer> list = Lists.newArrayList();
        PointTransfer wgxwTransfer12 = new PointTransfer();
        wgxwTransfer12.setSourcePoint(1);
        wgxwTransfer12.setTargetPoint(2);
        wgxwTransfer12.setPointValue(wgxwEntities.stream().map(chuzhiWgxwEntity -> chuzhiWgxwEntity.getWgxw1D0() + chuzhiWgxwEntity.getWgxw2D0() + chuzhiWgxwEntity.getWgxw3D0() + chuzhiWgxwEntity.getWgxw4D0() + chuzhiWgxwEntity.getWgxw5D0()).reduce(Long::sum).orElse(0L));


        PointTransfer wgxwTransfer13 = new PointTransfer();
        wgxwTransfer13.setSourcePoint(1);
        wgxwTransfer13.setTargetPoint(3);
        wgxwTransfer13.setPointValue(wgxwEntities.stream().map(chuzhiWgxwEntity -> chuzhiWgxwEntity.getWgxw1D1() + chuzhiWgxwEntity.getWgxw2D1() + chuzhiWgxwEntity.getWgxw3D1() + chuzhiWgxwEntity.getWgxw4D1() + chuzhiWgxwEntity.getWgxw5D1()
                + chuzhiWgxwEntity.getWgxw1D2() + chuzhiWgxwEntity.getWgxw2D2() + chuzhiWgxwEntity.getWgxw3D2() + chuzhiWgxwEntity.getWgxw4D2() + chuzhiWgxwEntity.getWgxw5D2()
                + chuzhiWgxwEntity.getWgxw1D3() + chuzhiWgxwEntity.getWgxw2D3() + chuzhiWgxwEntity.getWgxw3D3() + chuzhiWgxwEntity.getWgxw4D3() + chuzhiWgxwEntity.getWgxw5D3()).reduce(Long::sum).orElse(0L));


        PointTransfer wgxwTransfer24 = new PointTransfer();
        wgxwTransfer24.setSourcePoint(2);
        wgxwTransfer24.setTargetPoint(4);
        wgxwTransfer24.setPointValue(eventsInfo.stream().map(entity -> entity.getWgxw0()).reduce(Long::sum).orElse(0L));

        PointTransfer wgxwTransfer34 = new PointTransfer();
        wgxwTransfer34.setSourcePoint(3);
        wgxwTransfer34.setTargetPoint(4);
        wgxwTransfer34.setPointValue(eventsInfo.stream().map(entity -> entity.getWgxw1()).reduce(Long::sum).orElse(0L));

        PointTransfer wgwxTransfer14 = new PointTransfer();
        wgwxTransfer14.setSourcePoint(1);
        wgwxTransfer14.setTargetPoint(4);
        wgwxTransfer14.setPointValue(wgxwTransfer24.getPointValue() + wgxwTransfer34.getPointValue());

        list.add(wgxwTransfer12);
        list.add(wgxwTransfer13);
        list.add(wgxwTransfer24);
        list.add(wgxwTransfer34);
        list.add(wgwxTransfer14);
        return list;
    }

    public static List<PointTransfer> transferToGjqmPoint(List<EventsInfoEntity> eventsInfo, List<ChuzhiGjqmEntity> gjqmEntities) {
        List<PointTransfer> list = Lists.newArrayList();
        PointTransfer gjqmTransfer12 = new PointTransfer();
        gjqmTransfer12.setSourcePoint(1);
        gjqmTransfer12.setTargetPoint(2);
        gjqmTransfer12.setPointValue(gjqmEntities.stream().map(chuzhiGjqmEntity -> chuzhiGjqmEntity.getGjqm1D0() + chuzhiGjqmEntity.getGjqm2D0() + chuzhiGjqmEntity.getGjqm3D0() + chuzhiGjqmEntity.getGjqm4D0() + chuzhiGjqmEntity.getGjqm5D0()).reduce(Long::sum).orElse(0L));


        PointTransfer gjqmTransfer13 = new PointTransfer();
        gjqmTransfer13.setSourcePoint(1);
        gjqmTransfer13.setTargetPoint(3);
        gjqmTransfer13.setPointValue(gjqmEntities.stream().map(chuzhiGjqmEntity -> chuzhiGjqmEntity.getGjqm1D1() + chuzhiGjqmEntity.getGjqm2D1() + chuzhiGjqmEntity.getGjqm3D1() + chuzhiGjqmEntity.getGjqm4D1() + chuzhiGjqmEntity.getGjqm5D1()
                + chuzhiGjqmEntity.getGjqm1D2() + chuzhiGjqmEntity.getGjqm2D2() + chuzhiGjqmEntity.getGjqm3D2() + chuzhiGjqmEntity.getGjqm4D2() + chuzhiGjqmEntity.getGjqm5D2()
                + chuzhiGjqmEntity.getGjqm1D3() + chuzhiGjqmEntity.getGjqm2D3() + chuzhiGjqmEntity.getGjqm3D3() + chuzhiGjqmEntity.getGjqm4D3() + chuzhiGjqmEntity.getGjqm5D3()).reduce(Long::sum).orElse(0L));


        PointTransfer gjqmTransfer24 = new PointTransfer();
        gjqmTransfer24.setSourcePoint(2);
        gjqmTransfer24.setTargetPoint(4);
        gjqmTransfer24.setPointValue(eventsInfo.stream().map(entity -> entity.getGjqm0()).reduce(Long::sum).orElse(0L));

        PointTransfer gjqmTransfer34 = new PointTransfer();
        gjqmTransfer34.setSourcePoint(3);
        gjqmTransfer34.setTargetPoint(4);
        gjqmTransfer34.setPointValue(eventsInfo.stream().map(entity -> entity.getGjqm1()).reduce(Long::sum).orElse(0L));

        PointTransfer gjqmTransfer14 = new PointTransfer();
        gjqmTransfer14.setSourcePoint(1);
        gjqmTransfer14.setTargetPoint(4);
        gjqmTransfer14.setPointValue(gjqmTransfer24.getPointValue() + gjqmTransfer34.getPointValue());

        list.add(gjqmTransfer12);
        list.add(gjqmTransfer13);
        list.add(gjqmTransfer24);
        list.add(gjqmTransfer34);
        list.add(gjqmTransfer14);
        return list;
    }


    public static List<PointTransfer> transferToMbsjPoint(List<EventsInfoEntity> eventsInfo, List<ChuzhiMbsjEntity> mbsjEntities) {
        List<PointTransfer> list = Lists.newArrayList();
        PointTransfer mbsjTransfer12 = new PointTransfer();
        mbsjTransfer12.setSourcePoint(1);
        mbsjTransfer12.setTargetPoint(2);
        mbsjTransfer12.setPointValue(mbsjEntities.stream().map(chuzhiMbsjEntity -> chuzhiMbsjEntity.getMbsj1D0() + chuzhiMbsjEntity.getMbsj2D0() + chuzhiMbsjEntity.getMbsj3D0() + chuzhiMbsjEntity.getMbsj4D0() + chuzhiMbsjEntity.getMbsj5D0()).reduce(Long::sum).orElse(0L));


        PointTransfer mbsjTransfer13 = new PointTransfer();
        mbsjTransfer13.setSourcePoint(1);
        mbsjTransfer13.setTargetPoint(3);
        mbsjTransfer13.setPointValue(mbsjEntities.stream().map(chuzhiMbsjEntity -> chuzhiMbsjEntity.getMbsj1D1() + chuzhiMbsjEntity.getMbsj2D1() + chuzhiMbsjEntity.getMbsj3D1() + chuzhiMbsjEntity.getMbsj4D1() + chuzhiMbsjEntity.getMbsj5D1()
                + chuzhiMbsjEntity.getMbsj1D2() + chuzhiMbsjEntity.getMbsj2D2() + chuzhiMbsjEntity.getMbsj3D2() + chuzhiMbsjEntity.getMbsj4D2() + chuzhiMbsjEntity.getMbsj5D2()
                + chuzhiMbsjEntity.getMbsj1D3() + chuzhiMbsjEntity.getMbsj2D3() + chuzhiMbsjEntity.getMbsj3D3() + chuzhiMbsjEntity.getMbsj4D3() + chuzhiMbsjEntity.getMbsj5D3()).reduce(Long::sum).orElse(0L));


        PointTransfer mbsjTransfer24 = new PointTransfer();
        mbsjTransfer24.setSourcePoint(2);
        mbsjTransfer24.setTargetPoint(4);
        mbsjTransfer24.setPointValue(eventsInfo.stream().map(entity -> entity.getMbsj0()).reduce(Long::sum).orElse(0L));

        PointTransfer mbsjTransfer34 = new PointTransfer();
        mbsjTransfer34.setSourcePoint(3);
        mbsjTransfer34.setTargetPoint(4);
        mbsjTransfer34.setPointValue(eventsInfo.stream().map(entity -> entity.getMbsj1()).reduce(Long::sum).orElse(0L));

        PointTransfer mbsjTransfer14 = new PointTransfer();
        mbsjTransfer14.setSourcePoint(1);
        mbsjTransfer14.setTargetPoint(4);
        mbsjTransfer14.setPointValue(mbsjTransfer24.getPointValue() + mbsjTransfer34.getPointValue());

        list.add(mbsjTransfer12);
        list.add(mbsjTransfer13);
        list.add(mbsjTransfer24);
        list.add(mbsjTransfer34);
        list.add(mbsjTransfer14);
        return list;
    }


    public static List<PointTransfer> transferToTxzdPoint(List<EventsInfoEntity> eventsInfo, List<ChuzhiTxzdEntity> txzdEntities) {
        List<PointTransfer> list = Lists.newArrayList();
        PointTransfer txzdTransfer12 = new PointTransfer();
        txzdTransfer12.setSourcePoint(1);
        txzdTransfer12.setTargetPoint(2);
        txzdTransfer12.setPointValue(txzdEntities.stream().map(chuzhiTxzdEntity -> chuzhiTxzdEntity.getTxzd1D0() + chuzhiTxzdEntity.getTxzd2D0() + chuzhiTxzdEntity.getTxzd3D0() + chuzhiTxzdEntity.getTxzd4D0() + chuzhiTxzdEntity.getTxzd5D0()).reduce(Long::sum).orElse(0L));

        PointTransfer txzdTransfer13 = new PointTransfer();
        txzdTransfer13.setSourcePoint(1);
        txzdTransfer13.setTargetPoint(3);
        txzdTransfer13.setPointValue(txzdEntities.stream().map(chuzhiTxzdEntity -> chuzhiTxzdEntity.getTxzd1D1() + chuzhiTxzdEntity.getTxzd2D1() + chuzhiTxzdEntity.getTxzd3D1() + chuzhiTxzdEntity.getTxzd4D1() + chuzhiTxzdEntity.getTxzd5D1()
                + chuzhiTxzdEntity.getTxzd1D2() + chuzhiTxzdEntity.getTxzd2D2() + chuzhiTxzdEntity.getTxzd3D2() + chuzhiTxzdEntity.getTxzd4D2() + chuzhiTxzdEntity.getTxzd5D2()
                + chuzhiTxzdEntity.getTxzd1D3() + chuzhiTxzdEntity.getTxzd2D3() + chuzhiTxzdEntity.getTxzd3D3() + chuzhiTxzdEntity.getTxzd4D3() + chuzhiTxzdEntity.getTxzd5D3()).reduce(Long::sum).orElse(0L));

        PointTransfer txzdTransfer24 = new PointTransfer();
        txzdTransfer24.setSourcePoint(2);
        txzdTransfer24.setTargetPoint(4);
        txzdTransfer24.setPointValue(eventsInfo.stream().map(entity -> entity.getTxzd0()).reduce(Long::sum).orElse(0L));

        PointTransfer txzdTransfer34 = new PointTransfer();
        txzdTransfer34.setSourcePoint(3);
        txzdTransfer34.setTargetPoint(4);
        txzdTransfer34.setPointValue(eventsInfo.stream().map(entity -> entity.getTxzd1()).reduce(Long::sum).orElse(0L));

        PointTransfer txzdTransfer14 = new PointTransfer();
        txzdTransfer14.setSourcePoint(1);
        txzdTransfer14.setTargetPoint(4);
        txzdTransfer14.setPointValue(txzdTransfer24.getPointValue() + txzdTransfer34.getPointValue());

        list.add(txzdTransfer12);
        list.add(txzdTransfer13);
        list.add(txzdTransfer24);
        list.add(txzdTransfer34);
        list.add(txzdTransfer14);
        return list;
    }

    public static List<PointTransfer> transferToTotalPoint(List<PointTransfer> wgxw, List<PointTransfer> mbsj, List<PointTransfer> txzd , List<PointTransfer> gjqm) {
        List<PointTransfer> list = Lists.newArrayList();

        List<PointTransfer> listAll = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(wgxw)){
            listAll.addAll(wgxw);
        }
        if (!CollectionUtils.isEmpty(mbsj)) {
            listAll.addAll(mbsj);
        }
        if (!CollectionUtils.isEmpty(txzd)) {
            listAll.addAll(txzd);
        }
        if (!CollectionUtils.isEmpty(gjqm)) {
            listAll.addAll(gjqm);
        }

        PointTransfer totalTransfer12 = new PointTransfer();
        totalTransfer12.setSourcePoint(1);
        totalTransfer12.setTargetPoint(2);

        PointTransfer totalTransfer13 = new PointTransfer();
        totalTransfer13.setSourcePoint(1);
        totalTransfer13.setTargetPoint(3);

        PointTransfer totalTransfer24 = new PointTransfer();
        totalTransfer24.setSourcePoint(2);
        totalTransfer24.setTargetPoint(4);

        PointTransfer totalTransfer34 = new PointTransfer();
        totalTransfer34.setSourcePoint(3);
        totalTransfer34.setTargetPoint(4);

        PointTransfer totalTransfer14 = new PointTransfer();
        totalTransfer14.setSourcePoint(1);
        totalTransfer14.setTargetPoint(4);

        Map<Integer, Map<Integer, List<PointTransfer>>> collect = listAll.stream().collect(Collectors.groupingBy(PointTransfer::getSourcePoint, Collectors.groupingBy(PointTransfer::getTargetPoint)));
        if (Objects.nonNull(collect.get(1)) && Objects.nonNull(collect.get(1).get(2))) {
            totalTransfer12.setPointValue(collect.get(1).get(2).stream().collect(Collectors.summingLong(value -> value.getPointValue())));
        }
        if (Objects.nonNull(collect.get(2)) && Objects.nonNull(collect.get(2).get(4))) {
            totalTransfer24.setPointValue(collect.get(2).get(4).stream().collect(Collectors.summingLong(value -> value.getPointValue())));
        }
        if (Objects.nonNull(collect.get(3)) && Objects.nonNull(collect.get(3).get(4))) {
            totalTransfer34.setPointValue(collect.get(3).get(4).stream().collect(Collectors.summingLong(value -> value.getPointValue())));
        }
        if (Objects.nonNull(collect.get(1)) && Objects.nonNull(collect.get(1).get(4))) {
            totalTransfer14.setPointValue(collect.get(1).get(4).stream().collect(Collectors.summingLong(value -> value.getPointValue())));
        }

        if (Objects.nonNull(collect.get(1)) && Objects.nonNull(collect.get(1).get(3))) {
            totalTransfer13.setPointValue(collect.get(1).get(3).stream().collect(Collectors.summingLong(value -> value.getPointValue())));
        }

        list.add(totalTransfer12);
        list.add(totalTransfer24);
        list.add(totalTransfer34);
        list.add(totalTransfer14);
        list.add(totalTransfer13);
        return list;
    }

}
