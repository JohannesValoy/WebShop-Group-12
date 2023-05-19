const login = document.querySelector(".login");
const registerLink = document.querySelector(".register-link");
const loginLink = document.querySelector(".login-link");
const title = document.getElementById("title");
let firstTimeLocation = document.location.pathname;

registerLink.onclick = () => {
    funnySwitchingButton("/register");
    switchToRegister();
}
    

window.focus()
window.onload = function() {
    document.getElementById('username').focus();
};

loginLink.onclick = () => {
    funnySwitchingButton("/login");
    switchToLogin();
};

function funnySwitchingButton(url) {
    if(document.location.pathname === firstTimeLocation){
        history.pushState(url, null, url);
        document.title = (url.charAt(1).toUpperCase() + url.slice(2));
    }
    else{history.back();}
}

function switchToLogin() {
    login.classList.remove("register");
    title.innerHTML = "Login";
}

function switchToRegister() {
    login.classList.add("register");
    title.innerHTML = "Register";
}

addEventListener("popstate", () => {
    if ((history.state == null ? firstTimeLocation : history.state) === "/register") {
        login.classList.add("register");
    }
    else {login.classList.remove("register");}
    const title = String(history.state == null ? firstTimeLocation : history.state);
    document.title = (title.charAt(1).toUpperCase() + title.slice(2));
});

const username = document.getElementById("signUpUsername");
const nameMessage = document.getElementById("name-mismatch");
const password = document.getElementById("signUpPassword");
const confirmPassword = document.getElementById("confirmSignUpPassword");
const passMessage = document.getElementById("pass-mismatch");
const submitButton = document.getElementById("submitButton");
const termsCheckbox = document.getElementById("termsCheckbox");
function checkPasswordAlike() {
    const regexName = /^([A-Za-z0-9]){2,}$/;
    const regexPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;

    if (username.value === "") {
        nameMessage.classList.remove("password-mismatch");
        nameMessage.innerHTML = "";
        submitButton.disabled = true;
    } else if (!regexName.test(username.value)){
        nameMessage.classList.add("password-mismatch");
        nameMessage.innerHTML = "Invalid username, needs to be 2 or more characters and only contain letters and numbers";
        submitButton.disabled = true;
    } else {
        nameMessage.classList.remove("password-mismatch");
        nameMessage.innerHTML = "";
        submitButton.disabled = !termsCheckbox.checked;
    }

    if (password.value === "") {
        passMessage.classList.remove("password-mismatch");
        passMessage.innerHTML = "";
        submitButton.disabled = true;
    } else if (!regexPass.test(password.value)) {
        passMessage.classList.add("password-mismatch");
        passMessage.innerHTML = "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long";
        submitButton.disabled = true;
    } else if (regexPass.test(password.value) && confirmPassword.value === "") {
        passMessage.classList.remove("password-mismatch");
        passMessage.innerHTML = "";
    } else if (password.value !== confirmPassword.value && confirmPassword.value !== "") {
        passMessage.classList.add("password-mismatch");
        passMessage.innerHTML = "Passwords do not match";
        submitButton.disabled = true;
    }  else {
        passMessage.classList.remove("password-mismatch");
        passMessage.innerHTML = "";
        submitButton.disabled = !termsCheckbox.checked;
    }
}

username.addEventListener("keyup", checkPasswordAlike);
password.addEventListener("keyup", checkPasswordAlike);
confirmPassword.addEventListener("keyup", checkPasswordAlike);
termsCheckbox.addEventListener("change", checkPasswordAlike);