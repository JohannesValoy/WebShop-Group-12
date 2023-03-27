let slideIndex = 1;

function showSlide(n) {
    let items = document.querySelectorAll(".carousel-item");
    if (n > items.length) {
        slideIndex = 1;
    }
    if (n < 1) {
        slideIndex = items.length;
    }
    items.forEach(item => item.style.transform = `translateX(-${(slideIndex-1) * 100}%)`);
}

function prevSlide() {
    showSlide(slideIndex -= 1);
}

function nextSlide() {
    showSlide(slideIndex += 1);
}

showSlide(slideIndex);