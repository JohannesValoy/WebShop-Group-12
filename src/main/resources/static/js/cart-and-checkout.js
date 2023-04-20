const checkout = document.querySelector(".checkout-button");
const progressBubble1 = document.querySelector(".progress-bubble1");
const cartSection = document.querySelector(".cart-section");
const progressBubble2= document.querySelector(".progress-bubble2");
const shippingSection = document.querySelector(".shipping-section");
const progressBubble3 = document.querySelector(".progress-bubble3");
const paymentSection = document.querySelector(".payment-section");
const completeOrder= document.querySelector(".complete-order");
const removeFromCartButton = document.querySelectorAll(".remove-from-cart-button");

progressBubble1.onclick = () => {
    progressBubble1.classList.add("active");
    cartSection.classList.add("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
};

checkout.onclick = () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
};

progressBubble2.onclick = () => {
    progressBubble2.classList.add("active");
    shippingSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble3.classList.remove("active");
    paymentSection.classList.remove("active");
};

completeOrder.onclick = () => {
    progressBubble3.classList.add("active");
    paymentSection.classList.add("active");
    progressBubble1.classList.remove("active");
    cartSection.classList.remove("active");
    progressBubble2.classList.remove("active");
    shippingSection.classList.remove("active");
};

progressBubble3.onclick = () => {
    shippingSection.classList.add("clicked");
};

removeFromCartButton.forEach(onclick = () =>  {
    //TODO: Remove Event and find a way to get the product id.
    const productId = event.target.getAttribute('data-product-id');
    fetch(`/api/cart/product/${productId}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                document.querySelector(`[product-id="${productId}"]`).remove();
            }
        });
})