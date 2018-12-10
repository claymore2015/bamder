package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmUnknownIpsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmUnknownIpsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/12/9 11:02 AM
 * @since 1.0.0
 */
public interface UnknownIpsRepository extends JpaRepository<AlarmGjqmUnknownIpsEntity, AlarmGjqmUnknownIpsEntityPK> {
    @Query(value = "select e from AlarmGjqmUnknownIpsEntity e where  e.day >=:day ")
    List<AlarmGjqmUnknownIpsEntity> findByDay(@Param("day") Date day);
}
