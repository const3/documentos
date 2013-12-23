<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>
    <body class="span12">
        <h1>Nuevo Usuario</h1>


        <c:url var="nuevo" value="/usuario/crea"/>
        <form:form action="${nuevo}" method="post" commandName="usuario" >
            <form:errors path="*">
                <div class="alert alert-block alert-error fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <c:forEach items="${messages}" var="message">
                        <p>${message}</p>
                    </c:forEach>
                </div>
            </form:errors>
            <fieldset >

                <div class="row-fluid">
                    <s:bind path="usuario.username">
                        <div  class="row-fluid <c:if test='${not empty status.errorMessages}'>error</c:if>">

                                <label for="username" class="col-sm-1">
                                    R.P.E<span class="required-indicator">*</span>
                                </label>
                                <div class="col-xs-2">
                                <form:input     path="username" maxlength="128" class="form-control" required="true" />
                            </div>
                        </div>
                    </s:bind>
                    <s:bind path="usuario.password">
                        <div class="row-fluid <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="password">
                                    Password<span class="required-indicator">*</span>
                                </label>
                            <form:input path="password"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="usuario.nombre">
                        <div class="row-fluid <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="nombre">
                                    Nombre<span class="required-indicator">*</span>
                                </label>
                            <form:input path="nombre"  required="true"  />
                        </div>
                    </s:bind>
                </div>
                <s:bind path="usuario.apPaterno">
                    <div class="form-horizontal <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="apPaterno">
                                Apellido Paterno<span class="required-indicator">*</span>
                            </label>
                        <form:input path="apPaterno" maxlength="128" required="true" />
                    </div>
                </s:bind>
                <s:bind path="usuario.apMaterno">
                    <div class="form-horizontal <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="apMaterno">
                                Apellido Materno<span class="required-indicator">*</span>
                            </label>
                        <form:input path="apMaterno" maxlength="128" required="true" />
                    </div>
                </s:bind>
                <s:bind path="usuario.correo">
                    <div class="form-horizontal <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="correo">
                                Correo<span class="required-indicator">*</span>
                            </label>
                        <form:input path="correo" maxlength="128" required="true" />
                    </div>
                </s:bind>
                <s:bind path="usuario.puesto">
                    <div class="form-horizontal <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="puesto">
                                Puesto<span class="required-indicator">*</span>
                            </label>
                        <form:input path="puesto" maxlength="128" required="true" />
                    </div>
                </s:bind>
                <s:bind path="usuario.iniciales">
                    <div class="form-horizontal <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="iniciales">
                                Iniciales<span class="required-indicator">*</span>
                            </label>
                        <form:input path="iniciales" maxlength="128" required="true" />
                    </div>
                </s:bind>
                <s:bind path="usuario.oficina">
                    <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="oficina">
                                Oficina
                                <span class="required-indicator">*</span>
                            </label>
                            <select name="oficina">  
                                <option value="DA" selected>Administración</option>  
                                <option value="DCL">Calidad</option>  
                                <option value="DCFE">CFEfectiva</option>  
                                <option value="DC">Comercial</option>  
                                <option value="DCSC">CSC</option>  
                                <option value="DZ">Distribución</option>  
                                <option value="DM">Medición</option>  
                                <option value="DP">Personal</option>  
                                <option value="DPL">Planeación</option>  
                                <option value="DI">Sistemas</option>  
                                <option value="SZ">Superintendencia</option>  
                            </select> 
                        </div>
                </s:bind>
                <s:bind path="usuario.administrador">
                    <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="administrador">
                                Administrador
                                <span class="required-indicator">*</span>
                            </label>
                        <form:radiobutton path="administrador"  value="1"  cssClass="span3" />Si<br />
                        <form:radiobutton path="administrador"  value="0"  cssClass="span3" />No<br />
                        <form:errors path="administrador" cssClass="alert alert-error" />
                    </div>
                </s:bind>
                <p class="well" style="margin-top: 10px;">
                    <button type="submit" name="crearBtn" class="btn btn-primary " id="crear" ><i><span class="icon-white icon-check"></span></i>&nbsp;Crear Usuario</button>
                    <a class="btn btn-danger " href="<s:url value='/usuario'/>"><i class="icon-white icon-remove-sign"></i> Cancelar </a>
                </p>
            </fieldset>
        </form:form>

        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("input#username").focus();
            });
        </script>
    </body>
</html>
