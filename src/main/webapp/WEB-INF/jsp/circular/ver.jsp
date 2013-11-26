<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>


    <jsp:include page="../menu.jsp"/>
    <div class="span12 pagination-justify">
        <body>
            <h1>Ver Circular ${circular.folio}</h1>

            <div class="well">
                <a href="<c:url value="/circular"/>" class="btn btn-primary"><i class="icon-list icon-white" ></i>Documentos </a>
                <a href="<c:url value="/circular/nuevo"/>" class="btn btn-primary"><i class="icon-file icon-white" ></i>Nuevo</a>

            </div>

            <div class="row-fluid">
                <h4>Folio</h4>
                <div>${circular.folio}</div>
            </div>

            <div class="row-fluid">
                <h4>Fecha</h4>
                <div><fmt:formatDate value="${circular.fecha}" pattern="yyyy-MM-dd" /></div>
            </div>
            <div class="row-fluid">
                <h4>Asunto</h4>
                <div>${circular.asunto}</div>
            </div>
            <div  class="row-fluid" >
                <h4>Destinatario</h4>
                <div>${circular.destinatario }</div>
            </div>
            <div class="row-fluid">
                <h4>Departamento</h4>
                <div>${circular.departamento}</div>
            </div>

            <div class="row-fluid">
                <h4>Remitente</h4>
                <div>${circular.remitente}</div>
            </div>
            <div class="row-fluid">
                <h4>Contenido</h4>
                <div>${circular.contenido}</div>
            </div>
            <div class="row-fluid">
                <h4>Creador</h4>
                <div>${circular.creador}</div>
            </div>


            <div class="well"> 
                <a href="<c:url value="/circular/elimina/${circular.id}"/>" class="btn btn-danger" onclick="return confirm('¿Seguro que quiere eliminar el documento ${documento.folio}?');"><i class="icon-trash icon-white" ></i>Eliminar</a>
                <a href="<c:url value="/circular/download/${circular.id}"/>" class="btn btn-success" ><i class="icon-ok icon-white " ></i>Descargar</a>
            </div>
            <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
            <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
            <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        </body>
    </div>
</html>
