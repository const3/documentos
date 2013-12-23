<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>

    <jsp:include page="../menu.jsp"/>

    <body>
        <br>
        <div class="container-fluid">

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="alert alert-error">
                <h1>Uups!!</h1>
                <p>El usuario ${usuarioLogeado.nombre} no está autorizado para usar esta funcionalidad.</p>

            </div>



            <hr>

            <footer>
                <p>©  CFE 2013</p>
            </footer>

        </div>

        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>

</html>
