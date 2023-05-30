
const cartBar = document.querySelector(".cart-bar");
const cartSection = document.querySelector(".cart-section");
const checkoutBtn = document.querySelector(".checkout-button");
const checkoutBar= document.querySelector(".checkout-bar");
const checkoutSection = document.querySelector(".checkout-section");
const backToCartBtn = document.getElementById("back-to-cart");
const removeFromCartBtn = document.querySelectorAll(".remove-from-cart");

// If cart is empty, hide the checkout button and change cart title
if (document.querySelector(".cart").children.length >= 1) {
    checkoutBtn.removeAttribute("hidden");
    document.getElementById("cart-title").innerHTML = "Cart products";
} else {
    document.getElementById("cart-title").innerHTML = "Your cart is empty";
    checkoutBtn.setAttribute("hidden", "hidden");
}

// Change number of products in cart
const decreaseBtn = document.querySelectorAll(".decrease");
const increaseBtn = document.querySelectorAll(".increase");

// Decreases the number of products. Disables if amount is 1
decreaseBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = Number(button.getAttribute('data-product'));
        let quantity = Number(document.getElementById("amount-".concat(productId)).innerText) - 1;
        changeCartAmount(productId, quantity);
        if (quantity <= 1) {
            button.setAttribute("disabled", "disabled");   
        }
    });
});

// Increases the number of products. Enables decrease button if the amount is more than 1
increaseBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = Number(button.getAttribute('data-product'));
        let quantity = Number(document.getElementById("amount-".concat(productId)).innerText)+1;
        changeCartAmount(productId, quantity);
        if (quantity > 1) {
            document.querySelector(`[data-product="${productId}"].decrease`).removeAttribute("disabled");
        }
    });
});

// Changes number of products in cart. Fetches data from API. Updates total price
function changeCartAmount(id, amount) {
    fetch(`/api/carts/me/product/${Number(id)}/quantity/${Number(amount)}`, { method: 'PATCH' })
            .then(response => {
                if (response.ok) {
                    response.json().then( data => {
                        document.getElementById("amount-".concat(id)).innerHTML =  data.amount;
                        document.getElementById("totalPrice-".concat(id)).innerHTML = (data.amount * data.product.price).toLocaleString('no-NO') + " kr";
                    }).catch(error => console.error(error));
                } else {
                    window.location.reload();
                }
            }).catch(error => window.location.reload());
}

// Removes product from cart one by one.
function removeProductFromCart(productId) {
    document.querySelector(`[product-id="${productId}"]`).remove();
    let cart = document.querySelector(".cart");
    let products = cart.getElementsByClassName("product-table-product");
    if(products.length === 0) {
        document.getElementById("cart-title").innerHTML = "Your cart is empty";
        checkoutBtn.setAttribute("hidden", "hidden");
    }
}

// Removes a type of product from cart.
removeFromCartBtn.forEach(button => {
    button.addEventListener("click", () => {
        const productId = button.getAttribute('data-product-id');
        fetch(`/api/carts/me/product/${productId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    removeProductFromCart(productId);
                } else {
                    window.location.reload();
                }
            }).catch(error => window.location.reload());
    });
});

// Changes an active section in cart to check out
checkoutBtn.addEventListener("click", () => {
    checkoutSection.classList.add("active");
    checkoutBar.classList.add("active");
    cartBar.classList.remove("active");
    cartSection.classList.remove("active");
});

// Changes active section in cart to cart
backToCartBtn.addEventListener("click", () => {
    cartBar.classList.add("active");
    cartSection.classList.add("active");
    checkoutSection.classList.remove("active");
    checkoutBar.classList.remove("active");
});

// Path: src\main\resources\static\js\checkout.js
// Checks if input is valid.
// If not, shows an error message and adds red border to the input field
const name = document.getElementById("name");
const surname = document.getElementById("surname");
const address = document.getElementById("address");
const cardNumber = document.getElementById("cardNumber");
const phone = document.getElementById("phoneNumber");
const cvc = document.getElementById("cvc");
const submitButton = document.getElementById("completeOrder");

function checkInfo() {
    // Regex for input validation
    const regexName = /^([A-Za-zØÆÅøæå ]){2,}$/;
    const regexAddress = /^([A-Za-z0-9ØÆÅøæå ,]){2,}$/;
    const regexCard = /^([0-9]){16}$/;
    const regexPhone = /^([0-9]){8}$/;
    const regexCVC = /^([0-9 ,]){3}$/;

    // Checks if input is valid. If not, shows an error message and adds red border to the input field
    if (!regexName.test(name.value) && name.value !== "") {
        document.getElementById("p-name").removeAttribute("hidden");
        name.classList.add("mismatch");
    } else {
        document.getElementById("p-name").setAttribute("hidden", "hidden");
        name.classList.remove("mismatch");
    }
    if (!regexName.test(surname.value) && surname.value !== "") {
        document.getElementById("p-surname").removeAttribute("hidden");
        surname.classList.add("mismatch");
    } else {
        document.getElementById("p-surname").setAttribute("hidden", "hidden");
        surname.classList.remove("mismatch");
    }
    if (!regexAddress.test(address.value) && address.value !== "") {
        document.getElementById("p-address").removeAttribute("hidden");
        address.classList.add("mismatch");
    } else {
        document.getElementById("p-address").setAttribute("hidden", "hidden");
        address.classList.remove("mismatch");
    }
    if (!regexCard.test(cardNumber.value) && cardNumber.value !== "") {
        document.getElementById("p-cardNumber").removeAttribute("hidden");
        cardNumber.classList.add("mismatch");
    } else {
        document.getElementById("p-cardNumber").setAttribute("hidden", "hidden");
        cardNumber.classList.remove("mismatch");
    }
    if (!regexPhone.test(phone.value) && phone.value !== "") {
        document.getElementById("p-phone").removeAttribute("hidden");
        phone.classList.add("mismatch");
    } else {
        document.getElementById("p-phone").setAttribute("hidden", "hidden");
        phone.classList.remove("mismatch");
    }
    if (!regexCVC.test(cvc.value) && cvc.value !== "") {
        document.getElementById("p-cvc").removeAttribute("hidden");
        cvc.classList.add("mismatch");
    } else {
        document.getElementById("p-cvc").setAttribute("hidden", "hidden");
        cvc.classList.remove("mismatch");
    } submitButton.disabled = !(regexName.test(name.value) &&

        // Checks if all input fields are valid. If not, the submit button is disabled
        regexName.test(surname.value) &&
        regexAddress.test(address.value) &&
        regexCard.test(cardNumber.value) &&
        regexPhone.test(phone.value) &&
        regexCVC.test(cvc.value));
}

//Checks input on keyup,
// so that error message and red border are added when input is invalid and removed when input is valid
name.addEventListener("keyup", checkInfo);
surname.addEventListener("keyup", checkInfo);
address.addEventListener("keyup", checkInfo);
cardNumber.addEventListener("keyup", checkInfo);
phone.addEventListener("keyup", checkInfo);
cvc.addEventListener("keyup", checkInfo);