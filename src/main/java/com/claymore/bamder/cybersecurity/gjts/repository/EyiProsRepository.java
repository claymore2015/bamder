package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmEyiProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmEyiProsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/12/6 11:19 PM
 * @since 1.0.0
 */
public interface EyiProsRepository extends JpaRepository<AlarmGjqmEyiProsEntity, AlarmGjqmEyiProsEntityPK> {
    @Query(value = "select e from AlarmGjqmEyiProsEntity e where  e.day >=:day ")
    List<AlarmGjqmEyiProsEntity> findByDay(@Param("day") Date day);
}
