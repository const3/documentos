<%-- 
    Document   : menu
    Created on : 14/11/2013, 03:43:45 PM
    Author     : develop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en"><head>

        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css" /> 
        <link rel="shortcut icon" href="<c:url value='/images/cfe.png' />" type="image/x-icon" />
        <title>Inicio</title>
    </head>

    <body style="">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="<c:url value='/'/>">Inicio</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <c:if test="${usuarioLogeado.administrador}">
                                <li ><a href="<c:url value='/usuario'/>">Usuarios</a></li>
                                </c:if>
                            <!--Dropdown -->
                            <%--
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Archivos <b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li> <a href="<c:url value='/documento'/>">Documento </a></li>
                                <li class="divider"></li>
                                <li> <a href="<c:url value='/documento/reporte'/>">Reporte</a></li>
                                    <c:if test="${usuarioLogeado.puesto=='jefe'}">
                                    <li class="divider"></li>
                                    <li> <a href="<c:url value='/documento/autorizar'/>">Autorizar</a></li>
                                    </c:if>
                            </ul>
                        </li> <%--
                        <li ><a href="<c:url value='/documento/enviados'/>">Documentos Compartidos</a></li>--%>
                            <li ><a href="<c:url value='/sobres'/>">Diezmos</a></li>
                            <li ><a href="<c:url value='/feligres'/>">Feligresía</a></li>
                        </ul>


                        <p class="navbar-text pull-right">
                            <%= request.getUserPrincipal().getName()%> en sesión  <a onclick="return confirm('¿Desea salir?');" href="<c:url value="/j_spring_security_logout" />"><i class="icon-white icon-off"></i>Salir</a>
                        </p>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>

        <br/>
        <br/>
    </body></html>