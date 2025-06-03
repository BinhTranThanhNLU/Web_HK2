package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.CouponDAO;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.time.LocalDateTime;
import java.util.Optional;

public class CouponService {
    private final CouponDAO CouponDAO;

    public CouponService() {
        this.CouponDAO = new CouponDAO();
    }




    public static class CouponResult {
        private boolean success;
        private String message;
        private double discountAmount;

        public CouponResult(boolean success, String message, double discountAmount) {
            this.success = success;
            this.message = message;
            this.discountAmount = discountAmount;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }
    }
    /**
     * @param code Mã giảm giá người dùng nhập
     * @param productTotal Tổng tiền sản phẩm
     * @param shippingFee Phí vận chuyển
     * @param applyToShipping true nếu áp dụng cho vận chuyển, false nếu áp dụng cho sản phẩm
     * @return thông tin kết quả áp dụng mã
     */
    public CouponResult applyCoupon(String code, double productTotal, double shippingFee, boolean applyToShipping) {
        Optional<Coupon> optionalCoupon = CouponDAO.getByCode(code);


        if (optionalCoupon.isEmpty()) {
            return new CouponResult(false, "Mã giảm giá không tồn tại.", 0);
        }

        Coupon coupon = optionalCoupon.get();

        // Kiểm tra loại mã (SHIPPING hoặc PRODUCT)
        if (applyToShipping && coupon.getDiscountType() != Coupon.DiscountType.SHIPPING ||
                !applyToShipping && coupon.getDiscountType() != Coupon.DiscountType.PRODUCT) {
            return new CouponResult(false, "Mã không áp dụng cho phần này.", 0);
        }

        // Kiểm tra giá trị đơn hàng
        double relevantTotal = applyToShipping ? shippingFee : productTotal;
        if (relevantTotal < coupon.getMinOrderValue()) {
            return new CouponResult(false, "Không đủ giá trị tối thiểu để áp dụng mã.", 0);
        }

        // Kiểm tra thời gian và số lượt dùng
        if (!CouponDAO.isCouponValid(coupon)) {
            return new CouponResult(false, "Mã đã hết hạn hoặc đã sử dụng tối đa số lượt.", 0);
        }

        // Tính giá trị giảm
        double discount;
        if (coupon.isPercentage()) {
            discount = relevantTotal * coupon.getDiscountAmount() / 100.0;
        } else {
            discount = coupon.getDiscountAmount();
        }

        // Không vượt quá số tiền phải trả
        discount = Math.min(discount, relevantTotal);

        // Cập nhật lượt sử dụng
        CouponDAO.incrementUsedCount(coupon.getIdCoupon());

        return new CouponResult(true, "Áp dụng mã thành công.", discount);
    }


    public void increaseUsedCount(int idCoupon) {
        CouponDAO.incrementUsedCount(idCoupon);
    }

    public Optional<Coupon> getCouponByCode(String couponCode) {
        return null;
    }

    public void incrementUsedCount(int idCoupon) {
    }
}
