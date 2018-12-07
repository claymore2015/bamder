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
public class AlarmGjqmUnknownSubtypeEntityPK implements Serializable {
    private Date day;
    private int subtype;

    @Column(name = "day")
    @Id
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "subtype")
    @Id
    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmUnknownSubtypeEntityPK that = (AlarmGjqmUnknownSubtypeEntityPK) o;

        if (subtype != that.subtype) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + subtype;
        return result;
    }
}
