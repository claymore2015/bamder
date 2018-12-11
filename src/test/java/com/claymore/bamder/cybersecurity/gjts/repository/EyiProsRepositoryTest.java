package com.claymore.bamder.cybersecurity.gjts.repository;


import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmEyiProsEntity;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EyiProsRepositoryTest {
    @Autowired
    private EyiProsRepository eyiProsRepository;

    @Test
    public void testQueryByDay() {
        Date staticDate = new DateTime(new Date()).minusDays(10).withTimeAtStartOfDay().toDate();
        List<AlarmGjqmEyiProsEntity> byDay = eyiProsRepository.findByDay(staticDate);
        System.out.println(byDay.get(0).getName());
        System.out.println(byDay.get(0).getIsSim());
    }

}