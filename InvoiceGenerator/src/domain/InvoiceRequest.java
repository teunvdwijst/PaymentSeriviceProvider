package domain;

import java.util.Objects;

/**
 *
 * @author Teun
 */
public class InvoiceRequest {

    private Long id;
    private String currency;
    private double price;
    private String paymentMethod;

    public InvoiceRequest() {
    }

    public InvoiceRequest(double price, String currency, String paymentMethod) {
        this.price = price;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "id=" + id + ", price=" + price + ", currency=" + currency + ", paymentMethod=" + paymentMethod;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(this.id, other.id);
    }
}
