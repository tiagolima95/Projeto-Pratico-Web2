<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
    <h1>Lista de Clientes</h1>
    <ul>
        <!-- Itera sobre a lista de clientes -->
        <c:forEach var="cliente" items="${cliente}">
            <li>Nome: ${cliente.nome} - E-mail: ${cliente.email} - Telefone: ${cliente.telefone}</li>
        </c:forEach>
    </ul>
</body>
</html>