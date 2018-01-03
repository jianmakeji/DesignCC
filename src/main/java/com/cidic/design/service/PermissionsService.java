package com.cidic.design.service;

import com.cidic.design.model.Permission;

public interface PermissionsService {

	public int createPermission(Permission permission);

	public int deletePermission(Long permissionId);
}
