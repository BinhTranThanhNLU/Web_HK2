package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CouponDAO {
    private final Jdbi jdbi;

    public CouponDAO() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Coupon> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons")
                        .mapToBean(Coupon.class)
                        .list()
        );
    }

    public Optional<Coupon> getById(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .mapToBean(Coupon.class)
                        .findOne()
        );
    }

    public Optional<Coupon> getByCode(String code) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE code = :code")
                        .bind("code", code)
                        .mapToBean(Coupon.class)
                        .findOne()
        );
    }

    public boolean add(Coupon coupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO coupons (code, discountAmount, isPercentage, minOrderValue, startDate, endDate, usageLimit, usedCount) " +
                                "VALUES (:code, :discountAmount, :isPercentage, :minOrderValue, :startDate, :endDate, :usageLimit, :usedCount)")
                        .bind("code", coupon.getCode())
                        .bind("discountAmount", coupon.getDiscountAmount())
                        .bind("isPercentage", coupon.isPercentage())
                        .bind("minOrderValue", coupon.getMinOrderValue())
                        .bind("startDate", coupon.getStartDate())
                        .bind("endDate", coupon.getEndDate())
                        .bind("usageLimit", coupon.getUsageLimit())
                        .bind("usedCount", coupon.getUsedCount())
                        .execute() > 0
        );
    }

    public boolean update(Coupon coupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE coupons SET code = :code, discountAmount = :discountAmount, isPercentage = :isPercentage, " +
                                "minOrderValue = :minOrderValue, startDate = :startDate, endDate = :endDate, usageLimit = :usageLimit, usedCount = :usedCount " +
                                "WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", coupon.getIdCoupon())
                        .bind("code", coupon.getCode())
                        .bind("discountAmount", coupon.getDiscountAmount())
                        .bind("isPercentage", coupon.isPercentage())
                        .bind("minOrderValue", coupon.getMinOrderValue())
                        .bind("startDate", java.sql.Timestamp.valueOf(coupon.getStartDate()))
                        .bind("endDate", java.sql.Timestamp.valueOf(coupon.getEndDate()))
                        .bind("usageLimit", coupon.getUsageLimit())
                        .bind("usedCount", coupon.getUsedCount())
                        .execute() > 0
        );
    }

    public boolean delete(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM coupons WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .execute() > 0
        );
    }

    public boolean incrementUsedCount(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE coupons SET usedCount = usedCount + 1 WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .execute() > 0
        );
    }

    public boolean isCouponValid(Coupon coupon) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(coupon.getStartDate()) &&
                now.isBefore(coupon.getEndDate()) &&
                coupon.getUsedCount() < coupon.getUsageLimit();
    }

}
