package com.ns.check.Model;

import javax.persistence.*;

@Entity
@Table(name = "userdetails")
public class UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private int distributorId;

    private String name;

    private int level;

    private int sponserid;


    @Transient
    private ResultantModel resultantModelData;

    @Transient
    private int nextLevel;

    public ResultantModel getResultantModelData() {
          return resultantModelData;
    }

    public void setResultantModelData(ResultantModel resultantModelData) {
        this.resultantModelData = resultantModelData;
    }


    public int getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSponserid() {
        return sponserid;
    }

    public void setSponserid(int sponserid) {
        this.sponserid = sponserid;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public UserDetails() {
    }

    public UserDetails(int distributorId, String name, int level, int sponserId) {
        this.distributorId = distributorId;
        this.name = name;
        this.level = level;
        this.sponserid = sponserId;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", distributorId=" + distributorId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", sponserid=" + sponserid +
                ", nextLevel=" + nextLevel +
                '}';
    }
}
