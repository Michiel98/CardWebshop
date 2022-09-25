package entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Order", schema = "hellodemo")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    private float amount;
    @Basic
    @Column(name = "date_created", nullable = true)
    private Timestamp dateCreated;
    @Basic
    @Column(name = "confirmation_number", nullable = true)
    private Integer confirmationNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderedCardEntity> OrderedCardEntityList;

    @JoinColumn(name = "CustomerId",  referencedColumnName = "id",nullable = true)
    @ManyToOne(optional = false)
    private CustomerEntity customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(amount, that.amount)) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (confirmationNumber != null ? !confirmationNumber.equals(that.confirmationNumber) : that.confirmationNumber != null)
            return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = (31 * result) + ((amount != null) ? amount.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (confirmationNumber != null ? confirmationNumber.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    public List<OrderedCardEntity> getOrderedCardEntityList() {
        return OrderedCardEntityList;
    }

    public void setOrderedCardEntityList(List<OrderedCardEntity> orderedCardEntityList) {
        OrderedCardEntityList = orderedCardEntityList;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
