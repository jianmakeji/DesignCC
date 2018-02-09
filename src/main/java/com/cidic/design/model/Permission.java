package com.cidic.design.model;
// Generated 2017-3-26 18:14:32 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Permission generated by hbm2java
 */
@Entity
@Table(name = "permission", catalog = "design_cc")
public class Permission implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String permissionName;
	private Date createtime;
	private Set<PermissionRole> permissionRoles = new HashSet<PermissionRole>(0);

	public Permission() {
	}

	public Permission(String permissionName, Date createtime) {
		this.permissionName = permissionName;
		this.createtime = createtime;
	}

	public Permission(String permissionName, Date createtime, Set<PermissionRole> permissionRoles) {
		this.permissionName = permissionName;
		this.createtime = createtime;
		this.permissionRoles = permissionRoles;
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

	@Column(name = "permission_name", nullable = false, length = 30)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<PermissionRole> getPermissionRoles() {
		return this.permissionRoles;
	}

	public void setPermissionRoles(Set<PermissionRole> permissionRoles) {
		this.permissionRoles = permissionRoles;
	}

}
