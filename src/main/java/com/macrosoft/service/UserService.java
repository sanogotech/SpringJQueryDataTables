package com.macrosoft.service;

import com.macrosoft.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}