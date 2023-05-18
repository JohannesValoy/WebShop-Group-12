const login = document.querySelector(".login");
const registerLink = document.querySelector(".register-link");
const loginLink = document.querySelector(".login-link");

registerLink.onclick = () => {
    login.classList.add("register");
};

window.focus()
window.onload = function() {
    document.getElementById('username').focus();

};

loginLink.onclick = () => {
    login.classList.remove("register");
};


const username = document.getElementById("signUpUsername");
const usernameText = document.getElementById("signUpUsernameText");
const password = document.getElementById("signUpPassword");
const confirmPassword = document.getElementById("confirmSignUpPassword");
const message = document.getElementById("confirmation");
const submitButton = document.getElementById("submitButton");
const termsCheckbox = document.getElementById("termsCheckbox");
function checkPasswordAlike() {
    const regexName = /^([A-Za-z0-9]){2,}$/;
    const regexPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;

    if (username.value === "") {
        usernameText.classList.remove("password-mismatch");
        usernameText.innerHTML = "Username";
        submitButton.disabled = true;
    } else if (!regexName.test(username.value)){
        usernameText.classList.add("password-mismatch");
        usernameText.innerHTML = "Invalid username, needs to be 2 or more characters and only contain letters and numbers";
        submitButton.disabled = true;
    } else {
        usernameText.classList.remove("password-mismatch");
        usernameText.innerHTML = "Username";
        submitButton.disabled = !termsCheckbox.checked;
    }

    if (password.value === "") {
        message.classList.remove("password-mismatch");
        message.innerHTML = "";
        submitButton.disabled = true;
    } else if (!regexPass.test(password.value)) {
        message.classList.add("password-mismatch");
        message.innerHTML = "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long";
        submitButton.disabled = true;
    } else if (regexPass.test(password.value) && confirmPassword.value === "") {
        message.classList.remove("password-mismatch");
        message.innerHTML = "";
    } else if (password.value !== confirmPassword.value && confirmPassword.value !== "") {
        message.classList.add("password-mismatch");
        message.innerHTML = "Passwords do not match";
        submitButton.disabled = true;
    }  else {
    message.classList.remove("password-mismatch");
    submitButton.disabled = !termsCheckbox.checked;
    }
}

username.addEventListener("keyup", checkPasswordAlike);
password.addEventListener("keyup", checkPasswordAlike);
confirmPassword.addEventListener("keyup", checkPasswordAlike);
termsCheckbox.addEventListener("change", checkPasswordAlike);