package com.claymore.bamder.cybersecurity.wgxm.repository;

import com.claymore.bamder.cybersecurity.wgxm.entity.AlarmWgxwEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AlarmWgxwRepositoryTest {

    @Autowired
    AlarmWgxwRepository alarmWgxwRepository;

    @Test
    public void findByProvinceAndCityAndDistrictAndDay() throws Exception {

        List<AlarmWgxwEntity> byProvinceAndCityAndDistrictAndDay = alarmWgxwRepository.findByProvinceAndCityAndDistrictAndDay("1", "1", "1", new Date());
        System.out.println(byProvinceAndCityAndDistrictAndDay.size());
    }

}