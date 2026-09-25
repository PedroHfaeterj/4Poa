<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.faculdade.model.Produto" %>
<!DOCTYPE html>
<html>

    <div class="container">
        <h2>Seu Carrinho de Compras</h2>

        <% if (session.getAttribute("usuarioLogado") == null) { %>
            <div class="box-branca" style="text-align: center;">
                <p>Você precisa estar logado para ver o carrinho.</p><br>
                <a href="login.jsp" class="btn-azul" style="text-decoration: none;">Fazer Login agora</a>
            </div>
        <% } else { %>
            <div class="box-branca">
                <p>Comprador: <strong><%= session.getAttribute("usuarioLogado") %></strong></p>
                <br>
                <%
                    List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");
                    if (carrinho == null || carrinho.isEmpty()) {
                        out.println("<p>O seu carrinho está vazio.</p>");
                    } else {
                %>
                <table class="tabela-carrinho" style="width: 100%; text-align: left;">
                    <tr style="border-bottom: 2px solid #ccc;">
                        <th>Produto</th>
                        <th>Preço Unitário</th>
                    </tr>
                    <%
                        double total = 0.0;
                        for (Produto p : carrinho) {
                            total += p.getPreco();
                    %>
                    <tr style="border-bottom: 1px solid #eee;">
                        <td style="padding: 10px 0;"><%= p.getNome() %></td>
                        <td style="padding: 10px 0;">R$ <%= String.format("%.2f", p.getPreco()).replace(".", ",") %></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <br>
                <h3 style="text-align: right; color: #1a237e;">Total: R$ <%= String.format("%.2f", total).replace(".", ",") %></h3>
                <div style="text-align: right; margin-top: 15px;">
                    <button class="btn-azul" onclick="alert('Indo para o pagamento...')">Finalizar Compra</button>
                </div>
                <% } %>
            </div>
        <% } %>
    </div>
</body>
</html>