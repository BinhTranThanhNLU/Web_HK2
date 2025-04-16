package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Payment;

public class PaymentDao {

    private Jdbi jdbi;

    public PaymentDao() {
        this.jdbi = JDBIConnect.get();
    }

    public void insert(Payment payment) {
        String sql = "INSERT INTO payments (idOrder, paymentMethod, amount, status) " +
                "VALUES (:idOrder, :paymentMethod, :amount, :status)";

        jdbi.useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind("idOrder", payment.getOrder().getIdOrder())
                    .bind("paymentMethod", payment.getPaymentMethod())
                    .bind("amount", payment.getAmount())
                    .bind("status", payment.getStatus())
                    .execute();
        });
    }

}
