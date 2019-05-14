<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<div class="clientView">
    <h3>Client's name:</h3><br/>
    <div class="clientName"><c:out value="${client.name}" /></div><br/>
    <h5>Client's bills:</h5><br/>
    <c:forEach items="${simpleBillList}" var="bill" >
        <%--Type: <c:out value = "${bill.type}"/><br/>--%>
        Name: <c:out value = "${bill.name}"/><br/>
        Money: <c:out value = "${bill.money}"/><br/><br/>
    </c:forEach>
</div>
<h3>Create bill:</h3>
<div>
    <a href="<c:url value="/${client.id}/simple" />">Simple</a><br/>
    <a href="<c:url value="/${client.id}/deposit" />">Deposit</a><br/>
    <a href="<c:url value="/${client.id}/credit" />">Credit</a><br/>
</div>
</body>
</html>
