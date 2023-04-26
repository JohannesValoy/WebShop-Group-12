const checkout = document.querySelector(".checkout-button");
const progressBubble1 = document.querySelector(".progress-bubble1");
const cartSection = document.querySelector(".cart-section");
const progressBubble2= document.querySelector(".progress-bubble2");
const progressBubble3= document.querySelector(".progress-bubble3");
const shippingSection = document.querySelector(".shipping-section");
const paymentSection = document.querySelector(".payment-section");
const completeOrder= document.getElementById("completeOrder");
const removeFromCartButton = document.querySelectorAll(".remove-from-cart-button");



progressBubble1.addEventListener("click", () => {
    event.preventDefault();
    progressBubble1.classList.add("active");
    cartSection.classList.add("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
    paymentSection.classList.remove("active");
    progressBubble3.classList.remove("active");
});

function showShippingSection(event) {
    event.preventDefault();
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    paymentSection.classList.remove("active");
    progressBubble3.classList.remove("active");
}

checkout.addEventListener("click", showShippingSection);
progressBubble2.addEventListener("click", showShippingSection);


completeOrder.addEventListener("click", () => {
    event.preventDefault();
    paymentSection.classList.add("active");
    progressBubble3.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
});

const name = document.getElementById("name");
const surname = document.getElementById("surname");
const address = document.getElementById("address");
const cardNumber = document.getElementById("cardNumber");
const phone = document.getElementById("phone");
const coupon = document.getElementById("coupon");
const submitButton = document.getElementById("completeOrder");
function checkInfo(qualifiedName, value) {

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

removeFromCartButton.forEach(button => {
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