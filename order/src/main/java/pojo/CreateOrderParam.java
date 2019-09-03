package pojo;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/27.
 */
public class CreateOrderParam {
    private int paymentType;
    private String payment;
    private Tb_order_shipping orderShipping;
    private List<Tb_order_item> orderItems;

    public List<Tb_order_item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Tb_order_item> orderItems) {
        this.orderItems = orderItems;
    }

    public Tb_order_shipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(Tb_order_shipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
    @Override
    public String toString() {
        return "CreateOrderParam{" +
                "orderItems=" + orderItems +
                ", paymentType=" + paymentType +
                ", payment='" + payment + '\'' +
                ", orderShipping=" + orderShipping +
                '}';
    }
}
