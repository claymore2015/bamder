package com.claymore.bamder.cybersecurity.assets.repository;

import com.claymore.bamder.cybersecurity.assets.entity.ImportantAssets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantAssetsRepository extends JpaRepository<ImportantAssets, Long> {
}
