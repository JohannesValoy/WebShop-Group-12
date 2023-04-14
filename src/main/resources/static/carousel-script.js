const carousel = document.querySelector('.carousel');
const container = carousel.querySelector('.carousel-container');
const items = carousel.querySelectorAll('.carousel-item');
const prevBtn = carousel.querySelector('.carousel-prev');
const nextBtn = carousel.querySelector('.carousel-next');

let currentIndex = 0;
let interval;

function startCarousel() {
    interval = setInterval(() => {
        currentIndex++;
        if (currentIndex >= items.length) {
            currentIndex = 0;
        }
        updateCarousel();
    }, 3000);
}

function updateCarousel() {
    container.style.transform = `translateX(-${currentIndex * 100}%)`;
}

function stopCarousel() {
    clearInterval(interval);
}

function prevSlide() {
    currentIndex--;
    if (currentIndex < 0) {
        currentIndex = items.length - 1;
    }
    updateCarousel();
}

function nextSlide() {
    currentIndex++;
    if (currentIndex >= items.length) {
        currentIndex = 0;
    }
    updateCarousel();
}

prevBtn.addEventListener('click', prevSlide);
nextBtn.addEventListener('click', nextSlide);

startCarousel();
carousel.addEventListener('mouseover', stopCarousel);
carousel.addEventListener('mouseout', startCarousel);
