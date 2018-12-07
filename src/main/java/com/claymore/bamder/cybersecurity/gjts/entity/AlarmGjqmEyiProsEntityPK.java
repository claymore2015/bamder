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
public class AlarmGjqmEyiProsEntityPK implements Serializable {
    private Date day;
    private String proid;

    @Column(name = "day")
    @Id
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "proid")
    @Id
    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmEyiProsEntityPK that = (AlarmGjqmEyiProsEntityPK) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (proid != null ? !proid.equals(that.proid) : that.proid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (proid != null ? proid.hashCode() : 0);
        return result;
    }
}
