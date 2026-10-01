package br.com.faculdade.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.faculdade.dao.ProdutoDAO;
import br.com.faculdade.model.Produto;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        // Trava de segurança no backend: impede que usuários comuns enviem dados para cá
        if (!"admin@loja.com".equals(session.getAttribute("usuarioLogado"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String acao = request.getParameter("acao");
        ProdutoDAO dao = new ProdutoDAO();

        try {
            if ("adicionar".equals(acao)) {
                String nome = request.getParameter("nome");
                // Converte a vírgula para ponto caso o usuário digite no formato brasileiro
                double preco = Double.parseDouble(request.getParameter("preco").replace(",", "."));
                
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPreco(preco);
                
                dao.salvar(produto);
                
            } else if ("deletar".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deletar(id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar produto: " + e.getMessage());
        }

        // Devolve o admin para a tela do painel após a ação
        response.sendRedirect("admin.jsp");
    }
}