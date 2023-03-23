const login = document.querySelector(".login");
const registerLink = document.querySelector(".register-link");
const loginLink = document.querySelector(".login-link");


registerLink.onclick = () => {
    login.classList.add("register");
};

loginLink.onclick = () => {
    login.classList.remove("register");
};

