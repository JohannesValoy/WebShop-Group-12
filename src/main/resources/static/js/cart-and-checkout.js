
const cartBar = document.querySelector(".cart-bar");
const cartSection = document.querySelector(".cart-section");
const checkoutBtn = document.querySelector(".checkout-button");
const checkoutBar= document.querySelector(".checkout-bar");
const checkoutSection = document.querySelector(".checkout-section");
const completeOrderBtn= document.getElementById("completeOrder");
const completeBar= document.querySelector(".complete-bar");
const completeSection = document.querySelector(".complete-section");


if (document.querySelector(".cart").children.length > 1) {
    checkoutBtn.removeAttribute("hidden");
    document.getElementById("cart-title").innerHTML = "Cart products";
} else {
    document.getElementById("cart-title").innerHTML = "Your cart is empty";
    checkoutBtn.setAttribute("hidden", "hidden");
}

const decreaseBtn = document.querySelectorAll(".decrease");
const increaseBtn = document.querySelectorAll(".increase");


decreaseBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = button.getAttribute('data-product');
        const quantity = button.getAttribute('data-quantity');
        const price = button.getAttribute('data-price');
        fetch(`/api/cart/product/${productId}/quantity/${Number(quantity)-1}`, { method: 'PATCH' })
            .then(response => {
                if (response.ok) {
                    document.querySelector(`[quantity="${Number(quantity)-1}`);
                }
            })
        .then(data => {
            document.getElementById("amount").innerHTML = quantity;

        });
    });
});

increaseBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = button.getAttribute('data-product-id');
        let quantity = button.getAttribute('data-quantity');
        const price = button.getAttribute('data-price');
        fetch(`/api/cart/product/${Number(productId)}/quantity/${Number(quantity)+1}`, { method: 'PATCH' })
            .then(response => {
                if (response.ok) {
                    quantity="${Number(quantity)+1}".toString();
                }
            })
            .then(data => {
                document.getElementById("amount").innerHTML = quantity;
                document.getElementById("total").innerHTML = Number(price * quantity).toString();
            });
    });
});


const removeFromCartBtn = document.querySelectorAll(".remove-from-cart");
removeFromCartBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = button.getAttribute('data-product-id');
        fetch(`/api/cart/product/${productId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    document.querySelector(`[product-id="${productId}"]`).remove();
                }
            });
    });
});

checkoutBtn.addEventListener("click", () => {
    checkoutSection.classList.add("active");
    checkoutBar.classList.add("active");
    cartBar.classList.remove("active");
    cartSection.classList.remove("active");
    completeBar.classList.remove("active");
    completeSection.classList.remove("active");
});

completeOrderBtn.addEventListener("click", () => {
    completeSection.classList.add("active");
    completeBar.classList.add("active");
    cartBar.classList.remove("active");
    cartSection.classList.remove("active");
    checkoutBar.classList.remove("active");
    checkoutSection.classList.remove("active");
});

const name = document.getElementById("name");
const surname = document.getElementById("surname");
const address = document.getElementById("address");
const cardNumber = document.getElementById("cardNumber");
const phone = document.getElementById("phone");
const coupon = document.getElementById("coupon");
const submitButton = document.getElementById("completeOrder");
function checkInfo() {
    const regexName = /^([A-Za-zØÆÅøæå]){2,}$/;
    const regexFull = /^([A-Za-z0-9ØÆÅøæå ,]){2,}$/;
    const regexCard = /^([0-9]){16}$/;
    const regexPhone = /^([0-9]){8}$/;

    if (!regexName.test(name.value) && name.value !== "") {
        document.getElementById("p-name").removeAttribute("hidden");
        name.classList.add("mismatch");
    } else {
        document.getElementById("p-name").setAttribute("hidden", "hidden");
        name.classList.remove("mismatch");
    } if (!regexName.test(surname.value) && surname.value !== "") {
        document.getElementById("p-surname").removeAttribute("hidden");
        surname.classList.add("mismatch");
    } else {
        document.getElementById("p-surname").setAttribute("hidden", "hidden");
        surname.classList.remove("mismatch");
    } if (!regexFull.test(address.value) && address.value !== "") {
        document.getElementById("p-address").removeAttribute("hidden");
        address.classList.add("mismatch");
    } else {
        document.getElementById("p-address").setAttribute("hidden", "hidden");
        address.classList.remove("mismatch");
    } if (!regexCard.test(cardNumber.value) && cardNumber.value !== "") {
        document.getElementById("p-cardNumber").removeAttribute("hidden");
        cardNumber.classList.add("mismatch");
    } else {
        document.getElementById("p-cardNumber").setAttribute("hidden", "hidden");
        cardNumber.classList.remove("mismatch");
    } if (!regexPhone.test(phone.value) && phone.value !== "") {
        document.getElementById("p-phone").removeAttribute("hidden");
        phone.classList.add("mismatch");
    } else {
        document.getElementById("p-phone").setAttribute("hidden", "hidden");
        phone.classList.remove("mismatch");
    } if (!regexFull.test(coupon.value) && coupon.value !== "") {
        document.getElementById("p-coupon").removeAttribute("hidden");
        coupon.classList.add("mismatch");
    } else {
        document.getElementById("p-coupon").setAttribute("hidden", "hidden");
        coupon.classList.remove("mismatch");
    } submitButton.disabled = !(regexName.test(name.value) &&
        regexName.test(surname.value) &&
        regexFull.test(address.value) &&
        regexCard.test(cardNumber.value) &&
        regexPhone.test(phone.value) &&
        regexFull.test(coupon.value));
}

name.addEventListener("keyup", checkInfo);
surname.addEventListener("keyup", checkInfo);
address.addEventListener("keyup", checkInfo);
cardNumber.addEventListener("keyup", checkInfo);
phone.addEventListener("keyup", checkInfo);
coupon.addEventListener("keyup", checkInfo);