const checkout = document.querySelector(".checkout-button");
const progressbubble1 = document.querySelector(".progress-bubble1");
const cartsection = document.querySelector(".cart-section");
const progressbubble2= document.querySelector(".progress-bubble2");
const shippingsection = document.querySelector(".shipping-section");
const progressbubble3 = document.querySelector(".progress-bubble3");
const paymentsection = document.querySelector(".payment-section");
const completeorder= document.querySelector(".complete-order");

progressbubble1.onclick = () => {
    progressbubble1.classList.add("active");
    cartsection.classList.add("active");
    progressbubble2.classList.remove("active");
    shippingsection.classList.remove("active");
    progressbubble3.classList.remove("active");
    paymentsection.classList.remove("active");
};

checkout.onclick = () => {
    progressbubble2.classList.add("active");
    shippingsection.classList.add("active");
    progressbubble1.classList.remove("active");
    cartsection.classList.remove("active");
    progressbubble3.classList.remove("active");
    paymentsection.classList.remove("active");
};

progressbubble2.onclick = () => {
    progressbubble2.classList.add("active");
    shippingsection.classList.add("active");
    progressbubble1.classList.remove("active");
    cartsection.classList.remove("active");
    progressbubble3.classList.remove("active");
    paymentsection.classList.remove("active");
};

completeorder.onclick = () => {
    progressbubble3.classList.add("active");
    paymentsection.classList.add("active");
    progressbubble1.classList.remove("active");
    cartsection.classList.remove("active");
    progressbubble2.classList.remove("active");
    shippingsection.classList.remove("active");
};

progressbubble3.onclick = () => {
    shippingsection.classList.add("clicked");
};
