const about = document.querySelectorAll(".grey-box");

about.forEach(box => {
    const aboutContent = box.querySelector(".purchase-card");
    box.addEventListener("click", () => {
        console.log(box);
        // Remove 'active' class from all .purchase-card elements
        document.querySelectorAll('.purchase-card.active').forEach(card => {
            card.classList.remove('active');
        });
        // Add 'active' class to the clicked .purchase-card
        aboutContent.classList.add("active");
    });
});
