<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="./shared/head.jsp" %>

<div class="base-content register-layout">
    <h2>Register Company</h2>
        <div class="form-horizontal">
        
			<form:form action="processCompanyForm" method="POST" modelAttribute="company">
			<br/>
				<div class="form-group">
			        <label class="control-label col-md-2" >User Name</label> 
				    <div class="col-md-10">
	                  	<form:input class="form-control" type="text" path="userName" placeholder="User name" />
	                  	<div class="text-danger"></div>
	                </div>
			    </div>
				<div class="form-group">
			        <label class="control-label col-md-2" >Email</label> 
				    <div class="col-md-10">
	                  <form:input class="form-control" type="email" path="email" placeholder="email" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
				<div class="form-group">
			        <label class="control-label col-md-2" >Password</label> 
				    <div class="col-md-10">
	                 <form:input  class="form-control" type="password" path="password" placeholder="password" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
				
				<div class="form-group">
			        <label class="control-label col-md-2" >Name</label> 
				    <div class="col-md-10">
	                 <form:input  class="form-control" type="text" path="name" placeholder="Company Name" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
			    
				<div class="form-group">
			        <label class="control-label col-md-2" >City</label> 
				    <div class="col-md-10">
	                 <form:input  class="form-control" type="text" path="city" placeholder="City" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
	
				<div class="form-group">
			        <label class="control-label col-md-2" >State</label> 
				    <div class="col-md-10">
	                 <form:input  class="form-control" type="text" path="state" placeholder="State" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
			    <div class="form-group">
                   <div class="col-md-offset-2 col-md-10">
				      <input type="submit" />
			        </div>
                </div>
			</form:form>
		</div>
</div>
<%@ include file="./shared/bundlejs.jsp" %>
</body>
</html>