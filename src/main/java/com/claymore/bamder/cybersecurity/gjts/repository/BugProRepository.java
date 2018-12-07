package com.claymore.bamder.cybersecurity.gjts.repository;

import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugProsEntity;
import com.claymore.bamder.cybersecurity.gjts.entity.AlarmGjqmBugProsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugProRepository extends JpaRepository<AlarmGjqmBugProsEntity, AlarmGjqmBugProsEntityPK> {
}
