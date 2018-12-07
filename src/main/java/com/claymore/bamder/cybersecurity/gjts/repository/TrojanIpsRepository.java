package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmTrojanIpsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmTrojanIpsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author li.zhuo
 * @create 2018/12/6 11:23 PM
 * @since 1.0.0
 */
public interface TrojanIpsRepository extends JpaRepository<AlarmGjqmTrojanIpsEntity, AlarmGjqmTrojanIpsEntityPK> {
}
