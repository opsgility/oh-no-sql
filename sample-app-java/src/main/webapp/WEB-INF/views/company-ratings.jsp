<%@include file="./shared/head.jsp" %>

<div class="base-content rating-list-layout">
    <div class="admin-details-layout">
        <h1>Company Rating</h1>
        <hr />
        <h2> ${companyRating.companyName}</h2> 
        <form:form class="apply-button" action="${pageContext.request.contextPath}/CompanyRatings/AddReview" method="POST">
         	<input name="ratingId" type="hidden" value="${companyRating.id}"/>
         	<input name="companyId" type="hidden" value="${companyRating.companyId}"/>
       		<input type="submit"  value="view">
       	</form:form>           

        <div class="rating-container">

              <c:forEach items="${companyRating.ratings}" var="rating">   
                <div style="margin-bottom: 20px;">
                    <div style="font-size: 18px; margin-bottom: 5px;">
                    
							<c:forEach begin="1" end="${rating.numberOfStars}" var="i">
							  <div class="fa fa-star checked"></div>
						
							</c:forEach>
							<c:forEach begin="1" end="${5 - rating.numberOfStars}" var="i">
							  <div class="fa fa-star"></div>
					
							</c:forEach>

                        <div class="fa" style="font-family: inherit;">${rating.heading}</div>
                    </div>
                    <div style="max-width: 80%; margin-bottom: 5px;">${rating.body}</div>
                    <div>${rating.date}</div>
                </div>
                <hr />
               </c:forEach>

        </div>
        <div>

        </div>
    </div>

</div>


<%@include file="./shared/bundlejs.jsp" %>