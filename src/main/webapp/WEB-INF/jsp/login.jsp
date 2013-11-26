<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Ingresar</title>
        <link rel="shortcut icon" href="<c:url value='/images/cfe.png' />" type="image/x-icon" />
        <style>
            .errorblock {
                color: #ff0000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
        </style>
    </head>
    <body onload='document.f.j_username.focus();'>
        <h3>Ingresar al sistema</h3>

        <c:if test="${not empty error}">
            <div class="errorblock">
                Your login attempt was not successful, try again.<br /> Caused :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
            </div>
        </c:if>

        <form name='f' action="<c:url value='j_spring_security_check' />"
              method='POST' class='cssform' autocomplete='off'>

            <table>
                <tr>
                    <td>Usuario:</td>
                    <td><input type='text' name='j_username' value=''>
                    </td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type='password' name='j_password' />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit"
                                           value="submit" />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="reset" type="reset" />
                    </td>
                </tr>
            </table>


        </form>
        <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
        <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>
</html>