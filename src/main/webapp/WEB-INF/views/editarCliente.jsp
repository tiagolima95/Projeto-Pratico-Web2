<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cliente</title>
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
        input[type="email"] {
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
        <h2>Editar Cliente</h2>

        <form action="${pageContext.request.contextPath}/clientes" method="post">
            <!-- Envia o ID do cliente para o servidor -->
            <input type="hidden" name="id" value="${cliente.id}" />

            <label for="nome">Nome:</label>
            <input type="text" name="nome" id="nome" value="${cliente.nome}" required />

            <label for="email">E-mail:</label>
            <input type="email" name="email" id="email" value="${cliente.email}" required />

            <label for="telefone">Telefone:</label>
            <input type="text" name="telefone" id="telefone" value="${cliente.telefone}" required />

            <!-- Botão para atualizar o cliente -->
            <button type="submit">Atualizar Cliente</button>
        </form>
    </div>
</body>
</html>
