package com.cidic.design.model;
// Generated 2017-5-10 9:11:03 by Hibernate Tools 4.3.1.Final

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
 * FileInfo generated by hbm2java
 */
@Entity
@Table(name = "file_info", catalog = "design_cc")
public class FileInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String fileName;
	private String filePath;
	private byte fileType;
	private Date createtime;

	public FileInfo() {
	}

	public FileInfo(String fileName, String filePath, byte fileType, Date createtime) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.createtime = createtime;
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

	@Column(name = "file_name", nullable = false, length = 20)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_path", nullable = false)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "file_type", nullable = false)
	public byte getFileType() {
		return this.fileType;
	}

	public void setFileType(byte fileType) {
		this.fileType = fileType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
