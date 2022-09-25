package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderedCardEntityPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "order_id")
    private int orderId;

    @Basic(optional = false)
    @Column(name = "card_id")
    private int cardId;

    public OrderedCardEntityPK(int orderId, int cardId){
        this.orderId = orderId;
        this.cardId = cardId;
    }

    public OrderedCardEntityPK(){

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) cardId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderedCardEntity)) {
            return false;
        }
        OrderedCardEntityPK other = (OrderedCardEntityPK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        return this.cardId == other.cardId;
    }

    @Override
    public String toString() {
        return "entity.OrderedBookEntityPK[studentOrderId=" + orderId + ", bookId=" + cardId + "]";
    }
}
