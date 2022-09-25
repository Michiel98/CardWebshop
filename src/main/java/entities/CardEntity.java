package entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Card", schema = "hellodemo")
public class CardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "price", nullable = true, precision = 1)
    private float price;
    @Basic
    @Column(name = "type", nullable = true, length = 45)
    private String type;
    @Basic
    @Column(name = "set_id", nullable = true,insertable = false, updatable = false)
    private Integer setId;
    @Basic
    @Column(name = "set_name", nullable = true, length = 1)
    private String setName;
    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;
    @ManyToOne(optional = false)
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private SetEntity set;
    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL,mappedBy = "Card")
    private List<OrderedCardEntity> orderCardsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Objects.equals(price, that.price)) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (setId != null ? !setId.equals(that.setId) : that.setId != null) return false;
        if (setName != null ? !setName.equals(that.setName) : that.setName != null) return false;
        if (!Arrays.equals(image, that.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        //result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (setId != null ? setId.hashCode() : 0);
        result = 31 * result + (setName != null ? setName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public SetEntity getSet() {
        return set;
    }

    public void setSet(SetEntity set) {
        this.set = set;
    }

    public List<OrderedCardEntity> getOrderCardsList() {
        return orderCardsList;
    }

    public void setOrderCardsList(List<OrderedCardEntity> orderCardsList) {
        this.orderCardsList = orderCardsList;
    }
}
