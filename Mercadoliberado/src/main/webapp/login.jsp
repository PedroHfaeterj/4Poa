<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css?v=2">
</head>
<body>
    <header>
        <h1>Meu E-commerce</h1>
        <div class="menu-usuario">
            <a href="index.jsp">Voltar para a Loja</a>
        </div>
    </header>

    <div class="container">
        <div class="box-branca">
            <h2 style="text-align: center; margin-bottom: 20px;">Faça seu Login</h2>
            <form action="LoginServlet" method="POST">
                <label>E-mail:</label>
                <input type="email" name="email" required>
                
                <label>Senha:</label>
                <input type="password" name="senha" required>
                
                <button type="submit" class="btn-azul">Entrar</button>
            </form>
        </div>
    </div>
</body>
</html>