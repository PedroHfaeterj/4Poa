package br.com.faculdade.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.faculdade.dao.UsuarioDAO;
import br.com.faculdade.model.Usuario;

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Pega o e-mail e a senha que o usuário digitou
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // Monta o objeto Usuario e manda o DAO salvar
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvar(novoUsuario);
        
        // Redireciona o usuário para a tela de login para ele testar a conta nova
        response.sendRedirect("login.jsp");
    }
}