package com.claymore.bamder.cybersecurity.assets.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author li.zhuo
 * @create 2018/12/9 5:19 PM
 * @since 1.0.0
 */
@Entity
@Table(name = "important_assets")
@Data
public class ImportantAssets {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "important_assets_type")
    private int importantAssetsType;
    @Column(name = "name")
    private String name;
}
