<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca Per Fornitore</title>
</head>
<body>
   <c:if test="${ not CHIAVE_RICERCA_FORNITORE.isEmpty()}">
   		<c:set var="fornitore" value="${CHIAVE_RICERCA_FORNITORE.get()}"/>
  	    ${fornitore.nome}
   </c:if>
   
    <c:if test="${ CHIAVE_RICERCA_FORNITORE.isEmpty()}">
   		<c:set var="fornitore" value="${CHIAVE_RICERCA_FORNITORE.get()}"/>
  	    ${fornitore.nome}
   </c:if>
</body>
</html>