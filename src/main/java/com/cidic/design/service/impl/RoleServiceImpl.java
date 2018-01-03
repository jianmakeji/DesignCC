package com.cidic.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.RoleDao;
import com.cidic.design.model.Role;
import com.cidic.design.service.RoleService;


@Service
@Component
@Qualifier(value = "roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier(value = "roleDaoImpl")
	private RoleDao roleDaoImpl;
	
	@Override
	public void createRole(Role role) {
		
		roleDaoImpl.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDaoImpl.deleteRole(roleId);
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDaoImpl.correlationPermissions(roleId, permissionIds);
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDaoImpl.uncorrelationPermissions(roleId, permissionIds);
	}

}
