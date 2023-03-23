const login = document.querySelector(".login");
const registerLink = document.querySelector(".register-link");
const loginLink = document.querySelector(".login-link");


registerLink.onclick = () => {
    login.classList.add("register");
};

loginLink.onclick = () => {
    login.classList.remove("register");
};

function checkPasswordAlike(){
    const password = document.getElementById("signUpPassword").value;
    const password2 = document.getElementById("confirmSignUpPassword").value;
    if(password != null && password != "" && password2 != null && password2 != ""){
        if(password != password2){
            document.getElementById("confirmSignUpPassword").style.backgroundColor = "red";
        }else{
            document.getElementById("confirmSignUpPassword").style.backgroundColor = "green";
        }
    }
}