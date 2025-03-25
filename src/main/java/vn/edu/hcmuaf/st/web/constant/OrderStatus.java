package vn.edu.hcmuaf.st.web.constant;

public enum OrderStatus {
    CHO_XAC_NHAN("Chờ xác nhận"),
    DANG_GIAO("Đang giao"),
    HOAN_THANH("Hoàn thành"),
    DA_HUY("Đã hủy");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String text) {
        for (OrderStatus s : OrderStatus.values()) {
            if (s.status.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy trạng thái đơn hàng: " + text);
    }
}
