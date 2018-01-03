package com.cidic.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.PermissionDao;
import com.cidic.design.model.Permission;
import com.cidic.design.service.PermissionsService;
import com.cidic.design.util.ResponseCodeUtil;


@Service
@Component
@Qualifier(value = "permissionServiceImpl")
@Transactional
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	@Qualifier(value = "permissionDaoImpl")
	private PermissionDao permissionDaoImpl;
	
	@Override
	public int createPermission(Permission permission) {
		try{
			permissionDaoImpl.createPermission(permission);
			return ResponseCodeUtil.PERMISSION_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.PERMISSION_OPERATION_FAILURE;
		}
	}

	@Override
	public int deletePermission(Long permissionId) {
		
		try{
			permissionDaoImpl.deletePermission(permissionId);
			return ResponseCodeUtil.PERMISSION_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.PERMISSION_OPERATION_FAILURE;
		}
	}

}
