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
@Table(name = "bookstudent")
public class BookStudent implements java.io.Serializable {
    private static final long serialVersionUID = -4714427866396004820L;
    private Integer id;
    private Integer bid;
    private String bno;
    private String bname;
    private Integer sid;
    private String sno;
    private String sname;
    private String author;
    private Double price;
    private Date bdate;
    private Date rdate;
    private Date cdate;
    private Date udate;
    private Integer yn;
   public BookStudent(){
    	
    }
    @Id
   	@GeneratedValue(generator = "_native")
   	@GenericGenerator(name = "_native", strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
    @Column(length=20)
    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }
    @Column(length=20)
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
    @Column
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
    @Column(length=20)
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
    @Column(length=20)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    @Column(length=20)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Column(columnDefinition="numeric(10,2) default '0.00'")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(columnDefinition = "timestamp")
    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(columnDefinition = "timestamp")
    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
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