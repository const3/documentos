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


        <c:url var="actualiza" value="/sobres/actualiza"/>
        <form:form commandName="sobres" action="${actualiza}" method="post" >
            <form:hidden path="id" />
            <form:hidden path="version" />
            <form:errors path="*">
                <div class="alert alert-block alert-error fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <c:forEach items="${messages}" var="message">
                        <p>${message}</p>
                    </c:forEach>
                </div>
            </form:errors>

            ${sobres.id}
            <fieldset>
                <div class="row-fluid">

                 
                    <s:bind path="sobres.nombre">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="nombre">
                                    Nombre<span class="required-indicator">*</span>
                                </label>
                            <form:input path="nombre" maxlength="128" required="true" />
                        </div>
                    </s:bind>

                    <s:bind path="sobres.diezmos">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="diezmos">
                                    Diezmos<span class="required-indicator">*</span>
                                </label>
                            <form:input path="diezmos"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="sobres.ofrendas">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="ofrendas">
                                    Ofrendas<span class="required-indicator">*</span>
                                </label>
                            <form:input path="ofrendas"  required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="sobres.primicias">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="primicias">
                                    Primicias<span class="required-indicator">*</span>
                                </label>
                            <form:input path="primicias" maxlength="128" required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="sobres.otro">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="otro">
                                    Otro<span class="required-indicator">*</span>
                                </label>
                            <form:input path="otro" maxlength="128" required="true" />
                        </div>
                    </s:bind>
                </div>
            </fieldset>
            <p class="well" style="margin-top: 10px;">
                <button type="submit" name="actualizarBtn" class="btn btn-primary " id="actualizar" ><i class="icon-ok icon-white"></i>&nbsp;Actualizar Informe</button>
                <a class="btn " href="<s:url value='/sobres'/>"><i class="icon-remove"></i> Cancelar </a>
            </p>

        </form:form>
        <script src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("input#titulo").focus();
            });
        </script>
    </body>
</html>
