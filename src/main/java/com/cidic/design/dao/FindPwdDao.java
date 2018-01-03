package com.cidic.design.dao;

import java.util.Optional;

import com.cidic.design.model.FindPwd;

public interface FindPwdDao {

	public int createFindPwd(FindPwd findPwd);
	
	public Optional<FindPwd> getFindPwdByCondition(String email, String validCode);
	
	public Optional<FindPwd> getFindPwdById(int id);
}
