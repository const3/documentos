<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <jsp:include page="../menu.jsp"/>
    <body class="span12">
        <h1>Nuevo Documento</h1>


        <c:url var="nuevo" value="/documento/crea"/>
        <form:form action="${nuevo}" method="post" commandName="documento">
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

                    <s:bind path="documento.departamento">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="departamento">
                                    Departamento
                                    <span class="required-indicator">*</span>
                                </label>
                                <select name="departamento">  
                                    <option value="DPL">Esc. Sábatica</option>  
                                    <option value="DI">Comunicación</option>  
                                    <option value="SZ">Mayordomía</option>  
                                </select> 
                            </div>
                    </s:bind>
                    <s:bind path="documento.destinatario">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="destinatario">
                                    destinatario<span class="required-indicator">*</span>
                                </label>
                            <form:input path="destinatario" maxlength="128" required="true" />
                        </div>
                    </s:bind>

                    <s:bind path="documento.remitente">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="remitente">
                                    Remitente<span class="required-indicator">*</span>
                                </label>
                            <form:input path="remitente"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="documento.contenido">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="contenido">
                                    Contenido<span class="required-indicator">*</span>
                                </label>
                            <form:textarea path="contenido"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="documento.asunto">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="asunto">
                                    Asunto<span class="required-indicator">*</span>
                                </label>
                            <form:input path="asunto" maxlength="128" required="true" />
                        </div>
                    </s:bind>

                </div>
                <p class="well" style="margin-top: 10px;">
                    <button type="submit" name="crearBtn" class="btn btn-primary " id="crear" ><i class="icon-ok icon-white"></i>&nbsp;Crear Documento</button>
                    <a class="btn " href="<s:url value='/documento'/>"><i class="icon-remove"></i> Cancelar </a>
                </p>
            </fieldset>
        </form:form>
        <script src="<c:url value='/js/jquery-1.8.1.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.8.23.custom.min.js' />"></script>
        <script src="<c:url value='/js/i18n/jquery.ui.datepicker-es.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("input#titulo").focus();
            });
        </script>
    </body>
</html>
