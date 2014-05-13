<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>
    <body class="span12">
        <h1>Nuevo Feligres</h1>


        <c:url var="nuevo" value="/feligres/crea"/>
        <form:form action="${nuevo}" method="post" commandName="feligres">
            <form:errors path="*">
                <div class="alert alert-block alert-error fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <c:forEach items="${messages}" var="message">
                        <p>${message}</p>
                    </c:forEach>
                </div>
            </form:errors>
            <fieldset>
                <div class="row-fluid">


                    <s:bind path="feligres.nombre">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="nombre">
                                    Nombre<span class="required-indicator">*</span>
                                </label>
                            <form:input path="nombre" maxlength="128" required="true" />
                        </div>
                    </s:bind>

                    <s:bind path="feligres.apellidoPat">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="apellidoPat">
                                    Apellido Paterno<span class="required-indicator">*</span>
                                </label>
                            <form:input path="apellidoPat"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="feligres.apellidoMat">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="apellidoMat">
                                    Apellido Materno<span class="required-indicator">*</span>
                                </label>
                            <form:input path="apellidoMat"  required="true" />
                        </div>
                    </s:bind>

                </div>
            </fieldset>
            <p class="well" style="margin-top: 10px;">
                <button type="submit" name="crearBtn" class="btn btn-primary " id="crear" ><i class="icon-ok icon-white"></i>&nbsp;Crear Feligres</button>
                <a class="btn " href="<s:url value='/feligres'/>"><i class="icon-remove"></i> Cancelar </a>
            </p>
        </form:form>
        <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
        <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("input#nombre").focus();
            });
        </script>
    </body>
</html>
