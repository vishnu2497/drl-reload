package com.ns.check.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class ResultantModel {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private long id;

    private String orderNumber;

    private int distributorId;

    private float personalCc;

    private float novusCc;

    private float nonManagerCc;

    private float directDiscount;

    private float personalDiscount;

    private float volumeBonus;

    private float novusBonus;

    private float novusProfit;

    private int sponserLevel;

    private int distributorLevel;

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

    public float getPersonalCc() {
        return personalCc;
    }

    public void setPersonalCc(float personalCc) {
        this.personalCc = personalCc;
    }

    public float getNovusCc() {
        return novusCc;
    }

    public void setNovusCc(float novusCCc) {
        this.novusCc = novusCCc;
    }

    public float getNonManagerCc() {
        return nonManagerCc;
    }

    public void setNonManagerCc(float nonManagerCc) {
        this.nonManagerCc = nonManagerCc;
    }

    public float getDirectDiscount() {
        return directDiscount;
    }

    public void setDirectDiscount(float directDiscount) {
        this.directDiscount = directDiscount;
    }

    public float getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(float personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public float getVolumeBonus() {
        return volumeBonus;
    }

    public void setVolumeBonus(float volumeBonus) {
        this.volumeBonus = volumeBonus;
    }

    public float getNovusBonus() {
        return novusBonus;
    }

    public void setNovusBonus(float novusBonus) {
        this.novusBonus = novusBonus;
    }

    public float getNovusProfit() {
        return novusProfit;
    }

    public void setNovusProfit(float novusProfit) {
        this.novusProfit = novusProfit;
    }

    public int getSponserLevel() {
        return sponserLevel;
    }

    public void setSponserLevel(int sponserLevel) {
        this.sponserLevel = sponserLevel;
    }




    public int getDistributorLevel() {
        return distributorLevel;
    }

    public void setDistributorLevel(int distributorLevel) {
        this.distributorLevel = distributorLevel;
    }

    @Override
    public String toString() {
        return "ResultantModel{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", distributorId='" + distributorId + '\'' +
                ", personalCc=" + personalCc +
                ", novusCCc=" + novusCc +
                ", nonManagerCc=" + nonManagerCc +
                ", directDiscount=" + directDiscount +
                ", personalDiscount=" + personalDiscount +
                ", volumeBonus=" + volumeBonus +
                ", novusBonus=" + novusBonus +
                ", novusProfit=" + novusProfit +
                ", sponserLevel=" + sponserLevel +
                ", distributorLevel=" + distributorLevel +
                '}';
    }


    public void print() {
        System.out.println("id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", distributorId='" + distributorId + '\'' +
                ", personalCc=" + personalCc +
                ", novusCCc=" + novusCc +
                ", nonManagerCc=" + nonManagerCc +
                ", directDiscount=" + directDiscount +
                ", personalDiscount=" + personalDiscount +
                ", volumeBonus=" + volumeBonus +
                ", novusBonus=" + novusBonus +
                ", novusProfit=" + novusProfit +
                ", sponserLevel=" + sponserLevel +
                ", distributorLevel=" + distributorLevel);
    }
}
