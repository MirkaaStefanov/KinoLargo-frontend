document.addEventListener('DOMContentLoaded', () => {
    // --- Carousel Functionality (for the hero section on movie/all.html) ---
    const carouselItems = document.querySelectorAll('.carousel-item');
    const dots = document.querySelectorAll('.dot');
    let currentIndex = 0;

    function showCarouselItem(index) {
        if (carouselItems.length === 0) return; // Exit if no carousel items

        carouselItems.forEach((item, i) => {
            if (i === index) {
                item.classList.add('active');
            } else {
                item.classList.remove('active');
            }
        });
        dots.forEach((dot, i) => {
            if (i === index) {
                dot.classList.add('active');
            } else {
                dot.classList.remove('active');
            }
        });
    }

    function nextCarouselItem() {
        if (carouselItems.length > 0) {
            currentIndex = (currentIndex + 1) % carouselItems.length;
            showCarouselItem(currentIndex);
        }
    }

    // Add click listeners to dots (if present)
    if (dots.length > 0) {
        dots.forEach((dot, index) => {
            dot.addEventListener('click', () => {
                currentIndex = index;
                showCarouselItem(currentIndex);
            });
        });
    }

    // Auto-advance carousel (optional, only if there are items)
    if (carouselItems.length > 1) { // Only auto-advance if there's more than one item
        setInterval(nextCarouselItem, 5000); // Change item every 5 seconds
    }
});