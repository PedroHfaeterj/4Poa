package br.com.faculdade.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // Simulação de banco de dados
        if ("teste@teste.com".equals(email) && "123".equals(senha)) {
            // Sucesso: cria a sessão e manda para a Home
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", email);
            response.sendRedirect("index.jsp");
        } else {
            // Falha: renderiza a página de erro com o nosso estilo CSS
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Erro no Login - Meu E-commerce</title>");
            out.println("    <link rel='stylesheet' href='css/style.css?v=3'>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("    <header>");
            out.println("        <h1>Meu E-commerce</h1>");
            out.println("        <div class='menu-usuario'>");
            out.println("            <a href='index.jsp'>Voltar para a Loja</a>");
            out.println("        </div>");
            out.println("    </header>");
            
            out.println("    <div class='container'>");
            out.println("        <div class='box-branca' style='text-align: center;'>");
            out.println("            <h2 style='margin-bottom: 15px; color: #e53935;'>Acesso Negado</h2>");
            out.println("            <p style='margin-bottom: 25px; color: #555;'>E-mail ou senha incorretos. Verifique seus dados e tente novamente.</p>");
            out.println("            <a href='login.jsp' class='btn-azul' style='text-decoration: none; display: inline-block;'>Tentar Novamente</a>");
            out.println("        </div>");
            out.println("    </div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}