const API_URL = "http://localhost:8080/produtos";

function carregarProdutos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(produtos => {
            const lista = document.getElementById("lista-produtos");
            if (!lista) return; 

            lista.innerHTML = ""; 
            
            produtos.forEach(produto => {
                const item = document.createElement("li");
                const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
                let botaoHTML = '';
                
                // Prevenção de erro: Se o preço for nulo, define como 0.00
                const precoFormatado = produto.preco != null ? produto.preco.toFixed(2) : "0.00";
                const precoParaCarrinho = produto.preco != null ? produto.preco : 0.0;
                
                if (usuario && usuario.email === "admin@loja.com") {
                    botaoHTML = `<button onclick="deletarProduto(${produto.id})" style="background-color: #dc3545;">Excluir</button>`;
                } else if (usuario) {
                    botaoHTML = `<button onclick="adicionarAoCarrinho(${produto.id}, '${produto.nome}', ${precoParaCarrinho})" style="background-color: #28a745;">Comprar</button>`;
                }

                item.innerHTML = `
                    <div>
                        <strong>${produto.nome}</strong> - R$ ${precoFormatado} <br> 
                        <small>${produto.descricao}</small>
                    </div>
                    ${botaoHTML}
                `;
                lista.appendChild(item);
            });
        })
        .catch(error => console.error("Erro ao carregar produtos:", error));
}

function deletarProduto(id) {
    if (confirm("Tem certeza que deseja excluir este produto permanentemente?")) {
        fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("Produto removido com sucesso!");
                carregarProdutos(); 
            } else {
                alert("Erro ao remover o produto do banco de dados.");
            }
        })
        .catch(error => console.error("Erro na requisição:", error));
    }
}

function adicionarAoCarrinho(id, nome, preco) {
    let carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
    carrinho.push({ id, nome, preco });
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    alert(nome + " foi adicionado ao seu carrinho!");
}

function salvarProduto() {
    const nome = document.getElementById("nome").value;
    const preco = document.getElementById("preco").value;
    const descricao = document.getElementById("descricao").value;

    const produto = {
        nome: nome,
        preco: parseFloat(preco),
        descricao: descricao
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(produto)
    })
    .then(response => {
        if (response.ok) {
            alert("Produto cadastrado com sucesso!");
            document.getElementById("nome").value = "";
            document.getElementById("preco").value = "";
            document.getElementById("descricao").value = "";
            carregarProdutos(); 
        }
    })
    .catch(error => console.error("Erro ao salvar produto:", error));
}

carregarProdutos();