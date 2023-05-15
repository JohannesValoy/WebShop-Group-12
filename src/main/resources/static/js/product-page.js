const about = document.querySelector(".grey-box");
const aboutContent = document.querySelector(".purchase-card");
const specs= document.querySelector(".specs-button");
const specsContent = document.querySelector(".specs");
const rating = document.querySelector(".rating-button");
const ratingContent = document.querySelector(".rating");


about.onclick = () => {
    aboutContent.classList.add("active");
    specsContent.classList.remove("active");
    ratingContent.classList.remove("active");
};

specs.onclick = () => {
    specsContent.classList.add("active");
    aboutContent.classList.remove("active");
    ratingContent.classList.remove("active");
};

rating.onclick = () => {
    ratingContent.classList.add("active");
    specsContent.classList.remove("active");
    aboutContent.classList.remove("active");
};

