document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("search-form");
    const productList = document.querySelector(".product-list");

    form.addEventListener("submit", (event) => {
        event.preventDefault();
        const fd = new FormData(form);
        fetch(`/api/products?name=${encodeURIComponent(fd.get("search"))}`, { method: "GET" })
            .then((response) => {
                if (response.ok) {
                    response.json().then((data) => {
                        displayResults(data);
                    });
                } else {
                    console.error("Search request failed");
                }
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    });

    function displayResults(data) {
        //clears the product list
        productList.innerHTML = "";

        //creates a product card for each product
        data.forEach((product) => {

            //product card
            const productCard = document.createElement("div");
            productCard.classList.add("cart-product");
            productCard.className = ("cart-grid");

            //product image
            const productImage = document.createElement("div");
            productImage.classList.add("product-image");
            const img = document.createElement("img");
            img.src = product.image.primaryImage;
            productImage.appendChild(img);
            productImage.className = ("cart-box");

            //product details
            const productDetails = document.createElement("div");
            productDetails.classList.add("div-row");

            //product name
            const productName = document.createElement("h2");
            productName.textContent = product.name;
            productDetails.appendChild(productName);

            //product description
            const productDescription = document.createElement("p");
            productDescription.textContent = product.description;
            productDetails.appendChild(productDescription);

            //product price
            const productPrice = document.createElement("p");
            productPrice.textContent = "Price: $" + product.price;
            productDetails.appendChild(productPrice);

            //product stock
            const productStock = document.createElement("p");
            productStock.textContent = "Stock: " + product.stock;
            productDetails.appendChild(productStock);

            //product category
            const addToCartForm = document.createElement("form");
            addToCartForm.action = "/cart/" + product.id;
            addToCartForm.method = "post";
            const addToCartButton = document.createElement("button");
            addToCartButton.type = "submit";
            addToCartButton.textContent = "Go to Product";
            addToCartButton.className = "focus-button style-1";
            addToCartForm.appendChild(addToCartButton);

            //Inserts addToCartForm into productDetails
            productDetails.appendChild(addToCartForm);

            //Inserts productImage and productDetails into productCard
            productCard.appendChild(productImage);
            productCard.appendChild(productDetails);

            //Inserts productCard into productList
            productList.appendChild(productCard);
        });
    }
});
