package com.claymore.bamder.cybersecurity.gj.support;

import com.claymore.bamder.cybersecurity.gj.domain.district.GjCityResponse;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class GjSupportTest {

    @Test
    public void testMergeCity() {
        java.util.List<GjCityResponse> cityWgxw = Lists.newArrayList();
        GjCityResponse response = new GjCityResponse();
        response.setCityId("1");
        response.setWgxwNumber(1000L);
        response.setWgxwChuzhi(500L);
        response.setLevel1Chuzhi(1L);
        response.setLevel2Chuzhi(2L);
        response.setLevel3Chuzhi(3L);
        response.setLevel4Chuzhi(4L);
        response.setLevel5Chuzhi(5L);
        response.setLevel1(1L);
        response.setLevel2(2L);
        response.setLevel3(3L);
        response.setLevel4(4L);
        response.setLevel5(5L);
        cityWgxw.add(response);

        List<GjCityResponse> cityGjqm = Lists.newArrayList();
        GjCityResponse response2 = new GjCityResponse();
        response2.setCityId("1");
        response2.setGjqmNumber(1000L);
        response2.setGjqmChuzhi(500L);
        response2.setLevel1Chuzhi(1L);
        response2.setLevel2Chuzhi(2L);
        response2.setLevel3Chuzhi(3L);
        response2.setLevel4Chuzhi(4L);
        response2.setLevel5Chuzhi(5L);
        response2.setLevel1(1L);
        response2.setLevel2(2L);
        response2.setLevel3(3L);
        response2.setLevel4(4L);
        response2.setLevel5(5L);
        cityGjqm.add(response2);

        List<GjCityResponse> cityTxzd = Lists.newArrayList();
        GjCityResponse response3 = new GjCityResponse();
        response3.setCityId("1");
        response3.setTxzdNumber(1000L);
        response3.setTxzdChuzhi(500L);
        response3.setLevel1Chuzhi(1L);
        response3.setLevel2Chuzhi(2L);
        response3.setLevel3Chuzhi(3L);
        response3.setLevel4Chuzhi(4L);
        response3.setLevel5Chuzhi(5L);
        response3.setLevel1(1L);
        response3.setLevel2(2L);
        response3.setLevel3(3L);
        response3.setLevel4(4L);
        response3.setLevel5(5L);
        cityTxzd.add(response3);


        List<GjCityResponse> cityMbsj = Lists.newArrayList();
        GjCityResponse response4 = new GjCityResponse();
        response4.setCityId("1");
        response4.setMbsjNumber(1000L);
        response4.setMbsjChuzhi(500L);
        response4.setLevel1Chuzhi(1L);
        response4.setLevel2Chuzhi(2L);
        response4.setLevel3Chuzhi(3L);
        response4.setLevel4Chuzhi(4L);
        response4.setLevel5Chuzhi(5L);
        response4.setLevel1(1L);
        response4.setLevel2(2L);
        response4.setLevel3(3L);
        response4.setLevel4(4L);
        response4.setLevel5(5L);
        cityMbsj.add(response4);

        List<GjCityResponse> gjCityRespons = GjSupport.mergeCity(cityWgxw, cityGjqm, cityTxzd, cityMbsj);
        gjCityRespons.forEach(r -> {
            System.out.println(r.getCityId());
            System.out.println(r.getWgxwNumber());
            System.out.println(r.getWgxwChuzhi());
            System.out.println(r.getGjqmNumber());
            System.out.println(r.getGjqmChuzhi());
            System.out.println(r.getTxzdNumber());
            System.out.println(r.getTxzdChuzhi());
            System.out.println(r.getMbsjNumber());
            System.out.println(r.getMbsjChuzhi());
            System.out.println(r.getLevel1());
            System.out.println(r.getLevel2());
            System.out.println(r.getLevel3());
            System.out.println(r.getLevel4());
            System.out.println(r.getLevel5());
            System.out.println(r.getLevel1Chuzhi());
            System.out.println(r.getLevel2Chuzhi());
            System.out.println(r.getLevel3Chuzhi());
            System.out.println(r.getLevel4Chuzhi());
            System.out.println(r.getLevel5Chuzhi());
        });
    }
}