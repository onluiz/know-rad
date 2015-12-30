<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<html>
  <head>
    <link rel="stylesheet" href="<c:url value='/assets/css/arbor/style.css' />" type="text/css">
    <script src="<c:url value='/assets/js/jquery-2.1.4.min.js' />"></script>
    <script src="<c:url value='/assets/js/arbor/arbor.js' />"></script>
    <script src="<c:url value='/assets/js/search-laudos.js' />"></script>
    <script>
      URL_BASE = '<c:url value="/" />';
    </script>
  </head>

  <body>
    <h1><font style="color: #08c;">Teste</font></h1>

    <canvas id="viewport" width="1000" height="1000"></canvas>
  </body>

</html>