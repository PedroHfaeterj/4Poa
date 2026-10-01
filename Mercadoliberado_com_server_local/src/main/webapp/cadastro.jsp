<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Conta</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Meu E-commerce</h1>
        <div class="menu-usuario">
            <a href="index.jsp">Voltar para a Loja</a>
            <a href="login.jsp">Já tenho conta</a>
        </div>
    </header>

    <div class="container">
        <div class="box-branca">
            <h2 style="text-align: center; margin-bottom: 20px;">Crie sua Conta</h2>
            <!-- O formulário envia os dados para o CadastroServlet -->
            <form action="CadastroServlet" method="POST">
                <label>E-mail:</label>
                <input type="email" name="email" required>
                
                <label>Senha:</label>
                <input type="password" name="senha" required>
                
                <button type="submit" class="btn-azul">Cadastrar e Entrar</button>
            </form>
        </div>
    </div>
</body>
</html>