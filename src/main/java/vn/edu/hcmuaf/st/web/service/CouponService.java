package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.CouponDAO;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.time.LocalDateTime;
import java.util.Optional;

public class CouponService {
    private final CouponDAO CouponDAO = new CouponDAO();

    public Optional<Coupon> validateCoupon(String code, double orderTotal) {
        Optional<Coupon> opt = CouponDAO.getByCode(code);
        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())) return Optional.empty();
            if (coupon.getUsageLimit() <= coupon.getUsedCount()) return Optional.empty();
            if (orderTotal < coupon.getMinOrderValue()) return Optional.empty();
            return Optional.of(coupon);
        }
        return Optional.empty();
    }

    public void increaseUsedCount(int idCoupon) {
        CouponDAO.incrementUsedCount(idCoupon);
    }
}
