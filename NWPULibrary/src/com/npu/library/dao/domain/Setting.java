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
@Table(name = "setting")
public class Setting implements java.io.Serializable {

    private static final long serialVersionUID = 7061118381898238548L;
    private Integer id;
    private String name;
    private Integer value;
    private Date cdate;
    private Date udate;
    private Integer yn;
    
    public Setting(){
    	
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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