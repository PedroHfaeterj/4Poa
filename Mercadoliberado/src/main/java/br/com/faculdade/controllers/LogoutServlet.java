package br.com.faculdade.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    
    // Usamos doGet porque o link no HTML <a> faz uma requisição do tipo GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Destrói a sessão atual (apaga o "usuarioLogado" da memória)
        session.invalidate();
        
        // Redireciona o usuário de volta para a página inicial
        response.sendRedirect("index.jsp");
    }
}