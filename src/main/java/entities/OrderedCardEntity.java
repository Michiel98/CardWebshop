package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Ordered_card", schema = "hellodemo")
public class OrderedCardEntity {

    private static final long serialVersionUID = 1L;

    @jakarta.persistence.EmbeddedId
    protected OrderedCardEntityPK orderedCardEntityPK;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @JoinColumn(name = "card_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CardEntity card;

    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrderEntity order;

    public OrderedCardEntity(OrderedCardEntityPK orderedCardEntityPK){this.orderedCardEntityPK = orderedCardEntityPK;}

    public OrderedCardEntity(OrderedCardEntityPK orderedCardEntityPK, int quantity){
        this.orderedCardEntityPK = orderedCardEntityPK;
        this.quantity = quantity;
    }

    public OrderedCardEntity(int OrderId, int cardId){
        this.orderedCardEntityPK = new OrderedCardEntityPK(OrderId, cardId);
    }

    public OrderedCardEntity(){

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderedCardEntityPK getOrderedCardEntityPK(){return orderedCardEntityPK;}

    public void setOrderedCardEntityPK(OrderedCardEntityPK orderedCardEntityPK){
        this.orderedCardEntityPK = orderedCardEntityPK;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedCardEntityPK != null ? orderedCardEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderedCardEntity)) {
            return false;
        }
        OrderedCardEntity other = (OrderedCardEntity) object;
        if ((this.orderedCardEntityPK == null && other.orderedCardEntityPK != null) || (this.orderedCardEntityPK != null && !this.orderedCardEntityPK.equals(other.orderedCardEntityPK))) {
            return false;
        }
        return true;
    }

}
