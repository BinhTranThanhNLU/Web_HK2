package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.CouponDAO;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;

public class CouponService {
    private final CouponDAO CouponDAO = new CouponDAO();

    public Optional<Coupon> validateCoupon(String code, double orderTotal) {
        Optional<Coupon> opt = CouponDAO.getByCode(code);
        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            LocalDate now = LocalDate.now();
            if (now.isBefore(ChronoLocalDate.from(ChronoLocalDate.from(coupon.getStartDate()))) || now.isAfter(ChronoLocalDate.from(coupon.getEndDate()))) return Optional.empty();
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
