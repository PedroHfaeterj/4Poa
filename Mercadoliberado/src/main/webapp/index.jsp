<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meu E-commerce - Início</title>
    <!-- Mudei para v=3 só para garantir que o navegador não use cache -->
    <link rel="stylesheet" href="css/style.css?v=3">
</head>
<body>
    <header>
        <h1>Meu E-commerce</h1>
        
        <div class="menu-usuario">
            <% if (session.getAttribute("usuarioLogado") != null) { %>
                <span>Bem-vindo, <%= session.getAttribute("usuarioLogado") %>!</span>
                <a href="carrinho.jsp">Ver Carrinho</a>
                <a href="LogoutServlet">Sair</a>
            <% } else { %>
                <a href="login.jsp">Entre ou Cadastre-se</a>
                <a href="carrinho.jsp">Carrinho</a>
            <% } %>
        </div>
    </header>

    <!-- Easter Egg do Professor -->
    <marquee behavior="scroll" direction="left" scrollamount="10" style="background-color: #4dd0e1; color: #1a237e; padding: 8px 0; font-weight: bold; font-size: 14px;">
        ⚡ SUPER OFERTAS: Compre agora e garanta pontos extras na média final do semestre! Aproveite as promoções exclusivas! ⚡
    </marquee>

    <!-- O Container desgruda o conteúdo das laterais da tela -->
    <div class="container">
        <h2>Produtos em Destaque</h2>
        
        <!-- O Grid organiza os produtos lado a lado -->
        <div class="grid-produtos">
            
            <!-- Produto 1 -->
            <div class="produto-card">
                <h3>Smartphone XYZ</h3>
                <p class="preco">R$ 1.500,00</p>
                <form action="AdicionarCarrinhoServlet" method="POST">
                    <input type="hidden" name="produtoId" value="1">
                    <button type="submit" class="btn-azul">Adicionar ao Carrinho</button>
                </form>
            </div>

            <!-- Produto 2 -->
            <div class="produto-card">
                <h3>Fone de Ouvido Bluetooth</h3>
                <p class="preco">R$ 150,00</p>
                <form action="AdicionarCarrinhoServlet" method="POST">
                    <input type="hidden" name="produtoId" value="2">
                    <button type="submit" class="btn-azul">Adicionar ao Carrinho</button>
                </form>
            </div>

        </div>
    </div>
</body>
</html>