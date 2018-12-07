package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugIpsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugIpsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugIpRepository extends JpaRepository<AlarmGjqmBugIpsEntity, AlarmGjqmBugIpsEntityPK> {
}
