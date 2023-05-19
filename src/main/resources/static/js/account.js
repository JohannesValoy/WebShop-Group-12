const box = document.querySelectorAll(".grey-box");

box.forEach(box => {
    const card = box.querySelector(".purchase-card");
    box.addEventListener("click", () => {
        card.classList.toggle("active");
        box.classList.toggle("active");
    });
});
