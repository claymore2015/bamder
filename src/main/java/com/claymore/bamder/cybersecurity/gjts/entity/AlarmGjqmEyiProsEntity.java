package com.claymore.bamder.cybersecurity.gjts.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author li.zhuo
 * @create 2018/11/12 11:15 PM
 * @since 1.0.0
 */
@Entity
@Table(name = "alarm_gjqm_eyi_pros")
@IdClass(AlarmGjqmEyiProsEntityPK.class)
public class AlarmGjqmEyiProsEntity {
    private Date day;
    private String proid;
    private String name;
    private Byte isSim;
    private Long alarmCount;

    @Id
    @Column(name = "day")
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Id
    @Column(name = "proid")
    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_sim")
    public Byte getIsSim() {
        return isSim;
    }

    public void setIsSim(Byte isSim) {
        this.isSim = isSim;
    }

    @Basic
    @Column(name = "alarm_count")
    public Long getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Long alarmCount) {
        this.alarmCount = alarmCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmEyiProsEntity that = (AlarmGjqmEyiProsEntity) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (proid != null ? !proid.equals(that.proid) : that.proid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (isSim != null ? !isSim.equals(that.isSim) : that.isSim != null) return false;
        if (alarmCount != null ? !alarmCount.equals(that.alarmCount) : that.alarmCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (proid != null ? proid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isSim != null ? isSim.hashCode() : 0);
        result = 31 * result + (alarmCount != null ? alarmCount.hashCode() : 0);
        return result;
    }
}
