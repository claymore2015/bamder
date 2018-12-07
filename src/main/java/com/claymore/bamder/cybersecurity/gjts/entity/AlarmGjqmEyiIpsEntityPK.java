package com.claymore.bamder.cybersecurity.gjts.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author li.zhuo
 * @create 2018/11/12 11:15 PM
 * @since 1.0.0
 */
public class AlarmGjqmEyiIpsEntityPK implements Serializable {
    private Date day;
    private long sourip;
    private long destip;

    @Column(name = "day")
    @Id
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "sourip")
    @Id
    public long getSourip() {
        return sourip;
    }

    public void setSourip(long sourip) {
        this.sourip = sourip;
    }

    @Column(name = "destip")
    @Id
    public long getDestip() {
        return destip;
    }

    public void setDestip(long destip) {
        this.destip = destip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmEyiIpsEntityPK that = (AlarmGjqmEyiIpsEntityPK) o;

        if (sourip != that.sourip) return false;
        if (destip != that.destip) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (int) (sourip ^ (sourip >>> 32));
        result = 31 * result + (int) (destip ^ (destip >>> 32));
        return result;
    }
}
