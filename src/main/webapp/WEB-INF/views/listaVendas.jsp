<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Vendas</title>
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
            max-width: 900px;
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

        .btn-detalhes {
            background-color: #007bff;
            color: #fff;
            transition: background-color 0.3s ease;
        }

        .btn-detalhes:hover {
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

    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Vendas</h1>

        <!-- Tabela de vendas -->
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
                            <a href="detalhesVenda?id=${venda.id}" class="btn btn-detalhes">Ver Detalhes</a> | 
                            <a href="excluirVenda?id=${venda.id}" class="btn btn-excluir" onclick="return confirm('Tem certeza que deseja excluir esta venda?');">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
