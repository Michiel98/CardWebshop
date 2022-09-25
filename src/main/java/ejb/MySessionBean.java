package ejb;
import entities.*;
import jakarta.ejb.Stateless;
import jakarta.faces.bean.ManagedBean;
import jakarta.persistence.*;
import java.util.List;
@ManagedBean(name="SessionBean")
@jakarta.ejb.Stateless(name = "MySessionEJB")
public class MySessionBean {

    @PersistenceContext(unitName = "webshopPU")
    private EntityManager em;

    public MySessionBean() {
    }

    public List<CustomerEntity> findByName(String name){
        TypedQuery<CustomerEntity> query = this.em.createQuery("SELECT p FROM CustomerEntity p WHERE p.firstName LIKE :searchname", CustomerEntity.class);
        query.setParameter("searchname", name);
        query.setMaxResults(10);

        return query.getResultList();
    }

    public List<CustomerEntity> findByMail(String email){
        TypedQuery<CustomerEntity> query = this.em.createQuery("SELECT p FROM CustomerEntity p WHERE p.email LIKE :email", CustomerEntity.class);
        query.setParameter("email", email);
        query.setMaxResults(10);

        return query.getResultList();
    }

    public List<CardEntity> findCardByName(String name){
        TypedQuery<CardEntity> query = this.em.createQuery("SELECT p FROM CardEntity p WHERE p.name LIKE :cardName", CardEntity.class);
        query.setParameter("cardName", name);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<CardEntity> findBySet(String setId){
        TypedQuery<CardEntity> query = this.em.createQuery("SELECT p FROM CardEntity p WHERE p.setName LIKE :id", CardEntity.class);
        query.setParameter("id", setId);
        return query.getResultList();
    }



    public List<CustomerEntity> findcustomer(String email, String pwd){
        TypedQuery<CustomerEntity> query = this.em.createQuery("SELECT p FROM CustomerEntity p WHERE p.email LIKE :mailAddress AND p.password LIKE :passWord", CustomerEntity.class);
        query.setParameter("mailAddress", email);
        query.setParameter("passWord", pwd);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<OrderEntity> findOrders(){
        Query query = this.em.createNativeQuery("SELECT * FROM `Order` ", OrderEntity.class);
        return query.getResultList();
    }


    public CustomerEntity setNewUser(CustomerEntity customer){
        em.persist(customer);
        em.flush();
        return customer;
    }

    public OrderEntity setNewOrder(OrderEntity order){

        em.flush();
        em.persist(order);
        return order;
    }

    public void persistOrderedCards(OrderedCardEntity card){
        em.persist(card);
    }
}
