package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;

public class OrderDetailDao {

    private Jdbi jdbi;

    public OrderDetailDao() {
        this.jdbi = JDBIConnect.get();
    }

    public void insert(OrderDetail orderDetail) {
        String sql = "INSERT INTO order_detail (idOrder, idVariant, quantity, price, discountPrice) " +
                "VALUES (:idOrder, :idVariant, :quantity, :price, :discountPrice)";

        jdbi.useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind("idOrder", orderDetail.getOrder().getIdOrder())
                    .bind("idVariant", orderDetail.getIdVariant())
                    .bind("quantity", orderDetail.getQuantity())
                    .bind("price", orderDetail.getPrice())
                    .bind("discountPrice", orderDetail.getDiscountPrice())
                    .execute();
        });
    }


}
