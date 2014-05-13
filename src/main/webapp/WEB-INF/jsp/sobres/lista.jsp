<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>

    <body class="span12">
        <h1>Lista de Informes</h1>
        <div class="well">
            <a href="<c:url value="/sobres/nuevo"/>" class="btn btn-primary"><i class="icon-file icon-white" ></i>Nuevo</a>
            <a href="<c:url value="/sobres/download"/>" class="btn btn-success"><i class="icon-download icon-white" ></i>Reporte</a>
        </div>
        <table id="lista" class="table table-striped">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Nombre</th>
                    <th>Diezmos</th>
                    <th>Ofrendas</th>
                    <th>Primicias</th>
                    <th>Otro</th>
            </thead>
            <tbody>
                <c:forEach items="${sobres}" var="sobre">
                    <tr>
                        <td><a href="<c:url value="/sobres/ver/${sobre.id}"/>"><fmt:formatDate value="${sobre.fecha}" pattern="yyyy-MM-dd" /> </a></td>
                        <td>${sobre.nombre}</td>
                        <td>${sobre.diezmos}</td>
                        <td>${sobre.ofrendas}</td>
                        <td>${sobre.primicias}</td>
                        <td>${sobre.otro}</td>
                        <td><a href="<c:url value="/sobres/edita/${sobre.id}"/>">Editar </a></td>
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
