package br.com.faculdade.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdicionarCarrinhoServlet")
public class AdicionarCarrinhoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Define o tipo de conteúdo e codificação para suportar acentos
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        // Se não estiver logado, redireciona para a tela de login
        if (session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Se estiver logado, pega o ID do produto enviado pelo formulário
        String produtoId = request.getParameter("produtoId");
        
        // Log no console do Eclipse
        System.out.println("Produto " + produtoId + " adicionado ao carrinho pelo usuário: " + session.getAttribute("usuarioLogado"));
        
        // Renderiza a página de confirmação estilizada com o CSS do site
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Item Adicionado - Meu E-commerce</title>");
        out.println("    <link rel='stylesheet' href='css/style.css?v=3'>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("    <header>");
        out.println("        <h1>Meu E-commerce</h1>");
        out.println("        <div class='menu-usuario'>");
        out.println("            <a href='index.jsp'>Voltar para a Loja</a>");
        out.println("            <a href='carrinho.jsp'>Ver Carrinho</a>");
        out.println("        </div>");
        out.println("    </header>");
        
        out.println("    <div class='container'>");
        out.println("        <div class='box-branca' style='text-align: center;'>");
        out.println("            <h2 style='margin-bottom: 15px;'>Sucesso!</h2>");
        out.println("            <p style='margin-bottom: 25px; color: #555;'>Produto adicionado ao seu carrinho com sucesso.</p>");
        out.println("            <div style='display: flex; gap: 10px; flex-direction: column;'>");
        out.println("                <a href='carrinho.jsp' class='btn-azul' style='text-decoration: none;'>Ir para o Carrinho</a>");
        out.println("                <a href='index.jsp' style='color: #1a237e; text-decoration: none; font-size: 14px; margin-top: 10px;'>Continuar Comprando</a>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </div>");
        
        out.println("</body>");
        out.println("</html>");
    }
}