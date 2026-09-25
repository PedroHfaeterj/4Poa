<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seu Carrinho</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Meu E-commerce</h1>
        <div class="menu-usuario">
            <a href="index.jsp">Voltar para a Loja</a>
            <a href="LogoutServlet">Sair</a>
        </div>
    </header>

    <div class="container">
        <h2>Seu Carrinho de Compras</h2>

        <% if (session.getAttribute("usuarioLogado") == null) { %>
            <div class="box-branca" style="text-align: center;">
                <p>Você precisa estar logado para ver o carrinho.</p><br>
                <a href="login.jsp" class="btn-azul" style="text-decoration: none; display: inline-block;">Fazer Login agora</a>
            </div>
        <% } else { %>
            <p>Comprador: <strong><%= session.getAttribute("usuarioLogado") %></strong></p>
            
            <table class="tabela-carrinho">
                <thead>
                    <tr>
                        <th>Produto</th>
                        <th>Quantidade</th>
                        <th>Preço Unitário</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Smartphone XYZ</td>
                        <td>1</td>
                        <td>R$ 1.500,00</td>
                        <td><a href="#" class="btn-remover">Remover</a></td>
                    </tr>
                    <tr>
                        <td>Fone de Ouvido Bluetooth</td>
                        <td>2</td>
                        <td>R$ 150,00</td>
                        <td><a href="#" class="btn-remover">Remover</a></td>
                    </tr>
                </tbody>
            </table>
            
            <br>
            <h3 style="text-align: right;">Total: R$ 1.800,00</h3>
            <br>
            <div style="text-align: right;">
                <button class="btn-azul" style="width: auto; padding: 15px 30px;" onclick="alert('Compra finalizada!')">Finalizar Compra</button>
            </div>
        <% } %>
    </div>
</body>
</html>