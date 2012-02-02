<%@ include file="/taglibs.jsp" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <title><sm:title default="Welcome"/> | Spring Kickstart</title>
    <link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css"
          href="${ctx}/resources/styles/deliciouslyblue/theme.css" title="default" />
    <link rel="alternate stylesheet" type="text/css"
          href="${ctx}/resources/styles/deliciouslygreen/theme.css" title="green" />

    <sm:head/>
</head>

<body>
<div id="page">

    <div id="header" class="clearfix">

        <h1 style="cursor: pointer" onclick="location.href='${ctx}/'">Spring Kickstart</h1>

        <div id="branding">
            <a href="http://www.springframework.org" title="Spring - java/j2ee Application Framework">
                <img src="${ctx}/resources/images/powered-by-spring.png" width="125" height="66" alt="Spring Framework"/></a>
        </div>

        <p>Spring 3.1: Profiles, Caching, Servlet 3 and much more...</p>
    </div>

    <div id="content">

        <div id="main">
            <h1><sm:title/></h1>

            <sm:body/>
        </div>

        <div id="sub">
            <h3>Resources</h3>

            <p>The following is a list of resources that will make <a href="http://springsource.org">Spring</a> infinitely easier to use.</p>

            <ul class="glassList">
                <li><a href="http://static.springsource.org/spring/docs/3.1.0.RELEASE/spring-framework-reference/html/">Spring 3.1 Docs</a></li>
                <li><a href="http://static.springsource.org/spring/docs/3.1.x/javadoc-api/">Spring 3.1 API</a></li>
                <li><a href="http://www.springsource.org/tutorials">Spring Tutorials</a></li>
                <li><a href="http://forum.springframework.org/">Spring Forums</a></li>
            </ul>

            <img src="${ctx}/resources/images/image.gif" alt="Click to Change Theme" width="150" height="112" class="right" style="margin: 10px 0 20px 0"
                 id="theme-switcher"/>
        </div>

        <div id="nav">
            <div class="wrapper">
                <h2 class="accessibility">Navigation</h2>
                <ul class="clearfix">
                    <li><a href="${ctx}/" title="Home"><span>Home</span></a></li>
                    <li><a href="${ctx}/customers.htm" title="View Customers"><span>Customers</span></a></li>
                </ul>
            </div>
        </div><!-- end nav -->

    </div><!-- end content -->

    <div id="footer">
        <p>
            <a href="http://www.oswd.org/design/preview/id/2634">Deliciously Blue</a> from <a href="http://www.oswd.org/">OSWD</a> |
            Design by <a href="http://www.oswd.org/user/profile/id/8377">super j man</a>

        </p>
    </div>
    <% if ("dev".equals(System.getProperty("spring.profiles.active"))) { %>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="${ctx}/resources/scripts/stylesheetswitcher.js"></script>
    <script type="text/javascript" src="${ctx}/resources/scripts/application.js"></script>
    <script type="text/javascript">
        window.onload = StyleSheetSwitcher.initialize();
        jQuery('#theme-switcher').on('click', function() {
            StyleSheetSwitcher.setActive((StyleSheetSwitcher.getActive() == 'default') ? 'green' : 'default')
        });
    </script>
    <% } else { %>
    <script type="text/javascript" src="${ctx}/assets/scripts.js"></script>
    <% } %>
    <%= (request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : "" %>
   
</div>
</body>
</html>

