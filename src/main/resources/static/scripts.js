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
    const password = document.getElementById("signUpPassword");
    const confirmPassword = document.getElementById("confirmSignUpPassword");
    const message = document.getElementById("confirmation");
    const submitButton = document.getElementById("submitButton");
    const termsCheckbox = document.getElementById("termsCheckbox");

    if (password.value !== confirmPassword.value) {
        message.classList.add("password-mismatch");
        message.innerHTML = "Passwords do not match";
        submitButton.disabled = true;
    } else if (password.value.length < 6){
        message.classList.add("password-mismatch");
        message.innerHTML = "Password must be at least 6 characters long";
        submitButton.disabled = true;
    } else {
    message.classList.remove("password-mismatch");
    message.innerHTML = "Confirm password";
    submitButton.disabled = !termsCheckbox.checked;
    }
}

const passwordInput = document.getElementById("signUpPassword");
const confirmPasswordInput = document.getElementById("confirmSignUpPassword");
const termsCheckbox = document.getElementById("termsCheckbox");

passwordInput.addEventListener("keyup", checkPasswordAlike);
confirmPasswordInput.addEventListener("keyup", checkPasswordAlike);
termsCheckbox.addEventListener("change", checkPasswordAlike);