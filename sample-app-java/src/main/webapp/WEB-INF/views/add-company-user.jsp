
<%@include file="./shared/head.jsp" %>

<div class="base-content register-layout">
    <h2>Add User to Company</h2>

	<form:form action="/AddCompanyUser/AddUser" method="POST" modelAttribute="companyUser">
        <div class="form-horizontal">

            <hr />
           <div class="text-danger"></div> <!-- Validation Summary -->

			<div class="form-group">
				<label class="control-label col-md-2">User Name</label>
					<div class="col-md-10">
					 <form:input class="form-control" type="text"  path="UserName" placeholder="User name" /> <!-- user name binding -->
					 <div class="text-danger"></div>
				</div>
			</div>
			<div class="form-group"> <!-- Make Drop Down list -->
				<label class="control-label col-md-2">Role</label>
					<div class="col-md-10">
					<form:select path = "UserRole" class="control-label col-md-2">
                     <form:option value = "NONE" label = "Select"/>
                     <form:options items = "${roleList}" />
                  </form:select>  
			<div class="text-danger"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">Email</label>
					<div class="col-md-10">
					 <form:input class="form-control" type="email"  path="Email" placeholder="email" /> <!-- user name binding -->
					 <div class="text-danger"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">Password</label>
					<div class="col-md-10">
					 <form:input class="form-control" type="password"  path="password" placeholder="password" /> <!-- user name binding -->
					 <div class="text-danger"></div>
				</div>
			</div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="submit" value="Add User" class="btn btn-default" />
                </div>
            </div>
        </div>
   </form:form>
</div>
<%@include file="./shared/bundlejs.jsp" %>