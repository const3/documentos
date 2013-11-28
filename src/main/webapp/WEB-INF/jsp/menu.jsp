<%-- 
    Document   : menu
    Created on : 14/11/2013, 03:43:45 PM
    Author     : develop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CFE</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-theme.min.css' />" type="text/css" /> 
        <link rel="shortcut icon" href="<c:url value='/images/cfe.png' />" type="image/x-icon" />

    </head>
    <nav class="navbar navbar-fixed-top" role="navigation">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div nav nav-pills>
                    <ul class="nav nav-pills">
                        <li class="active"><a href="<c:url value='/'/>">Inicio</a> </li>

                        <li class="brand "><a href="<c:url value='/usuario'/>">Usuarios</a> </li>

                        <!--Dropdown -->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Archivos <b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li> <a href="<c:url value='/documento'/>">Documentos </a></li>
                                <li> <a href="<c:url value='/circular'/>">Circulares </a></li>
                                <li> <a href="<c:url value='/memo'/>">Memos</a></li>
                                <li> <a href="<c:url value='/memoInter'/>">Memos Inter</a></li>
                                <li> <a href="<c:url value='/oficio'/>">Oficios</a></li>
                            </ul>
                        </li>

                    </ul>
                                <p class="navbar-text pull-right">
                                    <text><%= request.getUserPrincipal().getName()%></text> 
                                    <a href="<c:url value='/j_spring_security_logout' />"><i class="icon-off"></i>Salir</a></p>

                </div><!--/.nav-collapse -->
            </div>
        </div>
    </nav>
    <br/>
    <br/>
    <br/>
</html>
