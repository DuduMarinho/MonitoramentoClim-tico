function register() {
    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-pass').value;

    if (!email || !password) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    console.log('Tentativa de registro com email:', email);

    fetch('http://localhost:3000/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Registro bem-sucedido! Faça login.');
            window.location.href = 'login.html';
        } else {
            alert('Falha no registro: ' + data.message);
        }
    })
    .catch(error => console.error('Erro:', error));
}
