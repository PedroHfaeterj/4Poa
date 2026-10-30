const API_CLIENTES = "http://localhost:8080/clientes";

function cadastrarCliente() {
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const cpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;

    if (!nome || !email || !cpf || !senha) {
        alert("Por favor, preencha todos os campos.");
        return;
    }

    const cliente = {
        nome: nome,
        email: email,
        cpf: cpf,
        senha: senha
    };

    fetch(API_CLIENTES, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cliente)
    })
    .then(response => {
        if (response.ok) {
            alert("Conta criada com sucesso! Faça seu login.");
            window.location.href = "login.html"; 
        } else {
            alert("Erro ao criar conta.");
        }
    })
    .catch(error => console.error("Erro na requisição:", error));
}

function fazerLogin() {
    localStorage.removeItem("usuarioLogado"); 
    
    const email = document.getElementById("emailLogin").value;
    const senha = document.getElementById("senhaLogin").value;

    if (!email || !senha) {
        alert("Preencha e-mail e senha.");
        return;
    }

    fetch(API_CLIENTES)
        .then(response => response.json())
        .then(clientes => {
            const usuarioEncontrado = clientes.find(c => c.email === email && c.senha === senha);

            if (usuarioEncontrado) {
                localStorage.setItem("usuarioLogado", JSON.stringify(usuarioEncontrado));
                
                if (usuarioEncontrado.email === "admin@loja.com") {
                    window.location.href = "admin.html";
                } else {
                    window.location.href = "index.html"; 
                }
            } else {
                alert("E-mail ou senha incorretos.");
            }
        })
        .catch(error => console.error("Erro ao fazer login:", error));
}