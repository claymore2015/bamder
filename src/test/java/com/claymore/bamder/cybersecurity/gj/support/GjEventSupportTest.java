package com.claymore.bamder.cybersecurity.gj.support;

import com.claymore.bamder.cybersecurity.gj.domain.event.response.PointTransfer;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

public class GjEventSupportTest {

    @Test
    public void transferToTotalPoint() throws Exception {

        PointTransfer p12 = new PointTransfer();
        p12.setSourcePoint(1);
        p12.setTargetPoint(2);
        p12.setPointValue(100L);

        PointTransfer p14 = new PointTransfer();
        p14.setSourcePoint(1);
        p14.setTargetPoint(4);
        p14.setPointValue(200L);

        PointTransfer p24 = new PointTransfer();
        p24.setSourcePoint(2);
        p24.setTargetPoint(4);
        p24.setPointValue(300L);

        PointTransfer p34 = new PointTransfer();
        p34.setSourcePoint(3);
        p34.setTargetPoint(4);
        p34.setPointValue(400L);

        List<PointTransfer> wgxw = Lists.newArrayList();
        wgxw.add(p12);
        wgxw.add(p14);
        wgxw.add(p24);
        wgxw.add(p34);

        List<PointTransfer > mbsj = Lists.newArrayList();
        mbsj.add(p12);
        mbsj.add(p14);
        mbsj.add(p24);
        mbsj.add(p34);

        List<PointTransfer> txzd = Lists.newArrayList();
        txzd.add(p12);
        txzd.add(p14);
        txzd.add(p24);
        txzd.add(p34);

        List<PointTransfer> gjqm = Lists.newArrayList();
        gjqm.add(p12);
        gjqm.add(p14);
        gjqm.add(p24);
        gjqm.add(p34);

        List<PointTransfer> transfers = GjEventSupport.transferToTotalPoint(wgxw, mbsj, txzd, gjqm);
        transfers.forEach(pointTransfer -> {
            System.out.println(pointTransfer.getSourcePoint());
            System.out.println(pointTransfer.getTargetPoint());
            System.out.println(pointTransfer.getPointValue());
        });

    }

}