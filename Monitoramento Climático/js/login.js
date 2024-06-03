function showRegisterForm() {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    loginForm.classList.add('slide-out-left');
    registerForm.classList.add('slide-in-right');

    loginForm.addEventListener('animationend', function() {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
        loginForm.classList.remove('slide-out-left');
        registerForm.classList.remove('slide-in-right');
    }, { once: true });
}
function showLoginForm() {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    registerForm.classList.add('slide-out-right');
    loginForm.classList.add('slide-in-left');

    registerForm.addEventListener('animationend', function() {
        registerForm.style.display = 'none';
        loginForm.style.display = 'block';
        registerForm.classList.remove('slide-out-right');
        loginForm.classList.remove('slide-in-left');
    }, { once: true });
}
function login() {
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-pass').value;

    if (!email || !password) {
        alert('Por favor, preencha todos os campos.');
        return;
    }
    console.log('Tentativa de login com email:', email);
    fetch('http://localhost:3000/login', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            window.location.href = 'index.html';
        } else {
            alert('Falha no login: ' + data.message);
        }
    })
    .catch(error => console.error('Erro:', error));
}
function register() {
    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-pass').value;

    if (!email || !password) {
        alert('Por favor, preencha todos os campos.');
        return;
    }
    console.log('Tentativa de registro com email:', email);

    fetch('/register', {
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
            showLoginForm();
        } else {
            alert('Falha no registro: ' + data.message);
        }
    })
    .catch(error => console.error('Erro:', error));
}
document.addEventListener('DOMContentLoaded', () => {
    console.log('Documento carregado');
    document.querySelectorAll('.login-container > input').forEach(input => {
        input.addEventListener('focus', () => {
            input.style.boxShadow = '0 0 8px rgba(0, 123, 255, 0.5)';
        });
        input.addEventListener('blur', () => {
            input.style.boxShadow = 'none';
        });
    });
});