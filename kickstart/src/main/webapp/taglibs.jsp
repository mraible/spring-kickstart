<%@ page language="java" errorPage="/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sm" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:if test="${not empty param.theme}">
    <c:set var="cssTheme" value="${param.theme}" scope="session"/>
</c:if>
<c:if test="${empty param.theme}">
    <c:set var="cssTheme" value="${(empty sessionScope.cssTheme) ? initParam['css-theme'] : sessionScope.cssTheme}"/>
</c:if>
