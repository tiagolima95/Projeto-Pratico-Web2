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
        <c:forEach var="cliente" items="${clientes}">
            <li>Nome: ${cliente.nome} - E-mail: ${cliente.email} - Telefone: ${cliente.telefone}</li>
        </c:forEach>
    </ul>

    <h1>Adicionar Novo Cliente</h1>
    <form action="${pageContext.request.contextPath}/clientes" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone" required><br><br>

        <input type="submit" value="Adicionar Cliente">
    </form>

</body>
</html>