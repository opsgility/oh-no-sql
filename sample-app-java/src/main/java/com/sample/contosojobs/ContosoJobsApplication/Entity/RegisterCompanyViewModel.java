	package com.sample.contosojobs.ContosoJobsApplication.Entity;

	import javax.validation.constraints.NotNull;
	
public class RegisterCompanyViewModel {

		private String id;
		@NotNull(message="required")
		private String userName;
		
		@NotNull(message="required")
		private String email;
		
		@NotNull(message="required")
		private String password;
		
		@NotNull(message="required")
		private String name;
		
		@NotNull(message="required")
		private String city;
		
		@NotNull(message="required")
		private String state;
		
		public RegisterCompanyViewModel() {
			
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "CompanyInfo [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
					+ ", name=" + name + ", city=" + city + ", state=" + state + "]";
		}
		
	
}
