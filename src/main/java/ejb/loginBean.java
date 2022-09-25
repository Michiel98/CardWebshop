package ejb;

@jakarta.ejb.Stateless(name = "loginEJB")
public class loginBean {
    public loginBean() {
    }
    public int doSomethingReallyDifficult(int a, int b){
        return a + b;
    }
}
