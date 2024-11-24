package com.uam.service;

import java.util.List;

import com.uam.entities.UserAccount;

public interface UserAccountService {

	public String saveOrUpdate(UserAccount userAccount);
	public List<UserAccount> getAllUserAccounts();
	public UserAccount getUserAccount(Integer userId);
	public boolean deleteUserAcccount(Integer userId);
	public boolean updateUserAccountStatus(Integer userId,String status);
}
