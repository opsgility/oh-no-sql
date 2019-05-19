<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="./shared/head.jsp" %>
<div style="margin-bottom: 0; height: 550px; width: 100%; padding: 10px; max-width: 88%; background-color: #fff; opacity: .9; position: relative; left: 6%; right: 6%; top: 30px;">
    <h2 style="width: 88%;max-width: 88%;">JobSeeker Profile for  <security:authentication property="principal.username" /></h2>
<c:set value="${viewModel.allSkills}" var="allSkills" />
    <div>
        <div id="user-skills">
            <H3 style="color: cornflowerblue">My Skills</H3>
            <hr />
         <!--  @foreach (var item in Model.Skills)-->
          <c:forEach items="${viewModel.skills}" var="skill">   
                <div>${skill.skill}</div>
          </c:forEach>
        </div>
        <div id="user-resume">
            <H3 style="color: cornflowerblue">My Resume</H3>
            <hr />
           <form:form action="addUpdateResume" method="POST"  modelAttribute="resume">
                <div class="form-horizontal">
                    <div class="form-group">
                         <div class="col-md-10">
                           <form:textarea id="MyResume_Content" path="content" class="form-control" rows="10" cols="160" /> 
                        <form:input type="hidden" path="id" />
                        </div>
                    </div>

                    <div class="form-group update-resume">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Update Resume"  class="btn btn-default" />
                        </div>
                    </div>
                </div>
       </form:form>     

        </div>
<!--  Skills   -->
      <form:form action="addSkills" method="POST"  modelAttribute="viewModel">
            <div class="form-horizontal">

                <hr />
                <div class="form-group multiselect">
               
				<form:select path="allSkills" multiple="true" id="Id" size="10">
					<form:options  items="${skillsList}" itemLabel="skill" itemValue="id" />
				</form:select>
				
                  
                    <div class="memberMultiselect">CTR+ click to select more than one skill.</div>
                </div>
                
                <div class="form-group click-to-add-delete">
                    <div>
                        <input type="submit" value="Delete Skill(s)" name="delete" class="btn btn-default" />
                    </div>
                </div>
                <div class="form-group click-to-add">

                    <div>
                        <input type="submit" value="Add Skill(s)" name="add" class="btn btn-default" />
                    </div>
                </div>
            </div>
</form:form>
    </div>
</div>
	
<table class="table" style="padding:10px; width: 88%;max-width: 88%; background-color: #fff; opacity: .9; position: relative; left: 6%; right: 6%; top: 0;">
    <tr>
        <th>
            Your Applications
        </th>
    </tr>
    <tr>
        <th>Company </th>
        <th>Job Title</th>
        <th>City</th>
        <th>State</th>
        <th>Date Created</th>
        <th></th>
    </tr>

    <!-- @foreach (var item in Model.JobApplications)  -->

     <c:forEach items="${viewModel.jobApplications}" var="job">   
        <tr>
            <td>${job.companyName}</td>
            <td>${job.jobTitle}</td>
            <td>${job.city}</td>
            <td>${job.state}</td>
            <td>${job.createdOnDate}</td>
            <td></td>
        </tr>
   </c:forEach>

</table>
<span style="margin-bottom: 20px;  opacity: .9; position: absolute; left: 6%; right: 6%; bottom: 20px;"> Html.PagedListPager(Model.JobApplications, i => Url.Action("Index", "Member", new { i }))</span>


<%@ include file="./shared/bundlejs.jsp" %>
</body>
</html>