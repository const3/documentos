<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="menu.jsp"/>

    <body >
        <h1>Lista de Usuarios</h1>
        <div class="well-small">
            <a href="<c:url value="/usuario/nuevo"/>" class="btn btn-primary"><i class="icon-file icon-white" ></i>Nuevo</a>
        </div>
        <table id="lista" class="table table-striped well-small">
            <thead>
                <tr>
                    <th>RPE</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Correo</th>
                    <th>Puesto</th>
                    <th>Oficina</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td><a href="<c:url value="/usuario/ver/${usuario.id}"/>">${usuario.username}</a></td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.apPaterno}</td>
                        <td>${usuario.apMaterno}</td>
                        <td>${usuario.correo}</td>
                        <td>${usuario.puesto}</td>
                        <td>${usuario.oficina}</td>
                    </tr>
                </c:forEach> 
            </tbody>

        </table>

        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>
</html>
