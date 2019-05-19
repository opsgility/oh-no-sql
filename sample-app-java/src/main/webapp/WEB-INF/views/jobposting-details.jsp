
<%@include file="./shared/head.jsp" %>

<div class="base-content application-details-layout">
    <div class="job-details">
        <h2>
           ${jobInfo.jobTitle }
        </h2>
        <div>
              ${jobInfo.jobDescription}
        </div>
        <div>
              ${jobInfo.createdOnDate}
        </div>
    </div>

        <table class="table base-content table-layout">
            <tr>
                <th></th>
                <th>Applicant User Name</th>
                <th>Applicant Email</th>

                <th></th>
                <th></th>
            </tr>

 			<c:forEach items="${applicants}" var="applicant">
                    <tr>
                        <td></td>
                        <td>${applicant.userName}</td>
                        <td>${applicant.email}</td>
                        <td>${applicant.status}</td>
                        <td></td>
                        <td></td>
                        <td class="view-btn">
                          <form:form method="POST" action="${pageContext.request.contextPath}/JobDetails">
                          	 <input type="hidden" value=" ${jobInfo.jobPostingID}" />
				          	 <div class="apply-button"><input type="submit" value="View" /></div>
				         </form:form>
                     </tr>
            </c:forEach>

        </table>



</div>
  <%@include file="./shared/bundlejs.jsp" %>