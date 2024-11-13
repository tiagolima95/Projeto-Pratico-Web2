<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Realizar Venda</title>
</head>
<body>
    <h2>Realizar Venda</h2>
    <form action="realizarVenda" method="post">
        <table border="1">
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
                        <td>
                            <input type="number" name="quantidade_${produto.id}" min="0" max="${produto.quantidadeEstoque}" value="0" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h3>Cliente</h3>
        <label for="clienteId">Cliente:</label>
        <input type="number" id="clienteId" name="clienteId" min="1" required  />

        <br />
        <button type="submit">Realizar Venda</button>
    </form>
</body>
</html>
