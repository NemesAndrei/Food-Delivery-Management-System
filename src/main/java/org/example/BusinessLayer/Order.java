package org.example.BusinessLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Nemes Mihnea-Andrei
 */
public class Order implements Serializable {

    /**
     * id of the order
     */
    private String orderId;

    /**
     * id of the client that created the order
     */
    private String clientId;

    /**
     * name of the client that created the order
     */
    private String clientName;

    /**
     * date that the order was created on
     */
    private Calendar date;

    /**
     * final price of the order
     */
    private Double finalPrice;

    /**
     * total protein count of the order
     */
    private int finalProtein;

    /**
     * @param clientId     id of the client who made the order
     * @param clientName   name of the client who made the order
     * @param finalPrice   final price of the order
     * @param finalProtein final protein count of the order
     *                     constructor for the order containing the clientid, name, the final price and the protein count
     */
    public Order(String clientId, String clientName, Double finalPrice, int finalProtein) {
        this.orderId = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.clientName = clientName;
        this.date = Calendar.getInstance();
        this.finalPrice = finalPrice;
        this.finalProtein = finalProtein;
    }

    /**
     * @return the id of the order
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the id to be given to the order
     *                sets the id of the order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the id of the client
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the id of the client to be given to the order
     *                 sets the id of the client
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the name of the client
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the name of the client to be given to the order
     *                   sets the name of the client
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the date of the order
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * @param date the date to be given to the order
     *             sets the date of the order
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * @return final price of the order
     */
    public Double getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice final price to be given to the order
     *                   sets the final price of the order
     */
    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * @return final protein count of the order
     */
    public int getFinalProtein() {
        return finalProtein;
    }

    /**
     * @param finalProtein final protein count to be given to the order
     *                     sets the final protein count of the order
     */
    public void setFinalProtein(int finalProtein) {
        this.finalProtein = finalProtein;
    }

    /**
     * @param o the object that will be compared
     * @return true if the two orders are equal, or false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return orderId.equals(order.orderId) && clientId.equals(order.clientId) && Objects.equals(date, order.date);
    }
}
