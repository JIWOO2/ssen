package com.ssafy.api.service;

import com.ssafy.api.request.UserPasswordUpdateReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdateReq;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	User getUserByNickname(String nickname);
	User updateUser(UserUpdateReq updateInfo);
	User updateUserPassword(UserPasswordUpdateReq passwordUpdateInfo);
	boolean deleteUser(String userId);
}
