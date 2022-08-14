<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 13/08/2022
  Time: 09:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Consultas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="row">
        <div class="col-12">
            <c:if test="${param['result']}">
                <p><c:out value="${param['message']}"></c:out></p>
            </c:if>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-6">PERSONAS</div>
                        <div class="col-6 text-end">
                            <a href="create" class="btn btn-outline-success btn-sm">
                                <i data-feather="plus"></i> RegistraR
                            </a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-sm table-hover datatable">
                        <thead>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>curp</th>
                        <th>cumple</th>
                        <th>Acciones</th>
                        </thead>
                        <tbody>
                        <c:forEach var="persona" items="${personas}" varStatus="status">
                            <tr>
                                <td><c:out value="${status.count}"></c:out></td>
                                <td><c:out value="${persona.nombre}"></c:out></td>
                                <td><c:out value="${persona.subname}"/></td>
                                <td><c:out value="${persona.curp}"/></td>
                                <td><c:out value="${persona.cumple}"/></td>
                                <td>
                                    <a href="get?id=${persona.id}" class="btn btn-warning
                                    btn-sm"><i data-feather="edit">Modificar</i></a>
                                    <form action="delete" method="post">
                                        <input type="hidden" value="${persona.id}" name="id"/>
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <i data-feather="trash-2">Eliminar</i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>