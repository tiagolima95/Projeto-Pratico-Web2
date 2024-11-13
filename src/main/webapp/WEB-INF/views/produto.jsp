<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Produtos</title>
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

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #f4f4f9;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.btn {
	padding: 6px 12px;
	border: none;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
}

.btn-alterar {
	background-color: #007bff;
	color: #fff;
	transition: background-color 0.3s ease;
}

.btn-alterar:hover {
	background-color: #0056b3;
}

.btn-excluir {
	background-color: #dc3545;
	color: #fff;
	transition: background-color 0.3s ease;
}

.btn-excluir:hover {
	background-color: #c82333;
}

form {
	margin-top: 20px;
	display: flex;
	flex-direction: column;
	gap: 10px;
}

label {
	font-weight: bold;
}

input[type="text"], input[type="number"] {
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
	width: 100%;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #28a745;
	color: #fff;
	padding: 10px 0;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Produtos</h1>

		<!-- Tabela de produtos -->
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>Descrição</th>
					<th>Preço</th>
					<th>Quantidade em Estoque</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produto" items="${produtos}">
					<tr>
						<td>${produto.nome}</td>
						<td>${produto.descricao}</td>
						<td>R$ ${produto.preco}</td>
						<td>${produto.quantidadeEstoque}</td>
						<td>
							<!-- Link para editar o produto --> <a
							href="${pageContext.request.contextPath}/produtos?id=${produto.id}"
							class="btn btn-alterar">Alterar</a> <!-- Formulário para excluir o produto -->
							<form action="${pageContext.request.contextPath}/excluirProduto"
								method="post" style="display: inline;"
								onsubmit="return confirm('Tem certeza que deseja excluir este produto?');">
								<input type="hidden" name="produtoId" value="${produto.id}">
								<button type="submit" class="btn btn-excluir">Excluir</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h1>Adicionar Novo Produto</h1>
		<form action="${pageContext.request.contextPath}/produtos"
			method="post">
			<label for="nome">Nome:</label> <input type="text" id="nome"
				name="nome" required> <label for="descricao">Descrição:</label>
			<input type="text" id="descricao" name="descricao" required>

			<label for="preco">Preço:</label> <input type="number" id="preco"
				name="preco" step="0.01" required> <label
				for="quantidadeEstoque">Quantidade:</label> <input type="number"
				id="quantidadeEstoque" name="quantidade" required>

			<label for="categoria">Categoria:</label> <select id="categoria"
				name="categoria" required>
				<option value="">Selecione a Categoria</option>
				<c:forEach var="categoria" items="${categorias}">
					<option value="${categoria.id}">${categoria.nome}</option>
				</c:forEach>
			</select> <input type="submit" value="Adicionar Produto">
		</form>
	</div>
</body>
</html>
