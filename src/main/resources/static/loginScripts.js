const login = document.querySelector(".login");
const registerLink = document.querySelector(".register-link");
const loginLink = document.querySelector(".login-link");

registerLink.onclick = () => {
    login.classList.add("register");
};

loginLink.onclick = () => {
    login.classList.remove("register");
};

function checkPasswordAlike() {
    const username = document.getElementById("signUpUsername");
    const usernameText = document.getElementById("signUpUsernameText");
    const password = document.getElementById("signUpPassword");
    const confirmPassword = document.getElementById("confirmSignUpPassword");
    const message = document.getElementById("confirmation");
    const submitButton = document.getElementById("submitButton");
    const termsCheckbox = document.getElementById("termsCheckbox");

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

    if (password.value === "" || confirmPassword.value === "") {
        message.classList.remove("password-mismatch");
        message.innerHTML = "Confirm password";
        submitButton.disabled = true;
    } else if (password.value !== confirmPassword.value) {
        message.classList.add("password-mismatch");
        message.innerHTML = "Passwords do not match";
        submitButton.disabled = true;
    } else if (!regexPass.test(password.value)){
        message.classList.add("password-mismatch");
        message.innerHTML = "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long";
        submitButton.disabled = true;
    } else {
    message.classList.remove("password-mismatch");
    message.innerHTML = "New password confirmed";
    submitButton.disabled = !termsCheckbox.checked;
    }
}

const usernameInput = document.getElementById("signUpUsername");
const passwordInput = document.getElementById("signUpPassword");
const confirmPasswordInput = document.getElementById("confirmSignUpPassword");
const termsCheckbox = document.getElementById("termsCheckbox");

usernameInput.addEventListener("keyup", checkPasswordAlike);
passwordInput.addEventListener("keyup", checkPasswordAlike);
confirmPasswordInput.addEventListener("keyup", checkPasswordAlike);
termsCheckbox.addEventListener("change", checkPasswordAlike);