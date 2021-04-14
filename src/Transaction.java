import java.util.Date;

public class Transaction {
    Date date;
    int cost;
    String transaction;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", cost=" + cost +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
