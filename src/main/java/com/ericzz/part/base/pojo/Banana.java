/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.part.base.pojo;

/** 
 * 
  * @author zz_huns  
 * @version Id: Banana.java, v 0.1 2019/2/7 12:57 PM zz_huns Exp $$
  */
public class Banana {
    private String color;

    private Integer weight;

    private String country;

    private Integer price;


    public Banana(Integer weight) {
        this.weight = weight;
    }

    public Banana() {

    }

    public Banana(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
