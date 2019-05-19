<%@include file="./shared/head.jsp" %>
<c:set value="${jobsList}" var="jobsPageList" />
<div class="base-content admin-details-layout">
    <h2 style="margin-top: 30px; margin-bottom: 50px;">${companyName}</h2>

    <h4>
        <span style="padding-right: 10px">Company Users</span>
          <security:authorize access="isAuthenticated()">
             <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
				<a href="${pageContext.request.contextPath}/AddCompanyUser">Add User</a>
           </security:authorize>
        </security:authorize>
    </h4>
    <div class="admin-users-container">
    <table class="table" style="margin-bottom: 50px;">
        <tr>
            <th>User Name</th>
            <th>User Role</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>

        </tr>
          <c:forEach items="${users}" var="user">  
            <tr>
                <td>${user.username }</td>
                <td>${user.userRole }</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
          </c:forEach>

    </table>
 </div>

    <h4>
        <span style="padding-right: 10px"> Current Job Postings </span>
        <security:authorize access="isAuthenticated()">
          <security:authorize access="hasAnyRole('ROLE_ADMINISTRATOR','ROLE_JOBPOSTER')">
            <a href="${pageContext.request.contextPath}/PostJob">Post Jobs</a>
           </security:authorize>
        </security:authorize>
	</h4>
	 <div class="admin-postings-container">
    <table class="table">
        <tr>
            <th>Job Title</th>
            <th>City</th>
            <th>State</th>
            <th>No of Applicants</th>
            <th>Date Created</th>
            <th></th>
        </tr>

             <c:forEach items="${jobsPageList.pageList}" var="job">  
                <tr>
                    <td>${job.jobTitle}</td>
                    <td>${job.city}</td>
                    <td>${job.state}</td>
                    <td></td>
                    <td>${job.createdOnDate}</td>
                    <td class="view-btn">
                        <form:form action="${pageContext.request.contextPath}/admin/postingDetails" method="POST">
                        <input name="jobId" type="hidden" value="${job.jobPostingID}"/>
                      	<input type="submit"  value="view">
                      	</form:form>
                    </td>
                </tr>
             </c:forEach>

    </table>
    </div>
</div>
<!--  put the pagination here -->
<span style="margin-bottom: 20px;  opacity: .9; position: absolute; left: 6%; right: 6%; bottom: 20px; font-size: 24px;"> 
<c:choose>
    <%-- Show Prev as link if not on first page --%>
    <c:when test="${jobsPageList.firstPage}">
      <span>Prev</span>
    </c:when>
    <c:otherwise>
        <c:url value="/admin/prev" var="url" />                  
        <a href='<c:out value="${url}" />'>Prev</a>
    </c:otherwise>
</c:choose>
<c:forEach begin="1" end="${jobsPageList.pageCount}" step="1"  varStatus="tagStatus">
    <c:choose>
        <%-- In PagedListHolder page count starts from 0 --%>
        <c:when test="${(jobsPageList.page + 1) == tagStatus.index}">
            <span>${tagStatus.index}</span>
        </c:when>
        <c:otherwise>
            <c:url value="/admin/${tagStatus.index}" var="url" />                  
            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:choose>
    <%-- Show Next as link if not on last page --%>
    <c:when test="${jobsPageList.lastPage}">
      <span>Next</span>
    </c:when>
    <c:otherwise>
        <c:url value="/admin/next" var="url" />                  
        <a href='<c:out value="${url}" />'>Next</a>
    </c:otherwise>
</c:choose>

</span>

 <%@include file="./shared/bundlejs.jsp" %>
