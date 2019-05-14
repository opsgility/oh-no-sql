<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="./shared/head.jsp" %>
<div style="margin-bottom: 0; height: 550px; width: 100%; padding: 10px; max-width: 88%; background-color: #fff; opacity: .9; position: relative; left: 6%; right: 6%; top: 30px;">
    <h2 style="width: 88%;max-width: 88%;">JobSeeker Profile for  ${param.userName }</h2>

    <div>
        <div id="user-skills">
            <H3 style="color: cornflowerblue">My Skills</H3>
            <hr />
         <!--  @foreach (var item in Model.Skills)-->
          
                <div>List skills here</div>
         
        </div>
        <div id="user-resume">
            <H3 style="color: cornflowerblue">My Resume</H3>
            <hr />
<!--  insert form here  -->
                <div class="form-horizontal">
                    <div class="form-group">
                         <div class="col-md-10">
                         <textarea class="form-control" rows="10" cols="160">
                         Add resume Content here
                         </textarea>
                           
                        </div>
                    </div>

                    <div class="form-group update-resume">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Update Resume" formaction=@Url.Action("AddUpdateResume", new { ResumeId= Model.MyResume.Id, Key = Model.MyResume.Key, UserId = Model.MyResume.UserId }) formmethod="post" class="btn btn-default" />
                        </div>
                    </div>
                </div>
            

        </div>
<!--  insert form here  -->
            <div class="form-horizontal">
                <hr />
                <div class="form-group multiselect">
                <!-- Add multi select here -->
                <listbox id="Id" size="10">
                Multi - select list of skills here
                </listbox>
                  
                    <div class="memberMultiselect">CTR+ click to select more than one skill.</div>
                </div>
                <div class="form-group click-to-add-delete">
                    <div>
                        <input type="submit" value="Delete Skill(s)" formaction=Url.Action("DeleteSkills") formmethod="post" class="btn btn-default" />
                    </div>
                </div>
                <div class="form-group click-to-add">

                    <div>
                        <input type="submit" value="Add Skill(s)" formaction=Url.Action("AddSkills") formmethod="post" class="btn btn-default" />
                    </div>
                </div>
            </div>

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

        <tr>
            <td>item.CompanyName</td>
            <td>item.JobTitle</td>
            <td>item.City</td>
            <td>item.State</td>
            <td>item.JobCreationDate.ToString("dd MMM yyyy")</td>
            <td></td>
        </tr>


</table>
<span style="margin-bottom: 20px;  opacity: .9; position: absolute; left: 6%; right: 6%; bottom: 20px;"> Html.PagedListPager(Model.JobApplications, i => Url.Action("Index", "Member", new { i }))</span>


<%@ include file="./shared/bundlejs.jsp" %>
</body>
</html>