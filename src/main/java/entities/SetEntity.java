package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Set", schema = "hellodemo")
public class SetEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL, mappedBy = "set")
    private List<CardEntity> cardList;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetEntity setEntity = (SetEntity) o;

        if (id != setEntity.id) return false;
        if (name != null ? !name.equals(setEntity.name) : setEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public List<CardEntity> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardEntity> cardList) {
        this.cardList = cardList;
    }
}
