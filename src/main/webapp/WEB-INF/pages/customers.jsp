<%@ include file="/taglibs.jsp" %>

<head>
    <title>Customer List</title>
</head>

<button onclick="location.href='customerform.htm'" class="button" id="addCustomer"
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

<c:set var="scripts" scope="request">
<script type="text/javascript">
    highlightTableRows("customer");
</script>
</c:set>