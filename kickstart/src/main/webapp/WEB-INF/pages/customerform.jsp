<%@ include file="/taglibs.jsp"%>

<head>
    <title>Customer Information</title>
    <%-- Calendar Setup - put in decorator if needed in multiple pages --%>
    <link  href="${ctx}/styles/calendar.css" type="text/css"  rel="stylesheet"/>
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
            <button id="cal" type="button" style="width: 30px"> ... </button>
        <form:errors path="customerSince" cssClass="fieldError"/>
    </li>

    <c:if test="${not empty customer.orders}">
    <li class="info" style="margin-left: 10px">
        <label class="desc">Orders</label>
    </li>
    <li style="margin-left: 20px">
        <c:forEach var="order" items="${customer.orders}">
            <strong>Order Id:</strong> ${order.id}<br/>
            <div style="display: inline; margin-left: 20px">
            <c:forEach var="item" items="${order.orderItems}">
                <strong>Product Id:</strong> ${item.product.id}
                <strong>Description:</strong> ${item.product.description}
            </c:forEach>
            </div>
        </c:forEach>
    </li>
    </c:if>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="Save"/>
      <%--<c:if test="${not empty param.id}">
        <input type="submit" class="button" name="delete" value="Delete"/>
      </c:if>--%>
      	<input type="submit" class="button" name="_cancel" value="Cancel" onclick="bCancel=true"/>
    </li>
</ul>
</form:form>

<script type="text/javascript">
    //Form.focusFirstElement($('userForm'));
    Calendar.setup(
    {
        inputField  : "customerSince", // id of the input field
        ifFormat    : "%m/%d/%Y",      // the date format
        button      : "cal"    // id of the button
    }
    );
</script>

<%--
<v:javascript formName="user" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
--%>
