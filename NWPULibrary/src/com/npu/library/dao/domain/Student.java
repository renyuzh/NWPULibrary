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
@Table(name = "student")
public class Student implements java.io.Serializable {
	private static final long serialVersionUID = 8442899157404726187L;
	private Integer id;
	private String sno;
	private String sname;
	private String password;
	private String passwordNew;
	private Date cdate;
	private Date udate;
	private Integer yn;
public Student(){
    	
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
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length=20)
	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
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
		return "Student [id=" + id + ", sno=" + sno + ", sname=" + sname
				+ ", password=" + password + ", passwordNew=" + passwordNew
				+ ", cdate=" + cdate + ", udate=" + udate + ", yn=" + yn + "]";
	}
	
}