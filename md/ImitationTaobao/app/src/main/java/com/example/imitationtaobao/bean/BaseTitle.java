package com.example.imitationtaobao.bean;

public class BaseTitle<T> {
    private int id;
    private String title;
    private int campaignOne;
    private int campaignTwo;
    private int campaignThree;
    private T cpOne;
    private T cpTwo;
    private T cpThree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCampaignOne() {
        return campaignOne;
    }

    public void setCampaignOne(int campaignOne) {
        this.campaignOne = campaignOne;
    }

    public int getCampaignTwo() {
        return campaignTwo;
    }

    public void setCampaignTwo(int campaignTwo) {
        this.campaignTwo = campaignTwo;
    }

    public int getCampaignThree() {
        return campaignThree;
    }

    public void setCampaignThree(int campaignThree) {
        this.campaignThree = campaignThree;
    }

    public T getCpOne() {
        return cpOne;
    }

    public void setCpOne(T cpOne) {
        this.cpOne = cpOne;
    }

    public T getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(T cpTwo) {
        this.cpTwo = cpTwo;
    }

    public T getCpThree() {
        return cpThree;
    }

    public void setCpThree(T cpThree) {
        this.cpThree = cpThree;
    }
}
