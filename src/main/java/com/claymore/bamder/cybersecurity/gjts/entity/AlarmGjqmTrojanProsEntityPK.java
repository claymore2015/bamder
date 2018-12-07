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
public class AlarmGjqmTrojanProsEntityPK implements Serializable {
    private Date day;
    private String trojanid;

    @Column(name = "day")
    @Id
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "trojanid")
    @Id
    public String getTrojanid() {
        return trojanid;
    }

    public void setTrojanid(String trojanid) {
        this.trojanid = trojanid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmTrojanProsEntityPK that = (AlarmGjqmTrojanProsEntityPK) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (trojanid != null ? !trojanid.equals(that.trojanid) : that.trojanid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (trojanid != null ? trojanid.hashCode() : 0);
        return result;
    }
}
