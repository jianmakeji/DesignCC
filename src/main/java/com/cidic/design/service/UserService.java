package com.cidic.design.service;

import java.util.Optional;
import java.util.Set;

import com.cidic.design.exception.DCException;
import com.cidic.design.model.User;
import com.cidic.design.model.UserPageModel;

public interface UserService {
	
	public int createUser(User user);
    public int updateUser(User user);
    public int deleteUser(Long userId);

    public int correlationRoles(Long userId, Long... roleIds);
    public int uncorrelationRoles(Long userId, Long... roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByEmail(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
    
    /**
     * 
     * @param email
     * @param activeCode
     * @return 200:激活成功 300:激活码过期 400:激活码不正确 500:邮箱未注册
     */
    public void activeUser(String email, String activeCode) throws DCException;
    
    public int updatePwd(String email, String password,String oldSlot);
    
    public int resetLoginUserPwd(String password);
    
    public Optional<User> checkAuthc(String email);
    
    public UserPageModel getUserByPage(int offset, int limit);
    
    public int updateValidSign(String email, int validValue);
}
