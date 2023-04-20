const checkout = document.querySelector(".checkout-button");
const progressBubble1 = document.querySelector(".progress-bubble1");
const cartSection = document.querySelector(".cart-section");
const progressBubble2= document.querySelector(".progress-bubble2");
const shippingSection = document.querySelector(".shipping-section");
const progressBubble3 = document.querySelector(".progress-bubble3");
const paymentSection = document.querySelector(".payment-section");
const completeOrder= document.querySelector(".complete-order");
const removeFromCartButton = document.querySelectorAll(".remove-from-cart-button");

const nameField = document.querySelector(".name-field");
const surNameField = document.querySelector(".surname-field");
const addressField = document.querySelector(".address-field");
const cardNumberField= document.querySelector(".card-number-field");
const phoneNumberField = document.querySelector(".phone-number-field");
const couponCodeField = document.querySelector(".card-coupon-field");
const completeOrderbutton = document.querySelector(".complete-order-button");


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
    shippingSection.classList.add("clicked");
});

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