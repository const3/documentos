<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>

    <body class="span12">
        <h1>Documentos Enviados</h1>

        <table id="lista" class="table table-striped">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Destinatario</th>
                    <th>Departamento</th>
                    <th>Contenido</th>
                    <th>Remitente</th>
                    <th>Creador</th>
                    <th>Folio</th>
                    <th>Asunto</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${circulares}" var="circular">
                    <tr>
                        <td><a href="<c:url value="/circular/ver/${circular.id}"/>"><fmt:formatDate value="${circular.fecha}" pattern="yyyy-MM-dd" /> </a></td>
                        <td>${circular.destinatario}</td>
                        <td>${circular.departamento}</td>
                        <td >${circular.contenido}</td>
                        <td>${circular.remitente}</td>
                        <td>${circular.creador}</td>
                        <td>${circular.folio}</td>
                        <td>${circular.asunto}</td>
                    </tr>
                </c:forEach> 
            </tbody>

        </table>
        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>
</html>
