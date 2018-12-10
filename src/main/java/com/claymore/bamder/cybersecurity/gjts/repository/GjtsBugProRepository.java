package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugProsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GjtsBugProRepository extends JpaRepository<AlarmGjqmBugProsEntity, AlarmGjqmBugProsEntityPK> {
    @Query(value = "select e from AlarmGjqmBugProsEntity e where  e.day >=:day ")
    List<AlarmGjqmBugProsEntity> findByDay(@Param("day") Date day);
}
