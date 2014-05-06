<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="shortcut icon" href="<c:url value='/images/cfe.png' />" type="image/x-icon" />
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css" /> 
        <title>Inicio</title>
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            .form-signin .form-signin-heading,
            .form-signin .checkbox {
                margin-bottom: 10px;
            }
            .form-signin input[type="text"],
            .form-signin input[type="password"] {
                font-size: 16px;
                height: auto;
                margin-bottom: 15px;
                padding: 7px 9px;
            }

        </style>
    </head>
    <body>

        <div class="container">

            <form class="form-signin" name='f' action="<c:url value='j_spring_security_check' />"
                  method='POST' class='cssform' autocomplete='off'>
                <h2 class="form-signin-heading">Favor de identificarse</h2>
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
                    <p style="color:red;padding: 0 10px 10px 0;">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                    </p>
                </c:if>
                <input type="text" class="input-block-level" placeholder="Usuario" name='j_username'>
                <input type="password" class="input-block-level" placeholder="Contraseña" name='j_password'>
                <label class="checkbox">
                    <input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me' />
                    ¿Recordarme?
                </label>
                <button class="btn btn-large btn-primary" type="submit" >Entrar</button>
            </form>
        </div> <!-- /container -->
        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    </body>
</html>