package com.claymore.bamder.cybersecurity.unit.repository;

import com.claymore.bamder.cybersecurity.unit.entity.UnitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/11/12 11:20 PM
 * @since 1.0.0
 */
public interface UnitsRepository extends JpaRepository<UnitsEntity, Long> {

    @Query(value = "select e from UnitsEntity e where e.province=:province and (:city is null or e.city=:city) and (:district is null or e.district=:district) ")
    List<UnitsEntity> findByProvinceAndCityAndDistrict(@Param("province") String province, @Param("city") String city, @Param("district") String district);
}
