<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Vendas</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Lista de Vendas</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Data</th>
                <th>Cliente</th>
                <th>Valor Total</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="venda" items="${vendas}">
                <tr>
                    <td>${venda.id}</td>
                    <td>${venda.data}</td>
                    <td>${venda.cliente.nome}</td>
                    <td>${venda.valorTotal}</td>
                    <td>
                        <a href="detalhesVenda?id=${venda.id}">Ver Detalhes</a> | 
                        <a href="excluirVenda?id=${venda.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
