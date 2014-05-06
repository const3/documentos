<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>

    <body class="span12">
        <h1>Lista de Feligresía</h1>
        <div class="well">
            <a href="<c:url value="/feligres/nuevo"/>" class="btn btn-primary"><i class="icon-file icon-white" ></i>Nuevo</a>
        </div>
        <table id="lista" class="table table-striped">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Nombre</th>
                    <th>Apellido Materno</th>
                    <th>Apellido Paterno</th>
                    
            </thead>
            <tbody>
                <c:forEach items="${feligreses}" var="feligres">
                    <tr>
                        <td><a href="<c:url value="/feligres/ver/${feligres.id}"/>"><fmt:formatDate value="${feligres.fecha}" pattern="yyyy-MM-dd" /> </a></td>
                        <td>${feligres.nombre}</td>
                        <td>${feligres.apellidoPat}</td>
                        <td>${feligres.apellidoMat}</td>
                        <td><a href="<c:url value="/feligres/edita/${feligres.id}"/>">Editar </a></td>
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
