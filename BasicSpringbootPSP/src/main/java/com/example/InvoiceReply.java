package com.example;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Teun
 */
@Entity
public class InvoiceReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String intent;
    private String createTime;
    @OneToOne(optional = true)
    private InvoiceRequest request;

    public InvoiceReply() {
    }

    public InvoiceReply(String status, String intent, String createTime) {
        this.status = status;
        this.intent = intent;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InvoiceRequest getRequest() {
        return request;
    }

    public void setRequest(InvoiceRequest request) {
        this.request = request;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InvoiceReply{" + "id=" + id + ", status=" + status + ", intent=" + intent + ", createTime=" + createTime + ", request=" + request + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(this.id, other.id);
    }
}
