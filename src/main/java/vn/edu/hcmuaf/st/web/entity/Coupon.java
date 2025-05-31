package vn.edu.hcmuaf.st.web.entity;

import java.sql.Date;

public class Coupon {
    private int idCoupon;
    private String code;
    private double discountAmount;
    private boolean isPercentage;
    private double minOrderValue;
    private Date startDate;
    private Date endDate;
    private int usageLimit;
    private int usedCount;

    // Constructor không tham số
    public Coupon() {
    }

    // Constructor đầy đủ
    public Coupon(int idCoupon, String code, double discountAmount, boolean isPercentage,
                  double minOrderValue, Date startDate, Date endDate, int usageLimit, int usedCount) {
        this.idCoupon = idCoupon;
        this.code = code;
        this.discountAmount = discountAmount;
        this.isPercentage = isPercentage;
        this.minOrderValue = minOrderValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
        this.usedCount = usedCount;
    }

    // Getters và Setters

    public int getIdCoupon() {
        return idCoupon;
    }

    public void setIdCoupon(int idCoupon) {
        this.idCoupon = idCoupon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public void setPercentage(boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }
}
