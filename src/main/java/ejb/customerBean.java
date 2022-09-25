package ejb;

import entities.*;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

// SESSIONSCOPED

@Named("customerBean")
@SessionScoped
@Stateful(name = "customerBean")
@StatefulTimeout(value = 5, unit = TimeUnit.MINUTES)
//@ManagedBean(name = "customerBean")

public class customerBean implements Serializable {

    @EJB
    private MySessionBean myBean;

    //@EJB
    //private timerBean timer;

    private int setId = 5;
    private List<CardEntity> cardList = new ArrayList<>();
    private Map<String, Boolean> checked = new HashMap<String, Boolean>();
    private List<CardEntity> cardsChecked = new ArrayList<>();
    private int deliveryId = 1;

    private String sessionTime = "";
    private String username; //is email but used for logging in instead of nickname
    private String password;
    private String text;
    private boolean loggedIn = false;
    private CustomerEntity customer;
    private int confirmationNumber;

    private String firstname;
    private String lastname;
    private String address1;

    private String address2;
    private String text2 = null;

    public void setUserWhenSignedIn(CustomerEntity customer){
        this.customer = customer;
        loggedIn = true;
    }

    public String setSetid(int id) {
        setId = id;
        return "/html/product.xhtml";
    }

    public String submit() {
        for (CardEntity card : cardList) {
            if (checked.get(card.getName())) {
                if(!cardsChecked.contains(card)){
                    cardsChecked.add(card);
                }
            }
        }
        return "/html/order.xhtml";
    }

    public float getPrice(){
        float price = 0;
        for(CardEntity card : cardsChecked){
            price += card.getPrice();
        }
        float roundedPrice = (float)Math.round(price*100) / 100;

        return roundedPrice;
        //return price;
    }

    public String logout(){
        //timer.setSessionSeconds(0);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/html/home.xhtml";
    }


    public String userValidation(){
        List<CustomerEntity> customers = this.myBean.findcustomer(getUsername(), getPassword());

        if(customers.isEmpty()){
            loggedIn = false;
            text = "Invalid username or password, try again with a valid username and password";
            return "/html/login.xhtml";
        }
        else{
            customer = customers.get(0);
            loggedIn = true;
            firstname = customer.getFirstName();
            lastname = customer.getSecondName();
            address1 = customer.getFirstLineAddress();
            address2 = customer.getSecondLineAddress();
            text = "";
            return "/html/userHome.xhtml";
        }
    }

    public String getText() {
        return text;
    }

    public String getNextPage() {
        if (loggedIn) {
            return "/html/userHome.xhtml";
        } else {
            return "/html/home.xhtml";
        }
    }
    public String buy(){

        if(getPrice()==0){
            text = "There is nothing to checkout.";
            return "/html/checkout.xhtml";
        }else{
            // Add order to order table
            //Date date = new Date();
            //java.sql.Timestamp timestamp = new Timestamp(date.getTime());
            OrderEntity customerOrder = new OrderEntity();
            customerOrder.setAmount(getPrice());
            //customerOrder.setDateCreated(timestamp);

            // create confirmation number
            Random random = new Random();
            confirmationNumber = random.nextInt(999999999);
            customerOrder.setConfirmationNumber(confirmationNumber);

            CustomerEntity user = this.myBean.findByMail(username).get(0);

            customerOrder.setCustomer(user);
            this.myBean.setNewOrder(customerOrder);

            // Add all the ordered cards to the ordered_card table
            List<OrderEntity>orders = this.myBean.findOrders();
            int orderId = 0;
            for(OrderEntity var: orders){
                orderId ++;
                if(var.getConfirmationNumber() == confirmationNumber){
                    orderId = var.getId();
                }
            }
            for(CardEntity card: cardsChecked){
                OrderedCardEntityPK orderedCardPK = new OrderedCardEntityPK();
                //orderedCardPK.setOrderId(orderId);
                orderedCardPK.setCardId(card.getId());

                OrderedCardEntity orderedCard = new OrderedCardEntity(orderedCardPK);
                orderedCard.setQuantity(1);

                this.myBean.persistOrderedCards(orderedCard);
            }

            text = "";
            return "/html/confirmation.xhtml";

        }
    }

    public void timer(){
        //timer.setTimerService(1000);
    }

    public void updateTime(){
        //this.sessionTime = timer.getSessionTime();
    }

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/html/userHome.xhtml");
    }

    public String setUser() {
        List<CustomerEntity> customers = this.myBean.findByMail(username);
        if (customers.isEmpty()) {
            CustomerEntity newUser = new CustomerEntity();

            newUser.setFirstName(firstname);
            newUser.setSecondName(lastname);
            newUser.setEmail(username);

            newUser.setFirstLineAddress(address1);
            newUser.setSecondLineAddress(address2);
            newUser.setPassword(password);

            loggedIn = true;

            this.myBean.setNewUser(newUser);
            return "/html/userHome.xhtml";
        } else {
            text2= "The provided email address is already in use";
            return "/html/signup.xhtml";
        }
    }

    // Getters and Setters
    public String getConfirmationNumber(){ return Integer.toString(confirmationNumber); }
    public void setDeliveryId(int optionId){ deliveryId = optionId; }
    public int getDeliveryId(){ return deliveryId; }
    public String getUsername() { return username; }
    public void setUsername(String username){ this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password){ this.password = password; }
    public List<CardEntity> getcardsChecked() { return cardsChecked; }
    public int getSetId() { return setId; }
    public Map<String, Boolean> getChecked() { return checked; }
    public List<CardEntity> getCards()
    {
        cardList = this.myBean.findBySet(Integer.toString(setId));
        return cardList;
    }
    public boolean isLoggedIn(){ return loggedIn; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getAddress1() { return address1; }
    public String getText2(){return text2;}
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setAddress1(String address1){
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @PostConstruct
    public void init(){ setDeliveryId(1); }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }
}
