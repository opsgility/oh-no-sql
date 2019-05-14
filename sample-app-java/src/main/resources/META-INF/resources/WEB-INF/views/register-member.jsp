<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="./shared/head.jsp" %>

<div class="base-content register-layout">
    <h2>Register User</h2>
          <div class="form-horizontal">
			<form:form action="processForm" method="POST" modelAttribute="user">
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
                   <div class="col-md-offset-2 col-md-10">
				      <input type="submit" />
			        </div>
             </div>
			
			</form:form>
		</div>
</div>

<%@include file="./shared/bundlejs.jsp" %>
</div>
</body>
</html>