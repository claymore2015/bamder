package com.claymore.bamder.cybersecurity.gjts.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author li.zhuo
 * @create 2018/11/12 11:15 PM
 * @since 1.0.0
 */
@Entity
@Table(name = "alarm_gjqm_unknown_subtype")
@IdClass(AlarmGjqmUnknownSubtypeEntityPK.class)
public class AlarmGjqmUnknownSubtypeEntity {
    private Date day;
    private int subtype;
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
    @Column(name = "subtype")
    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
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

        AlarmGjqmUnknownSubtypeEntity that = (AlarmGjqmUnknownSubtypeEntity) o;

        if (subtype != that.subtype) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (alarmCount != null ? !alarmCount.equals(that.alarmCount) : that.alarmCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + subtype;
        result = 31 * result + (alarmCount != null ? alarmCount.hashCode() : 0);
        return result;
    }
}
