<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="contacthome.home.page.title"/></title>
</head>
<body>
<html:errors/>
<html:form action="/SearchContact">
    <table>
        <tr>
            <td><html:text property="search" maxlength="200"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" class="btn btn-primary"
                       value=" <bean:message key="form.contact.search.validate" />"/>
                <a href="ContactHome.do" class="btn btn-primary"><bean:message key="form.cancel"/></a>
            </td>
        </tr>
    </table>
</html:form>
<table>
    <thead>
    <tr>
        <th><bean:message key="contact.display.lastname"/></th>
        <th><bean:message key="contact.display.firstname"/></th>
        <th><bean:message key="contact.display.email"/></th>
        <th><bean:message key="contact.display"/></th>
        <th><bean:message key="contact.display.delete"/></th>
    </tr>
    </thead>
    <tbody>
    <logic:present name="contacts">
        <logic:iterate name="contacts" id="contact">
            <tr>
                <td><bean:write name="contact" property="lastName"/></td>
                <td><bean:write name="contact" property="firstName"/></td>
                <td><bean:write name="contact" property="email"/></td>
                <td>
                    <html:form action="/DisplayContact">
                        <html:hidden property="id" value="${contact.id}"/>
                        <input type="submit" value="<bean:message key="contact.display"/>"/>
                    </html:form>
                </td>

                <td>
                    <html:form action="/DeleteContact">
                        <html:hidden property="id" value="${contact.id}"/>
                        <input type="submit" value="<bean:message key="contact.display.delete"/>"/>
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
    </tbody>
</table>
</body>
</html>