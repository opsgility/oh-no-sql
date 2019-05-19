<%@include file="./shared/head.jsp" %>
<div class="base-content register-layout">
    <h2>Post New Job</h2>
    <div class="form-horizontal">
        <hr />
<form:form action="/PostJob/PostNewJob" method="POST" modelAttribute="jobPost">
        <div class="form-group">
        <label class="control-label col-md-2">Title</label>
            <div class="col-md-10">
            <form:input class="form-control" type="text" path="Title" placeholder="Title" />
             <form:errors class="text-danger" path="Title"></form:errors>
            </div>
        </div>

        <div class="form-group">
        <label class="control-label col-md-2">Description</label>
            <div class="col-md-10">
            <form:textarea class="form-control" type="text" rows="10" cols="60"  path="Description" placeholder="Description" />
            <form:errors class="text-danger" path="Description"></form:errors>
            </div>
        </div>
        <div class="form-group multiselect">
            <form:select id="Id" path="Skills" items="${skillsList}" multiple = "true" size="12"/>
             <div class="click-to-add">CTR+ click to select more than one skill.</div>
        </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="submit" value="Post Job" class="btn btn-default" />
                </div>
            </div>
</form:form>
        </div>

<%@include file="./shared/bundlejs.jsp" %>