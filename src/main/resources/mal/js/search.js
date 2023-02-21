const searchInput = document.querySelector('#searchInput');
const productList = document.querySelectorAll('#productList li');

searchInput.addEventListener('keyup', function(event) {
    const searchTerm = event.target.value.toLowerCase();

    productList.forEach(function(product) {
        const productText = product.textContent.toLowerCase();

        if (productText.includes(searchTerm)) {
            product.style.display = 'block';
        } else {
            product.style.display = 'none';
        }
    });
});