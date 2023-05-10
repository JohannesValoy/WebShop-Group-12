const form = document.getElementById("search-form");

form.addEventListener("submit", (event) => {
    event.preventDefault();
    const fd = new FormData(form);
    fetch(`/api/products?name=${encodeURIComponent(fd.get("search"))}`, { method: 'GET' })
        .then(response => {
            if (response.ok) {
                response.json().then(data => {
                    const searchParams = new URLSearchParams();
                    searchParams.set("q", fd.get("search"));
                    window.location.href = searchUrl;
                });
            } else {
                console.error('Search request failed');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

