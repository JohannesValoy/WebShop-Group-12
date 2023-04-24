const checkout = document.querySelector(".checkout-button");
const progressBubble1 = document.querySelector(".progress-bubble1");
const cartSection = document.querySelector(".cart-section");
const progressBubble2= document.querySelector(".progress-bubble2");
const shippingSection = document.querySelector(".shipping-section");
const paymentSection = document.querySelector(".payment-section");
const completeOrder= document.getElementById("completeOrder");
const removeFromCartButton = document.querySelectorAll(".remove-from-cart-button");



progressBubble1.addEventListener("click", () => {
    progressBubble1.classList.add("active");
    cartSection.classList.add("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
    paymentSection.classList.remove("active");
});

checkout.addEventListener("click", () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    paymentSection.classList.remove("active");
});

progressBubble2.addEventListener("click", () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    paymentSection.classList.remove("active");
});

completeOrder.addEventListener("click", () => {
    paymentSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
});

const name = document.getElementById("name");
const surName = document.getElementById("surName");
const address = document.getElementById("address");
const cardNumber = document.getElementById("cardNumber");
const phone = document.getElementById("phone");
const coupon = document.getElementById("coupon");
const submitButton = document.getElementById("completeOrder");
const invalid = document.getElementById("invalid");
function checkInfo() {
    const regexName = /^([A-Åa-å]){2,}$/;
    const regexFull = /^([A-Åa-å0-9 ,]){2,}$/;
    const regexCard = /^([0-9]){16}$/;
    const regexPhone = /^([0-9]){8}$/;

    if (name.value === "" && surName.value === "" && address.value === "" && cardNumber.value === "" && phone.value === "" && coupon.value === "") {
        invalid.innerHTML = "";
        submitButton.disabled = true;
    } else if(!regexName.test(name.value)) {
        invalid.innerHTML = "Invalid name, needs to be 2 or more characters and only contain letters";
        submitButton.disabled = true;
    } else if(!regexName.test(surName.value)) {
        invalid.innerHTML = "Invalid surname, needs to be 2 or more characters and only contain letters";
        submitButton.disabled = true;
    } else if(!regexFull.test(address.value)) {
        invalid.innerHTML = "Invalid address, needs to be 2 or more characters and only contain letters and numbers";
        submitButton.disabled = true;
    } else if(!regexCard.test(cardNumber.value)) {
        invalid.innerHTML = "Invalid card number, needs to be 16 characters and can only contain numbers";
        submitButton.disabled = true;
    } else if(!regexPhone.test(phone.value)) {
        invalid.innerHTML = "Invalid phone number, needs to be 8 characters and can only contain numbers";
        submitButton.disabled = true;
    } else if(!regexFull.test(coupon.value)) {
        invalid.innerHTML = "Invalid coupon, needs to be 2 or more characters and can only contain letters and numbers";
        submitButton.disabled = true;
    } else {
        invalid.innerHTML = "";
        submitButton.disabled = false;
    }
}

name.addEventListener("keyup", checkInfo);
surName.addEventListener("keyup", checkInfo);
address.addEventListener("keyup", checkInfo);
cardNumber.addEventListener("keyup", checkInfo);
phone.addEventListener("keyup", checkInfo);
coupon.addEventListener("keyup", checkInfo);

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