<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>

    <body class="container-fluid">
        <h1>Lista de Documentos</h1>
        <div class="well">
            <a href="<c:url value="/oficio/nuevo"/>" class="btn btn-primary"><i class="icon-plus-sign icon-white" ></i>Nuevo</a>
        </div>
        <table id="lista" class="table table-striped">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Destinatario</th>
                    <th>Departamento</th>
                    <th>Contenido</th>
                    <th>Remitente</th>
                    <th>Creador</th>
                    <th>Folio</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${oficios}" var="oficio">
                    <tr>
                        <td><a href="<c:url value="/oficio/ver/${oficio.id}"/>"><fmt:formatDate value="${oficio.fecha}" pattern="yyyy-MM-dd" /> </a></td>
                        <td>${oficio.destinatario}</td>
                        <td>${oficio.departamento}</td>
                        <td>${oficio.contenido}</td>
                        <td>${oficio.remitente}</td>
                        <td>${oficio.creador}</td>
                        <td>${oficio.folio}</td>
                    </tr>
                </c:forEach> 
            </tbody>

        </table>
        <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
        <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>
</html>
