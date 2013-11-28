<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>

    <jsp:include page="../menu.jsp"/>

    <body>


        <h1>Documentos CFE</h1>
        <p class="alert alert-block alert-info"><strong>

                En esta sección del sistema se estarán anunciando los cambio y avances programados.
                <br/>
                Agradecemos su paciencia y cooperación.
                <br/>
                Esta semana se trabajo en lo que fue:
                <br/>
                Usuario, actualmente ya se guardan en una base de datos los usuario con contraseña.
                <br/>
                El método para editar los archivos, aun esta en proceso, ya que se esta presentando un pequeño problema al actualizar.
            </strong></p>
        <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
        <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>

</html>
