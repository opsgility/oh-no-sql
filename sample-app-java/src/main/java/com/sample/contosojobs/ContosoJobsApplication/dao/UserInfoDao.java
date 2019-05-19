package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;

@Repository
public class UserInfoDao implements IUserInfoDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
	@Lazy
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public UserInfo getUserInfo(String username) {
		String sql = "SELECT u.Username AS name, u.Id, a.RoleID, CONCAT('ROLE_', UPPER(r.Name)) AS role, u.Password AS pass, r.Name AS role" + 
				" FROM Users u INNER JOIN UserRoles a on u.Id=a.UserID" + 
				" JOIN Roles r on a.RoleID=r.Id" + 
				" WHERE u.Status=1 and u.Username = ?";
		

	UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql, new Object[]{username},
		new RowMapper<UserInfo>() {

		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
               UserInfo user = new UserInfo();
               user.setUserName(rs.getString("name"));
               user.setId("Id");
               user.setPassword(rs.getString("pass"));
               user.setRole(rs.getString("role"));
               return user;
           }
   });
	return userInfo;
	}
}
