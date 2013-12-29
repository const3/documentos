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
            <h1>Ver Memo ${memo.folio}</h1>

            <div class="well">
                <a href="<c:url value="/memo"/>" class="btn btn-primary"><i class="icon-list icon-white" ></i>Documentos </a>
                <a href="<c:url value="/memo/nuevo"/>" class="btn btn-primary"><i class="icon-plus-sign icon-white" ></i>Nuevo</a>

            </div>

            <div class="row-fluid">
                <h4>Folio</h4>
                <div>${memo.folio}</div>
            </div>

            <div class="row-fluid">
                <h4>Fecha</h4>
                <div><fmt:formatDate value="${memo.fecha}" pattern="yyyy-MM-dd" /></div>
            </div>
            <div class="row-fluid">
                <h4>Asunto</h4>
                <div>${memo.asunto}</div>
            </div>
            <div  class="row-fluid" >
                <h4>Destinatario</h4>
                <div>${memo.destinatario }</div>
            </div>
            <div class="row-fluid">
                <h4>Departamento</h4>
                <div>${memo.departamento}</div>
            </div>

            <div class="row-fluid">
                <h4>Remitente</h4>
                <div>${memo.remitente}</div>
            </div>
            <div class="row-fluid">
                <h4>Contenido</h4>
                <div>${memo.contenido}</div>
            </div>
            <div class="row-fluid">
                <h4>Creador</h4>
                <div>${memo.creador}</div>
            </div>


            <div class="well"> 
                <c:if test="${memo.status=='AUT'}">
                    <a href="<c:url value="/memo/envia/${memo.id}"/>" class="btn btn-danger" ><i class="icon-envelope icon-white" ></i>Envia</a>
                </c:if>
                <a href="<c:url value="/memo/elimina/${memo.id}"/>" class="btn btn-danger" onclick="return confirm('¿Seguro que quiere eliminar el documento ${documento.folio}?');"><i class="icon-trash icon-white" ></i>Eliminar</a>
                <a href="<c:url value="/memo/download/${memo.id}"/>" class="btn btn-success" ><i class="icon-download icon-white " ></i>Descargar</a>
            </div>
            <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>

            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        </body>
    </div>
</html>
