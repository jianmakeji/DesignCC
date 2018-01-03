package com.cidic.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.GroupDao;
import com.cidic.design.model.Group;
import com.cidic.design.service.GroupService;

@Service
@Component
@Qualifier(value = "groupServiceImpl")
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	@Qualifier(value = "groupDaoImpl")
	private GroupDao groupDaoImpl;
	
	@Override
	public void createGroup(Group group) {
		groupDaoImpl.createGroup(group);
	}

	@Override
	public void deleteGroup(int id) {
		groupDaoImpl.deleteGroup(id);
	}

	@Override
	public void updateGroup(Group group) {
		groupDaoImpl.updateGroup(group);
	}

	@Override
	public List<Group> getAllGroup() {

		return groupDaoImpl.getAllGroup();
	}

}
