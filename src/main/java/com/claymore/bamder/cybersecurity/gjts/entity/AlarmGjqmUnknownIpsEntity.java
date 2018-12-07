package com.claymore.bamder.cybersecurity.gjts.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author li.zhuo
 * @create 2018/11/12 11:15 PM
 * @since 1.0.0
 */
@Entity
@Table(name = "alarm_gjqm_unknown_ips")
@IdClass(AlarmGjqmUnknownIpsEntityPK.class)
public class AlarmGjqmUnknownIpsEntity {
    private Date day;
    private long sourip;
    private long destip;
    private String sourarea;
    private String destarea;
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
    @Column(name = "sourip")
    public long getSourip() {
        return sourip;
    }

    public void setSourip(long sourip) {
        this.sourip = sourip;
    }

    @Id
    @Column(name = "destip")
    public long getDestip() {
        return destip;
    }

    public void setDestip(long destip) {
        this.destip = destip;
    }

    @Basic
    @Column(name = "sourarea")
    public String getSourarea() {
        return sourarea;
    }

    public void setSourarea(String sourarea) {
        this.sourarea = sourarea;
    }

    @Basic
    @Column(name = "destarea")
    public String getDestarea() {
        return destarea;
    }

    public void setDestarea(String destarea) {
        this.destarea = destarea;
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

        AlarmGjqmUnknownIpsEntity that = (AlarmGjqmUnknownIpsEntity) o;

        if (sourip != that.sourip) return false;
        if (destip != that.destip) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (sourarea != null ? !sourarea.equals(that.sourarea) : that.sourarea != null) return false;
        if (destarea != null ? !destarea.equals(that.destarea) : that.destarea != null) return false;
        if (alarmCount != null ? !alarmCount.equals(that.alarmCount) : that.alarmCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (int) (sourip ^ (sourip >>> 32));
        result = 31 * result + (int) (destip ^ (destip >>> 32));
        result = 31 * result + (sourarea != null ? sourarea.hashCode() : 0);
        result = 31 * result + (destarea != null ? destarea.hashCode() : 0);
        result = 31 * result + (alarmCount != null ? alarmCount.hashCode() : 0);
        return result;
    }
}
