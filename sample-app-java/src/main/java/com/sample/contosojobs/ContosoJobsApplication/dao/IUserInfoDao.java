package com.sample.contosojobs.ContosoJobsApplication.dao;

import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;

public interface IUserInfoDao {

	public UserInfo getUserInfo(String username);

}
