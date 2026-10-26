const API_URL = "http://localhost:8080/produtos";


function carregarProdutos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(produtos => {
            const lista = document.getElementById("lista-produtos");
            lista.innerHTML = ""; 
            
            produtos.forEach(produto => {
                const item = document.createElement("li");
                item.innerHTML = `<strong>${produto.nome}</strong> - R$ ${produto.preco.toFixed(2)} <br> <small>${produto.descricao}</small>`;
                lista.appendChild(item);
            });
        })
        .catch(error => console.error("Erro ao carregar produtos:", error));
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