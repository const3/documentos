<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>


    <jsp:include page="../menu.jsp"/>
    <div class="span12 pagination-justify">
        <body>
            <h1>Ver Feligres ${feligres.nombre}</h1>

            <div class="well">
                <a href="<c:url value="/feligres"/>" class="btn btn-primary"><i class="icon-list icon-white" ></i>Sobres </a>

            </div>

            <div  class="row-fluid" >
                <h4>Nombre</h4>
                <div>${feligres.nombre}</div>
            </div>
            <div class="row-fluid">
                <h4>Apellido Paterno</h4>
                <div>${feligres.apellidoPat}</div>
            </div>
            <div class="row-fluid">
                <h4>Apellido Materno</h4>
                <div>${feligres.apellidoPat}</div>
            </div>

            <p class="well" style="margin-top: 10px;">
                <a class="btn " href="<s:url value='/feligres'/>"><i class="icon-list"></i> Lista </a>
            </p>

            <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
            <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
            <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        </body>
    </div>
</html>
