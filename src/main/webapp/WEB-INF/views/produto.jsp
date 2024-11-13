<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Produtos</title>
</head>
<body>
	 <h1>Produtos</h1>
    <ul>
        <c:forEach var="produto" items="${produtos}">
            <li>Nome: ${produto.nome} - Descrição: ${produto.descricao} - Preço: R$ ${produto.preco}</li>
        </c:forEach>
    </ul>

    <h1>Adicionar Novo Produto</h1>
    <form action="${pageContext.request.contextPath}/produtos" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="descricao">Descrição:</label>
        <input type="descricao" id="descricao" name="descricao" required><br><br>

        <label for="preco">Preço:</label>
        <input type="preco" id="preco" name="preco" required><br><br>

        <input type="submit" value="Adicionar Cliente">
    </form>

</body>
</html>