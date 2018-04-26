package domain;

import java.util.Objects;

/**
 *
 * @author Teun
 */
public class InvoiceReply {

    private String id;
    private double price;
    private InvoiceRequest request;

    public InvoiceReply() {
    }

    public InvoiceReply(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public InvoiceReply(String id, double price, InvoiceRequest request) {
        this.id = id;
        this.price = price;
        this.request = request;
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

    public InvoiceRequest getRequest() {
        return request;
    }

    public void setRequest(InvoiceRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "InvoiceReply{" + "id=" + id + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final InvoiceReply other = (InvoiceReply) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
}
