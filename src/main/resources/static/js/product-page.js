const about = document.getElementById("about-button");
const aboutContent = document.getElementById("about");
const specs= document.getElementById("specs-button");
const specsContent = document.getElementById("specs");
const rating = document.getElementById("rating-button");
const ratingContent = document.getElementById("rating");


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

