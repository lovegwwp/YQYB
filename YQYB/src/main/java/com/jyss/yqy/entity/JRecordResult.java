package com.jyss.yqy.entity;


import java.io.Serializable;

public class JRecordResult implements Serializable{

    private Float amount;      //总金额
    private Float cashScore;    //股券
    private Float shoppingScore;   //消费券
    private Float elecScore;     //电子券

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getCashScore() {
        return cashScore;
    }

    public void setCashScore(Float cashScore) {
        this.cashScore = cashScore;
    }

    public Float getShoppingScore() {
        return shoppingScore;
    }

    public void setShoppingScore(Float shoppingScore) {
        this.shoppingScore = shoppingScore;
    }

    public Float getElecScore() {
        return elecScore;
    }

    public void setElecScore(Float elecScore) {
        this.elecScore = elecScore;
    }
}
