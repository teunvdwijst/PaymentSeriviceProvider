package domain;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Teun
 */
public class TableRow {

    private final SimpleStringProperty request;
    private final SimpleStringProperty reply;

    public TableRow(String request, String reply) {
        this.reply = new SimpleStringProperty(reply);
        this.request = new SimpleStringProperty(request);
    }

    public String getRequest() {
        return request.get();
    }

    public String getReply() {
        return reply.get();
    }

    public void setReply(String reply) {
        this.reply.set(reply);
    }

    public void setRequest(String request) {
        this.request.set(request);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.request);
        hash = 67 * hash + Objects.hashCode(this.reply);
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
        final TableRow other = (TableRow) obj;
        if (!Objects.equals(this.request, other.request)) {
            return false;
        }
        return Objects.equals(this.reply, other.reply);
    }
}
