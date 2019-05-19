<%@include file="./shared/head.jsp" %>

<c:set value="${jobsList}" var="jobsPageList" />
    <form:form action="/" method="GET" class="base-content job-search-layout">
        <input name="Search" placeholder="Search" id="Search" class="job-list-layout"/>
        <input type="submit" value="Submit" class="submit-layout"/>
    <!--    <a ${pageContext.request.contextPath}/Search"></a>  --> 
    </form:form>
    <table class="table base-content table-layout" >
        <tr>
            <th></th>
            <th>Company Name</th>
            <th>Rating</th>
            <th>Job Title</th>
            <th>City</th>
            <th>State</th>
            <th>Date Created</th>
            <th></th>
        </tr>

              <c:forEach items="${jobsPageList.pageList}" var="job">   
                <tr>
                    <td ></td>
                    <td>
                      <form:form action="${pageContext.request.contextPath}/SearchByCompany" method="GET">
                        <input type="hidden" name="SearchByCompany" value="${job.companyName}"/>
                      	<input type="submit"  value="${job.companyName}" class="plain-text-btn company-name"/>
                      </form:form>
                     <td>
                        <a href="${pageContext.request.contextPath}/CompanyRatings?getId=${job.companyID}"> <!-- @Url.Action("CompanyRating", new { CompanyRating = item.CompanyId}) -->
                            <div>
                            
<!-- *************** See Company Rating jsp for binding hints replace with values from NoSql ******************  -->
                            
                            
                                  <c:forEach var = "i" begin = "1" end = "3"> <!-- job.AverageRating -->
								         <div class="fa fa-star checked"></div>
								  </c:forEach>
								  <c:forEach var = "i" begin = "1" end = "2"> <!-- job.AverageRating -->
								         <div class="fa fa-star"></div>
								  </c:forEach>
                            </div>
                        </a>
                    </td>
                    <td>${job.jobTitle}</td>
                    <td>${job.city}</td>
                    <td>${job.state}</td>
                    <td>${job.createdOnDate}</td>
                    <!-- <tags:OffsetDateTime date="${job.createdOnDate}"/>
						<tags:OffsetDateTime date="${job.createdOnDate}" pattern="yyyy-MM-dd-HH.mm.ss.SSSSSSZ"/>
					-->
	
					  <td></td>
                    <td class="view-btn">
                        <form:form action="${pageContext.request.contextPath}/JobDetails" method="POST">
                        <input name="jobId" type="hidden" value="${job.jobPostingID}"/>
                      	<input type="submit"  value="view">
                      	</form:form>
                      	</td>
                    </tr>
                </c:forEach>
    </table>

<span style="margin-bottom: 20px;  opacity: .9; position: absolute; left: 6%; right: 6%; bottom: 20px; font-size: 24px;"> 
<c:choose>
    <%-- Show Prev as link if not on first page --%>
    <c:when test="${jobsPageList.firstPage}">
      <span>Prev</span>
    </c:when>
    <c:otherwise>
        <c:url value="/home/prev" var="url" />                  
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
            <c:url value="/home/${tagStatus.index}" var="url" />                  
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
        <c:url value="/home/next" var="url" />                  
        <a href='<c:out value="${url}" />'>Next</a>
    </c:otherwise>
</c:choose>

</span>


  <%@include file="./shared/bundlejs.jsp" %>
