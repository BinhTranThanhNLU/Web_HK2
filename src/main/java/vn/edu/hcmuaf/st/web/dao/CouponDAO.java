package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CouponDAO {
    private final Jdbi jdbi;

    public CouponDAO() {
        this.jdbi = JDBIConnect.get();
    }

    // Custom mapper vì mapToBean không hỗ trợ enum tốt
    private final RowMapper<Coupon> couponMapper = (rs, ctx) -> {
        Coupon coupon = new Coupon();
        coupon.setIdCoupon(rs.getInt("idCoupon"));
        coupon.setCode(rs.getString("code"));
        coupon.setDiscountAmount(rs.getDouble("discountAmount"));
        coupon.setPercentage(rs.getBoolean("isPercentage"));
        coupon.setMinOrderValue(rs.getDouble("minOrderValue"));
        coupon.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
        coupon.setEndDate(rs.getTimestamp("endDate").toLocalDateTime());
        coupon.setUsageLimit(rs.getInt("usageLimit"));
        coupon.setUsedCount(rs.getInt("usedCount"));

        String type = rs.getString("discountType");
        if (type != null) {
            coupon.setDiscountType(Coupon.DiscountType.valueOf(type.toUpperCase()));
        }

        return coupon;
    };

    public List<Coupon> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons")
                        .map(couponMapper)
                        .list()
        );
    }

    public Optional<Coupon> getById(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .map(couponMapper)
                        .findOne()
        );
    }

    public Optional<Coupon> getByCode(String code) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE code = :code")
                        .bind("code", code)
                        .map(couponMapper)
                        .findOne()
        );
    }

    public boolean add(Coupon coupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO coupons (code, discountAmount, isPercentage, minOrderValue, startDate, endDate, usageLimit, usedCount, discountType) " +
                                "VALUES (:code, :discountAmount, :isPercentage, :minOrderValue, :startDate, :endDate, :usageLimit, :usedCount, :discountType)")
                        .bind("code", coupon.getCode())
                        .bind("discountAmount", coupon.getDiscountAmount())
                        .bind("isPercentage", coupon.isPercentage())
                        .bind("minOrderValue", coupon.getMinOrderValue())
                        .bind("startDate", java.sql.Timestamp.valueOf(coupon.getStartDate()))
                        .bind("endDate", java.sql.Timestamp.valueOf(coupon.getEndDate()))
                        .bind("usageLimit", coupon.getUsageLimit())
                        .bind("usedCount", coupon.getUsedCount())
                        .bind("discountType", coupon.getDiscountType().name())
                        .execute() > 0
        );
    }

    public boolean update(Coupon coupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE coupons SET code = :code, discountAmount = :discountAmount, isPercentage = :isPercentage, " +
                                "minOrderValue = :minOrderValue, startDate = :startDate, endDate = :endDate, usageLimit = :usageLimit, usedCount = :usedCount, " +
                                "discountType = :discountType WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", coupon.getIdCoupon())
                        .bind("code", coupon.getCode())
                        .bind("discountAmount", coupon.getDiscountAmount())
                        .bind("isPercentage", coupon.isPercentage())
                        .bind("minOrderValue", coupon.getMinOrderValue())
                        .bind("startDate", java.sql.Timestamp.valueOf(coupon.getStartDate()))
                        .bind("endDate", java.sql.Timestamp.valueOf(coupon.getEndDate()))
                        .bind("usageLimit", coupon.getUsageLimit())
                        .bind("usedCount", coupon.getUsedCount())
                        .bind("discountType", coupon.getDiscountType().name())
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
