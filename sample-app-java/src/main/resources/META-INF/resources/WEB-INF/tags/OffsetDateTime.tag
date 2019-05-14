<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="date" required="true" type="java.time.OffsetDateTime" %>
<%@ attribute name="pattern" required="false" type="java.lang.String" %>

<c:if test="${empty pattern}">
    <c:set var="pattern" value="yyyy-MM-dd-HH.mm.ss.SSSSSSZ"/>
</c:if>
<!-- <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> -->
<fmt:parseDate value="${date}" pattern="yyyy-MM-dd-HH.mm.ss.SSSSSSZ" var="parsedDate" type="both"/>
<fmt:formatDate value="${parsedDate}" type="both" pattern="${pattern}"/>