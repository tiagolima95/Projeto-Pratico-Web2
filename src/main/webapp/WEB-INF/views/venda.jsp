<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Realizar Venda</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	color: #333;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	text-align: center;
	background-color: #ffffff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 600px;
	width: 100%;
}

h2 {
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 12px;
	border: 1px solid #ddd;
	text-align: center;
}

th {
	background-color: #007bff;
	color: #fff;
}

td input[type="number"] {
	width: 60px;
	padding: 5px;
	text-align: center;
	border-radius: 4px;
	border: 1px solid #ddd;
}

label {
	font-size: 16px;
	margin-right: 8px;
}

button {
	background-color: #007bff;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #0056b3;
}

nav ul {
	list-style-type: none;
	padding: 0;
	display: flex;
	justify-content: center;
	gap: 5px;
}

nav ul li a {
	color: #007bff;
	text-decoration: none;
	padding: 8px 12px;
	border: 1px solid #007bff;
	border-radius: 4px;
	font-size: 16px;
	transition: background-color 0.3s ease, color 0.3s ease;
}

nav ul li a:hover {
	background-color: #007bff;
	color: #fff;
}

.client-info {
	margin: 20px 0;
	text-align: left;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Realizar Venda</h2>
		<form action="realizarVenda" method="post">
			<table>
				<thead>
					<tr>
						<th>Produto</th>
						<th>Descrição</th>
						<th>Preço</th>
						<th>Quantidade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produto" items="${produtos}">
						<tr>
							<td>${produto.nome}</td>
							<td>${produto.descricao}</td>
							<td>${produto.preco}</td>
							<td><input type="number" name="quantidade_${produto.id}"
								min="0" max="${produto.quantidadeEstoque}" value="0" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav>
				<ul>
					<c:forEach var="i" begin="1" end="${totalPaginas}">
						<li><a href="?pagina=${i}">${i}</a></li>
					</c:forEach>
				</ul>
			</nav>

			<div class="client-info">
				<h3>Cliente</h3>
				<label for="clienteId"> Selecionar Cliente:</label> <input type="number"
					id="clienteId" name="clienteId" min="1" required />

				<table>
					<c:forEach var="cliente" items="${clientes}">
						<tr>
							<td>${cliente.id}</td>
							<td>${cliente.nome}</td>
							<td>${cliente.telefone}</td>
						</tr>
					</c:forEach>

				</table>

			</div>

			<button type="submit">Realizar Venda</button>
		</form>
	</div>
</body>
</html>
