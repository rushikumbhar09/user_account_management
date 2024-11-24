package com.uam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uam.entities.UserAccount;
import com.uam.repository.UserAccountRepo;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountRepo userAccountRepo;

	@Override
	public String saveOrUpdate(UserAccount userAccount) {
		Integer userId = userAccount.getUserId();
		// UPSERT operation (INSERT or UPDATE)
		UserAccount savedUserAccount = userAccountRepo.save(userAccount);

		if (userId == null) {
			return "User Account is saved";
		} else {
			return "User Account is updated";
		}

	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
		List<UserAccount> accounts = userAccountRepo.findAll();
		return accounts;
	}

	@Override
	public UserAccount getUserAccount(Integer userId) {
		Optional<UserAccount> userAccoutOptional = userAccountRepo.findById(userId);
		if (userAccoutOptional.isPresent()) {
			return userAccoutOptional.get();
		}
		return null;
	}

	@Override
	public boolean deleteUserAcccount(Integer userId) {
		try {
			userAccountRepo.deleteById(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// second way
//		boolean exist=userAccountRepo.existsById(userId);
//		if (exist) {
//		userAccountRepo.deleteById(userId);
//			return true;
//		}
		return false;
	}

	@Override
	public boolean updateUserAccountStatus(Integer userId, String status) {

		try {
			userAccountRepo.updateUserAccountStatus(userId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
