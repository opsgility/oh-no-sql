package com.opsgility.contosojobs.ContosoJobsApplication.dao;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.UserInfo;

public interface IUserInfoDao {

	public UserInfo getUserInfo(String username);
}
