package ro.fasttrackit.curs20.budgetapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    private String product;
    private Type type;
    private double amount;

    public Transaction() {

    }

    public Transaction(String product, Type type, double amount) {
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
