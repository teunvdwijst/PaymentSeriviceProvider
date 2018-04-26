package domain;

import java.util.Objects;

/**
 *
 * @author Teun
 */
public class InvoiceRequest {

    private String id;
    private double price;
    private String paymentMethod;

    public InvoiceRequest() {
    }

    public InvoiceRequest(String id, double price, String paymentMethod) {
        this.id = id;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "InvoiceRequest{" + "id=" + id + ", price=" + price + ", paymentMethod=" + paymentMethod + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.paymentMethod);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InvoiceRequest other = (InvoiceRequest) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.paymentMethod, other.paymentMethod);
    }
}
