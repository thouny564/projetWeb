

    console.log('roj');
    document.addEventListener("DOMContentLoaded", () => {

    const images = document.querySelectorAll(".carousel-images img");
    const nextBtn = document.querySelector(".next-btn");
    const prevBtn = document.querySelector(".prev-btn");

    let index = 0;

    function showImage(i) {
    images.forEach(img => img.classList.remove("active"));
    images[i].classList.add("active");
}

    nextBtn.addEventListener("click", () => {
    index = (index + 1) % images.length;
    showImage(index);
});

    prevBtn.addEventListener("click", () => {
    index = (index - 1 + images.length) % images.length;
    showImage(index);
});

});
