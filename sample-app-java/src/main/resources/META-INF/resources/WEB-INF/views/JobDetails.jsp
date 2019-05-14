
<%@include file="./shared/head.jsp" %>
<h2>JobDetails</h2>

<div class="base-content job-details-layout">

    <div class="container-details">
        <h1> ${Job.jobTitle}</h1>
        <hr />
        <div class="details-left">
            <h3>${Job.jobDescription}</h3>
            <div class="job-description">
              JobDescription binding
            </div>
            <span>Job Id: ${Job.jobPostingID} </span><span class="left-space-20"> Posted on:  ${Job.createdOnDate} </span>

            <form:form method="POST" action="${pageContext.request.contextPath}/Apply">
            <div class="apply-button"><input type="submit" value="APPLY" /></div>
            </form:form>
      
        </div>

        <div class="details-right">

            <img src="${pageContext.request.contextPath}css/Office-01-WF.png" />
            <h3>${Job.companyName}</h3>

            <div>
                Average rating is (Average Rating) stars
                <a href="${pageContext.request.contextPath}/CompanyRating/CompanyId">

					<c:forEach var = "i" begin = "1" end = "3"> <!-- job.AverageRating -->
					<div class="fa fa-star checked"></div>
					</c:forEach>
					<c:forEach var = "i" begin = "1" end = "2"> <!-- job.AverageRating -->
					<div class="fa fa-star"></div>
					</c:forEach>
                </a>
            </div>
            <hr />
            <div>
                At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium
                voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati
                cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi,
                id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio.
                Nam libero tempore.
            </div>
            <div>${Job.city}</div>
            <div>${Job.jobTitle}</div>
            <span>Company Id: ${Job.companyID}</span>
        </div>
    </div>


</div>
  <%@include file="./shared/bundlejs.jsp" %>