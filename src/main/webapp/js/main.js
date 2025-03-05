document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".nav-category").forEach(nav => {
        const category = nav.dataset.category; // Lấy loại danh mục (boy/girl)
        const tabs = nav.querySelectorAll(".tab");

        if (tabs.length > 0) {
            // Đặt tab đầu tiên là active khi tải trang
            tabs[0].classList.add("active");

            // Lấy tên ID của tab đầu tiên từ thuộc tính onclick
            const firstTabName = tabs[0].getAttribute("onclick").match(/'([^']+)'/)[1];

            // Gọi hàm để hiển thị tab đầu tiên
            showTab(firstTabName, category);
        }

        // Thêm sự kiện click cho các tab
        tabs.forEach(tab => {
            tab.addEventListener("click", function () {
                tabs.forEach(item => item.classList.remove("active"));
                this.classList.add("active");

                // Lấy ID tab khi bấm vào
                const tabName = this.getAttribute("onclick").match(/'([^']+)'/)[1];
                showTab(tabName, category);
            });
        });
    });
});

function showTab(tabName, category) {
    // Ẩn tất cả các sản phẩm trong danh mục đang thao tác
    document.querySelectorAll(`[data-category="${category}"] + .products-category`).forEach(product => {
        product.style.display = "none";
    });

    // Hiển thị sản phẩm của tab đang chọn
    const selectedTab = document.getElementById(tabName);
    if (selectedTab) {
        selectedTab.style.display = "flex";
    }

    // Cập nhật trạng thái tab active
    document.querySelectorAll(`[data-category="${category}"] .tab`).forEach(tab => {
        tab.classList.toggle("active", tab.getAttribute("onclick").includes(tabName));
    });
}