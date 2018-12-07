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
public class AlarmGjqmBugProsEntityPK implements Serializable {
    private Date day;
    private String bugid;

    @Column(name = "day")
    @Id
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "bugid")
    @Id
    public String getBugid() {
        return bugid;
    }

    public void setBugid(String bugid) {
        this.bugid = bugid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmGjqmBugProsEntityPK that = (AlarmGjqmBugProsEntityPK) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (bugid != null ? !bugid.equals(that.bugid) : that.bugid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (bugid != null ? bugid.hashCode() : 0);
        return result;
    }
}
