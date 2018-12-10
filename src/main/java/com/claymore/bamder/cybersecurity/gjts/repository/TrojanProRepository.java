package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmTrojanProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmTrojanProsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/12/6 11:23 PM
 * @since 1.0.0
 */
public interface TrojanProRepository extends JpaRepository<AlarmGjqmTrojanProsEntity, AlarmGjqmTrojanProsEntityPK> {
    @Query(value = "select e from AlarmGjqmTrojanProsEntity e where  e.day >=:day ")
    List<AlarmGjqmTrojanProsEntity> findByDay(@Param("day") Date day);
}
