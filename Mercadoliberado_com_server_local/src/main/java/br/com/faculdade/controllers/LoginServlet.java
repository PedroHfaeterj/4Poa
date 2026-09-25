package br.com.faculdade.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.faculdade.dao.UsuarioDAO;
import br.com.faculdade.model.Usuario;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // Consulta o banco de dados em vez de usar valores fixos
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.validarLogin(email, senha);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            // Guarda o e-mail na sessão para identificar quem está logado
            session.setAttribute("usuarioLogado", usuario.getEmail());
            response.sendRedirect("index.jsp");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Erro no Login</title><link rel='stylesheet' href='css/style.css'></head><body>");
            out.println("<div class='container'><div class='box-branca' style='text-align: center;'>");
            out.println("<h2 style='color: #e53935;'>Acesso Negado</h2>");
            out.println("<p>E-mail ou senha incorretos. Verifique seus dados e tente novamente.</p>");
            out.println("<a href='login.jsp' class='btn-azul' style='text-decoration: none;'>Tentar Novamente</a>");
            out.println("</div></div></body></html>");
        }
    }
}