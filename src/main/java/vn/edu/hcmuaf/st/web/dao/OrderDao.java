package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Order;

public class OrderDao {

    private final Jdbi jdbi;

    public OrderDao() {
        this.jdbi = JDBIConnect.get();
    }

    //lấy ra id của order mới được thêm vào, viết tạm thời chưa dùng được
    public int insertOrder(Order order) {
        String sql = "INSERT INTO orders (idUser, idAddress, idCoupon, totalPrice) " +
                "VALUES (:idUser, :idAddress, :idCoupon, :totalPrice)";

        return jdbi.withHandle(handle -> {
            Update update = handle.createUpdate(sql)
                    .bind("idUser", order.getIdUser())
                    .bind("idAddress", order.getIdAddress())
                    .bind("idCoupon", order.getIdCoupon() != null ? order.getIdCoupon() : null)
                    .bind("totalPrice", order.getTotalPrice());

            int orderId = update.executeAndReturnGeneratedKeys("idOrder")
                    .mapTo(Integer.class)
                    .findOne()
                    .orElseThrow(() -> new RuntimeException("Failed to insert order"));

            return orderId;
        });
    }


}
