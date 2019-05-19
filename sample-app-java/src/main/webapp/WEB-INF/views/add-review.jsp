<%@include file="./shared/head.jsp" %>


<div class="base-content rating-list-layout">
    <div class="admin-details-layout">
        <h2>${companyRating.companyName }</h2>


         <form:form action="/processAddReviewForm" method="POST" modelAttribute="rating">
            <div class="form-horizontal">
                <h4>Rating</h4>
                <hr />
                <div class="text-danger"></div>


              <div class="form-group"> <!-- Make Drop Down list -->
				 <label class="control-label col-md-2">Number of Stars</label>
					<div class="col-md-10">
						<form:select path="numberOfStars" class="control-label col-md-2">
                    	 	<form:option value="0" label="Select"/>
                    	 	<form:options items="${starsList}" />
                    	</form:select>  
			        	<div class="text-danger"></div>
			        </div>
               </div>
  			  <div class="form-group">
			        <label class="control-label col-md-2" >Heading</label> 
				    <div class="col-md-10">
	                 <form:input  class="form-control" type="text" path="heading" placeholder="Heading" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>
  			  <div class="form-group">
			        <label class="control-label col-md-2" >Review</label> 
				    <div class="col-md-10">
	                 <form:textarea  class="form-control" rows="10" cols="60"  type="text" path="body" placeholder="Add review" />
	                  <div class="text-danger"></div>
	                </div>
			    </div>


                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="Add Review" class="btn btn-default" />
                    </div>
                </div>
            </div>
        </form:form>


        <div>
        </div>
    </div>
</div>

<%@include file="./shared/bundlejs.jsp" %>
