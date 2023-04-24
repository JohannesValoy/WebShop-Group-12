const checkout = document.querySelector(".checkout-button");
const progressBubble1 = document.querySelector(".progress-bubble1");
const cartSection = document.querySelector(".cart-section");
const progressBubble2= document.querySelector(".progress-bubble2");
const shippingSection = document.querySelector(".shipping-section");
const progressBubble3 = document.querySelector(".progress-bubble3");
const paymentSection = document.querySelector(".payment-section");
const completeOrder= document.getElementById("completeOrder");
const removeFromCartButton = document.querySelectorAll(".remove-from-cart-button");



progressBubble1.addEventListener("click", () => {
    progressBubble1.classList.add("active");
    cartSection.classList.add("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
});

checkout.addEventListener("click", () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
});

progressBubble2.addEventListener("click", () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
});

completeOrder.addEventListener("click", () => {
    progressBubble3.classList.add("active");
    paymentSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
});

progressBubble3.addEventListener("click", () => {
    progressBubble3.classList.add("active");
    paymentSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
});

progressBubble3.addEventListener("click", () => {
    shippingSection.classList.add("clicked");
});

function checkInfo() {
    const username = document.getElementById("name");
    const usernameText = document.getElementById("surName");
    const password = document.getElementById("address");
    const confirmPassword = document.getElementById("cardNumber");
    const message = document.getElementById("phone");
    const submitButton = document.getElementById("coupon");

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

removeFromCartButton.forEach(button => {
    button.addEventListener("click", () => {
        //TODO: Remove Event and find a way to get the product id.
        const productId = button.getAttribute('data-product-id');
        fetch(`/api/cart/product/${productId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    document.querySelector(`[product-id="${productId}"]`).remove();
                }
            });
    });
});