<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Produto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            width: 100%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }

        button {
            background-color: #28a745;
            color: #fff;
            padding: 10px 0;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Editar Produto</h2>

        <form action="produtos" method="post">
            <input type="hidden" name="id" value="${produto.id}" />

            <label for="nome">Nome:</label>
            <input type="text" name="nome" id="nome" value="${produto.nome}" required />

            <label for="descricao">Descrição:</label>
            <input type="text" name="descricao" id="descricao" value="${produto.descricao}" required />

            <label for="quantidade_estoque">Quantidade em Estoque:</label>
            <input type="number" name="quantidade_estoque" id="quantidade_estoque" value="${produto.quantidadeEstoque}" required />

            <label for="preco">Preço:</label>
            <input type="number" step="0.01" name="preco" id="preco" value="${produto.preco}" required />

            <label for="categoriaId">Categoria:</label>
            <select name="categoriaId" id="categoriaId" required>
                <c:forEach var="categoria" items="${categorias}">
                    <option value="${categoria.id}" ${categoria.id == produto.categoria.id ? 'selected' : ''}>
                        ${categoria.nome}
                    </option>
                </c:forEach>
            </select>

            <button type="submit">Atualizar Produto</button>
        </form>
    </div>
</body>
</html>
