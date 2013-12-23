<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>


    <jsp:include page="../menu.jsp"/>
    <div class="span12 pagination-justify">
        <body>
            <h1>Ver Usuario ${usuario.nombre}</h1>

            <div class="well">
                <a href="<c:url value="/usuario"/>" class="btn btn-primary"><i class="icon-list icon-white" ></i>Documentos </a>
                <a href="<c:url value="/usuario/nuevo"/>" class="btn btn-primary"><i class="icon-user icon-white" ></i>Nuevo</a>

            </div>

            <div  class="row-fluid" >
                <h4>R.P.E</h4>
                <div>${usuario.username}</div>
            </div>
            <div  class="row-fluid" >
                <h4>Nombre</h4>
                <div>${usuario.nombre}</div>
            </div>
            <div class="row-fluid">
                <h4>Apellido Paterno</h4>
                <div>${usuario.apPaterno}</div>
            </div>
            <div class="row-fluid">
                <h4>Apellido Materno</h4>
                <div>${usuario.apMaterno}</div>
            </div>

            <div class="row-fluid">
                <h4>Correo</h4>
                <div>${usuario.correo}</div>
            </div>
            <div class="row-fluid">
                <h4>Puesto</h4>
                <div>${usuario.puesto}</div>
            </div>
            <div class="row-fluid">
                <h4>Oficina</h4>
                <div>${usuario.oficina}</div>
            </div>



            <div class="well"> 
                <a href="<c:url value="/usuario/elimina/${usuario.id}"/>" class="btn btn-danger" onclick="return confirm('¿Seguro que quiere eliminar el usuario ${usuario.nombre}?');"><i class="icon-trash icon-white" ></i>Eliminar</a>
            </div>
            <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
            <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
            <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        </body>
    </div>
</html>
