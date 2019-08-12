package com.ns.check.Model;

import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderNumber;

    private float cc;

    private float orderPrice;

    private int distributorId;

    public Purchase() {
    }

    public Purchase(String orderNumber, int distributorId, float orderPrice, float cc) {
        this.orderNumber = orderNumber;
        this.distributorId = distributorId;
        this.orderPrice = orderPrice;
        this.cc = cc;
    }

    public Purchase(long id, String orderNumber, int distributorId, float orderPrice, float cc) {
        this.orderNumber = orderNumber;
        this.distributorId = distributorId;
        this.orderPrice = orderPrice;
        this.cc = cc;
        this.id = id;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public float getCc() {
        return cc;
    }

    public void setCc(float cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", cc=" + cc +
                ", orderPrice=" + orderPrice +
                ", distributorId=" + distributorId +
                '}';
    }
}
