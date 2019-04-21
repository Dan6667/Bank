<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<H1>Clients:</H1><br/>
<ul class="clientList">
    <c:forEach items="${clientList}" var="client" >
        <a href = "<c:url value="/${client.id}" />"><c:out value = "${client.name}"/></a><br/>
    </c:forEach>
</ul>
</body>
</html>
