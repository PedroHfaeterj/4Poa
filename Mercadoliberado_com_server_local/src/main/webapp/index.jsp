<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.faculdade.dao.ProdutoDAO" %>
<%@ page import="br.com.faculdade.model.Produto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meu E-commerce - Início</title>
    
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

   
    <marquee behavior="scroll" direction="left" scrollamount="10" style="background-color: #4dd0e1; color: #1a237e; padding: 8px 0; font-weight: bold; font-size: 14px;">
        ⚡ SUPER OFERTAS: Compre agora e garanta pontos extras na média final do semestre! Aproveite as promoções exclusivas! ⚡
    </marquee>

    <div class="container">
        <h2>Produtos em Destaque</h2>
        
        <div class="grid-produtos">
            <%
                ProdutoDAO dao = new ProdutoDAO();
                List<Produto> produtos = dao.listarTodos();
                
                if (produtos.isEmpty()) {
                    out.println("<p>Nenhum produto cadastrado na loja ainda.</p>");
                } else {
                    for (Produto p : produtos) {
            %>
            <div class="produto-card">
                <h3><%= p.getNome() %></h3>
                <p class="preco">R$ <%= String.format("%.2f", p.getPreco()).replace(".", ",") %></p>
                <form action="AdicionarCarrinhoServlet" method="POST">
                    <input type="hidden" name="produtoId" value="<%= p.getId() %>">
                    <button type="submit" class="btn-azul">Adicionar ao Carrinho</button>
                </form>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</body>
</html>