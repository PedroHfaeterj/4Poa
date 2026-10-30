function carregarCarrinho() {
    const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
    const lista = document.getElementById("lista-carrinho");
    const labelTotal = document.getElementById("total-carrinho");
    
    lista.innerHTML = "";
    let total = 0;

    if (carrinho.length === 0) {
        lista.innerHTML = "<p style='text-align:center;'>Seu carrinho está vazio.</p>";
        labelTotal.innerHTML = "Total: R$ 0.00";
        return;
    }

    carrinho.forEach((produto, index) => {
        total += produto.preco;
        const item = document.createElement("li");
        item.innerHTML = `
            <span><strong>${produto.nome}</strong></span> 
            <span>
                R$ ${produto.preco.toFixed(2)} 
                <button onclick="removerDoCarrinho(${index})" style="background-color: #dc3545; padding: 5px 10px; margin-left: 15px;">X</button>
            </span>
        `;
        lista.appendChild(item);
    });

    labelTotal.innerHTML = `Total: R$ ${total.toFixed(2)}`;
}

function removerDoCarrinho(index) {
    let carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
    carrinho.splice(index, 1);
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    carregarCarrinho();
}

function finalizarCompra() {
    const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
    const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];

    if (carrinho.length === 0) {
        alert("Adicione produtos ao carrinho antes de finalizar.");
        return;
    }

    const pedido = {
        cliente: {
            id: usuario.id
        },
        produtos: carrinho.map(produto => ({ id: produto.id }))
    };

    fetch("http://localhost:8080/pedidos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(pedido)
    })
    .then(response => {
        if (response.ok) {
            alert("Pedido realizado com sucesso!");
            localStorage.removeItem("carrinho");
            window.location.href = "index.html"; 
        } else {
            alert("Erro ao processar o pedido no servidor.");
        }
    })
    .catch(error => console.error("Erro na requisição:", error));
}

carregarCarrinho();