function carregarMenu() {
    const nav = document.getElementById("menu-navegacao");
    if (!nav) return;

    const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
    let menuHTML = `<div style="font-size: 1.5em; font-weight: bold;">Põe no Comércio</div><div>`;

    if (usuario) {
        menuHTML += `<span style="margin-right: 15px;">Olá, ${usuario.nome.split(' ')[0]}</span>`;
        
        if (usuario.email === "admin@loja.com") {
            menuHTML += `<a href="index.html">Ver Vitrine</a>`;
            menuHTML += `<a href="admin.html">Painel Admin</a>`;
        } else {
            menuHTML += `<a href="carrinho.html">Meu Carrinho</a>`;
            menuHTML += `<a href="index.html">Produtos</a>`;
        }
        menuHTML += `<a href="#" onclick="sair()">Sair</a>`;
    } else {
        menuHTML += `<a href="login.html">Login</a>`;
        menuHTML += `<a href="cadastro.html">Cadastrar</a>`;
    }
    
    menuHTML += `</div>`;
    nav.innerHTML = menuHTML;
}

function sair() {
    localStorage.removeItem("usuarioLogado");
    window.location.href = "login.html";
}

carregarMenu();