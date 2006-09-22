<%@ include file="/taglibs.jsp" %>

<head>
    <title>Customer List</title>
</head>

<%--<table class="table">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Customer Since</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td><a href="<c:url value='/customerform.htm?id=${customer.id}'/>">${customer.id}</a></td>
                <td>${customer.name}</td>
                <td><fmt:formatDate value="${customer.customerSince}" dateStyle="medium"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>--%>

<button onclick="location.href='customerform.htm'" class="button"
        style="float: right; margin-top: -30px; width: 100px">Add Customer</button>

<d:table name="customerList" class="table" export="true" id="customer" requestURI="" pagesize="10">
    <d:column title="Id" property="id" sortable="true" href="customerform.htm"
        paramId="id" paramProperty="id"/>
    <d:column title="Name" property="name" sortable="true"/>
    <d:column title="Customer Since" sortProperty="customerSince" sortable="true">
        <fmt:formatDate value="${customer.customerSince}" dateStyle="medium"/>
    </d:column>
    <d:setProperty name="export.pdf.filename" value="customers.pdf"/>
    <d:setProperty name="export.excel.filename" value="customers.xls"/>
</d:table>


<script type="text/javascript">
    highlightTableRows("customer");
</script>