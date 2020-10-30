package com.example.imitationtaobao.bean;

public class ListBean {
    /**
     * id : 262
     * categoryId : 10
     * campaignId : 3
     * name : 苏泊尔（supor）炒锅少烟不粘锅电磁炉燃气通用32cm炒菜锅具EC32FP01
     * imgUrl : http://m.360buyimg.com/n4/jfs/t2302/348/574767572/148829/2ad5db57/5618ce19N91191a4d.jpg!q70.jpg
     * price : 189
     * sale : 8846
     */

    private int id;
    private int categoryId;
    private int campaignId;
    private String name;
    private String imgUrl;
    private float price;
    private int sale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}
