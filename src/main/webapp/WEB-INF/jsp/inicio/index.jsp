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
            <div class="hero-unit">
                <h1>Hola</h1>
                <p>En esta parte del sistema se le estara avisando de los cambios y modificaciones hechos recientemente.</p>
                <ul>
                    <li>Se agrego la funcionalidad para agregar feligres.</li>
                    <li>Se agrego un autocomplete para poder agilizar la busqueda de nombres de usuario.</li>
                    <li>Se termino la funcionalidad de reportes.</li>
                </ul>
            </div>



            <hr>

            <footer>
                <p>©  Zambrano 2014</p>
            </footer>

        </div>

        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>

</html>
