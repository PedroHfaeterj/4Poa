package br.com.faculdade.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.faculdade.dao.ProdutoDAO;
import br.com.faculdade.model.Produto;

@WebServlet("/AdicionarCarrinhoServlet")
public class AdicionarCarrinhoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        
        
        ProdutoDAO dao = new ProdutoDAO();
        Produto produtoSelecionado = dao.buscarPorId(produtoId);
        
        if (produtoSelecionado != null) {
          
            List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new ArrayList<>();
            }
            
            carrinho.add(produtoSelecionado);
            session.setAttribute("carrinho", carrinho);
        }
        
        
        response.sendRedirect("carrinho.jsp");
    }
}