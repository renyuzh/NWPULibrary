package com.npu.library.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "booktype")
public class BookType implements java.io.Serializable {
    private static final long serialVersionUID = -4714427866396004820L;
    private Integer id;
    private String tname;
    private Date cdate;
    private Date udate;
    private Integer yn;
    @Id
  	@GeneratedValue(generator = "_native")
  	@GenericGenerator(name = "_native", strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length=20)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    @Temporal(TemporalType.TIMESTAMP)
  	@Column(columnDefinition = "timestamp")
    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    @Temporal(TemporalType.TIMESTAMP)
  	@Column(columnDefinition = "timestamp")
    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
    @Column
    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}