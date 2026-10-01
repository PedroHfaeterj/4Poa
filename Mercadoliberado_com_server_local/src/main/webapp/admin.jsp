<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.faculdade.dao.ProdutoDAO" %>
<%@ page import="br.com.faculdade.model.Produto" %>
<%@ page import="java.util.List" %>
<%
    // Trava de segurança visual: expulsa quem não estiver logado como admin
    if (!"admin@loja.com".equals(session.getAttribute("usuarioLogado"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel Administrativo - Meu E-commerce</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Painel de Controle</h1>
        <div class="menu-usuario">
            <span style="color: #ffeb3b; font-weight: bold;">Modo Administrador</span>
            <a href="index.jsp">Acessar a Loja</a>
            <a href="LogoutServlet">Sair</a>
        </div>
    </header>

    <div class="container">
        <div class="box-branca">
            <h2 style="margin-bottom: 20px;">Adicionar Novo Produto</h2>
            <form action="ProdutoServlet" method="POST">
                <!-- Este campo oculto avisa o Servlet qual ação executar -->
                <input type="hidden" name="acao" value="adicionar">
                
                <label>Nome do Produto:</label>
                <input type="text" name="nome" required>
                
                <label>Preço (R$):</label>
                <input type="text" name="preco" required placeholder="Ex: 150.00" style="margin-bottom: 20px;">
                
                <button type="submit" class="btn-azul">Salvar no Banco de Dados</button>
            </form>
        </div>

        <h2 style="margin-top: 30px;">Estoque Atual</h2>
        <div class="box-branca">
            <table class="tabela-carrinho" style="width: 100%; text-align: left;">
                <tr style="border-bottom: 2px solid #ccc;">
                    <th>ID</th>
                    <th>Produto</th>
                    <th>Preço</th>
                    <th>Ação</th>
                </tr>
                <%
                    ProdutoDAO dao = new ProdutoDAO();
                    List<Produto> produtos = dao.listarTodos();
                    
                    if (produtos.isEmpty()) {
                        out.println("<tr><td colspan='4' style='padding: 15px 0;'>Nenhum produto cadastrado.</td></tr>");
                    } else {
                        for (Produto p : produtos) {
                %>
                <tr style="border-bottom: 1px solid #eee;">
                    <td style="padding: 10px 0;"><%= p.getId() %></td>
                    <td style="padding: 10px 0;"><%= p.getNome() %></td>
                    <td style="padding: 10px 0;">R$ <%= String.format("%.2f", p.getPreco()).replace(".", ",") %></td>
                    <td style="padding: 10px 0;">
                        <!-- Formulário individual para excluir cada produto -->
                        <form action="ProdutoServlet" method="POST" style="margin: 0;">
                            <input type="hidden" name="acao" value="deletar">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit" style="background: #e53935; color: white; border: none; padding: 6px 12px; cursor: pointer; border-radius: 4px; font-weight: bold;">Excluir</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } 
                %>
            </table>
        </div>
    </div>
</body>
</html>