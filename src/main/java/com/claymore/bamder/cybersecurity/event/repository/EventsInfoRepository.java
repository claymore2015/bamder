package com.claymore.bamder.cybersecurity.event.repository;

import com.claymore.bamder.cybersecurity.event.entity.EventsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/28 9:22 PM
 * @since 1.0.0
 */
public interface EventsInfoRepository extends JpaRepository<EventsInfoEntity, Long> {
    @Query(value = "select e from EventsInfoEntity e where e.province=:province and (:city is null or e.city=:city) and (:district is null or e.district=:district) and e.eventTime >=:day order by e.eventTime desc")
    List<EventsInfoEntity> findByProvinceAndCityAndDistrictAndDay(@Param("province") String province, @Param("city") String city, @Param("district") String district, @Param("day") Date day);
}
