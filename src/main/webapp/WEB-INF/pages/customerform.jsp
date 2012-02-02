<%@ include file="/taglibs.jsp"%>

<head>
    <title>Customer Information</title>
    <%-- Calendar Setup - put in decorator if needed in multiple pages --%>
    <script type="text/javascript" src="${ctx}/scripts/calendar.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/calendar-setup.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/lang/calendar-en.js"></script>
</head>

<form:form commandName="customer" method="post" action="customerform.htm">
<form:errors path="*" cssClass="error"/>
<form:hidden path="id" />
<ul>
    <li class="info">
        Please fill in customer's information below:
    </li>
    <li>
        <label for="name" class="desc">Name <span class="req">*</span></label>
        <form:input path="name" id="name" cssClass="text medium"/>
        <form:errors path="name" cssClass="fieldError"/>
    </li>
    <li>
        <label for="customerSince" class="desc">Customer Since</label>
        <form:input path="customerSince" cssClass="text date" id="customerSince" size="11"/>
            <button id="cal" type="button" style="width: 30px; margin-left: 5px; line-height: 12px"> ... </button>
        <form:errors path="customerSince" cssClass="fieldError"/>
    </li>

    <c:if test="${not empty customer.orders}">
    <li class="info" style="margin-left: 10px">
        <label class="desc">Orders</label>
    </li>
    <li style="margin-left: 20px">
        <c:forEach var="order" items="${customer.orders}" varStatus="status">
            <fieldset class="fieldset${(status.count % 2 == 0) ? ' odd' : ''}" style="width: 400px">
                <form:hidden path="orders[${status.index}].id"/>
                <form:hidden path="orders[${status.index}].status" id="status${status.index}"/>
                <legend>
                    Order # ${order.id} <span style="font-weight: normal">| Status is <span style="color: orange">${order.status}</span>
                    <c:if test="${order.orderDate != null}"> | Placed on <fmt:formatDate value="${order.orderDate}" dateStyle="short"/></c:if>
                    </span>
                </legend>
                <c:if test="${order.status != 'CANCELLED'}">
                    <input type="submit" value="Cancel Order" class="button"
                           onclick="$('status${status.index}').value = 'CANCELLED'"
                           style="width: 90px; float: right; margin: -20px -21px 0 0"/>
                </c:if>
                <ul style="margin-left: 20px">
                <c:forEach var="item" items="${order.orderItems}" varStatus="status2">
                    <li><strong>Product Id:</strong> ${item.product.id}</li>
                    <li><label><strong>Description:</strong>
                        <form:input path="orders[${status.index}].orderItems[${status2.index}].product.description"
                                cssClass="text medium"/></label>
                    </li>
                    ${(!status2.last) ? '<li class="info" style="margin-left: -20px"/>' : ''}
                </c:forEach>
                </ul>
            </fieldset>
        </c:forEach>
    </li>
    </c:if>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="Save"/>
      	<input type="submit" class="button" name="cancel" value="Cancel" onclick="bCancel=true"/>
    </li>
</ul>
</form:form>

<c:set var="scripts" scope="request">
<script type="text/javascript">
    jQuery("#name").focus();
    Calendar.setup({
        inputField  : "customerSince", // id of the input field
        ifFormat    : "%m/%d/%Y",      // the date format
        button      : "cal"    // id of the button
    });
</script>
</c:set>