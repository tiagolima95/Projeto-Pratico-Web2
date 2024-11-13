<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Produto</title>
</head>
<body>

    <h2>Editar Produto</h2>

    <form action="produtos" method="post">
        <input type="hidden" name="id" value="${produto.id}" />
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="${produto.nome}" required /><br>

        <label for="descricao">Descrição:</label>
        <input type="text" name="descricao" id="descricao" value="${produto.descricao}" required /><br>

        <label for="quantidade_estoque">Quantidade em Estoque:</label>
        <input type="number" name="quantidade_estoque" id="quantidade_estoque" value="${produto.quantidadeEstoque}" required /><br>

        <label for="preco">Preço:</label>
        <input type="number" step="0.01" name="preco" id="preco" value="${produto.preco}" required /><br>

        <label for="categoriaId">Categoria:</label>
        <select name="categoriaId" id="categoriaId" required>
            <c:forEach var="categoria" items="${categorias}">
                <option value="${categoria.id}" ${categoria.id == produto.categoria.id ? 'selected' : ''}>
                    ${categoria.nome}
                </option>
            </c:forEach>
        </select><br>

        <button type="submit">Atualizar Produto</button>
    </form>

</body>
</html>
