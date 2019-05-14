<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <title>Contoso Jobs - ${title}</title>
 	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/PagedList.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/site.css" rel="stylesheet" />
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <style>
        .checked {
            color: orange;
        }
    </style>
</head>

 <body>
<%@include file="navigation.jsp" %>
    <div class="container body-content">
        <div style="background-image: url('${pageContext.request.contextPath}/css/DeathtoStock_Desk7.jpg'); height:1080px; width: 1920px;max-width: 100%; max-height: 100%; background-repeat: no-repeat; background-size:cover;
    position: absolute; left: 0; top: 0; opacity: .6; overflow-x: hidden; overflow-y: hidden;">
            <div class="overlay" style="height:1080px; width: 1920px;max-width: 100%;max-height: 100%;  background-color: #000; opacity: .6; position: absolute; left: 0; top: 0; overflow-x: hidden;overflow-y: hidden;">

            </div>