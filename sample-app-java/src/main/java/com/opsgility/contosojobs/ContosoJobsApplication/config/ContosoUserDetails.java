package com.opsgility.contosojobs.ContosoJobsApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.PrincipalUser;
import com.opsgility.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.dao.UserInfoDao;

//@Service("userDetailsService")
public class ContosoUserDetails  {
/* implements UserDetailsService
    @Autowired
    private UserInfoDao userDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
        throws UsernameNotFoundException {
        UserInfo user = userDao.getUserInfo(username);

return new PrincipalUser(user); 

    }
*/
}
