package com.claymore.bamder.cybersecurity.platformconfig.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author li.zhuo
 * @create 2018/11/18 11:14 PM
 * @since 1.0.0
 */
@Entity
@Table(name = "platform_config")
@Data
public class PlatformConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String module;
    @Column(name = "config_key")
    private String configKey;
    @Column(name = "config_value")
    private String configValue;
    @Column(name = "type")
    private String type;
}
