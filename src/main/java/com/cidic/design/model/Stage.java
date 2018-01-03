package com.cidic.design.model;
// Generated 2017-5-9 16:09:09 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Stage generated by hbm2java
 */
@Entity
@Table(name = "stage", catalog = "design_competition_seniorman")
public class Stage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String timeRange;
	private byte rank;
	private Date createTime;

	public Stage() {
	}

	public Stage(String name, String timeRange, byte rank, Date createTime) {
		this.name = name;
		this.timeRange = timeRange;
		this.rank = rank;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "time_range", nullable = false, length = 15)
	public String getTimeRange() {
		return this.timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	@Column(name = "rank", nullable = false)
	public byte getRank() {
		return this.rank;
	}

	public void setRank(byte rank) {
		this.rank = rank;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
