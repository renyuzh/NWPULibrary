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
@Table(name = "book")
public class Book implements java.io.Serializable {
    private static final long serialVersionUID = -4714427866396004820L;
    private Integer id;
    private String bno;
    private String bname;
    private Integer tid;
    private String tname;
    private String author;
    private Double price;
    private String brief;
    private Integer total;
    private Integer remain;
    private Date cdate;
    private Date udate;
    private Integer yn;
   public Book(){
    	
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
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
    @Column(length=20)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    public void setPrice(double price) {
        this.price = price;
    }
    @Column(length=200)
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    @Column
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    @Column
    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
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
	@Override
	public String toString() {
		return "Book [id=" + id + ", bno=" + bno + ", bname=" + bname
				+ ", tid=" + tid + ", tname=" + tname + ", author=" + author
				+ ", price=" + price + ", brief=" + brief + ", total=" + total
				+ ", remain=" + remain + ", cdate=" + cdate + ", udate="
				+ udate + ", yn=" + yn + "]";
	}
    
}