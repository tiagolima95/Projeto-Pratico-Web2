<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Inicial</title>
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
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }

        p {
            margin: 15px 0;
        }

        a {
            color: #ffffff;
            background-color: #007bff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 4px;
            display: inline-block;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bem-vindo à Página Inicial!</h1>
        
        <p><a href="realizarVenda">Registrar Venda</a></p>
        <p><a href="produtos">Gerenciar Produtos</a></p>
        <p><a href="clientes">Gerenciar Clientes</a></p>
        <p><a href="vendasRealizadas">Vendas Realizadas</a></p>
    </div>
</body>
</html>
