package com.claymore.bamder;

import org.joda.time.DateTime;

import java.text.NumberFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BamderApplicationTests {

	//@Test
	public void contextLoads() {

    }

    public static void main(String[] args) {
        Date date = new DateTime(new Date()).minusDays(1).withTimeAtStartOfDay().toDate();
        //System.out.println(date);


        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);

        String format = numberFormat.format(0.1);
        System.out.println(format);

    }

}
