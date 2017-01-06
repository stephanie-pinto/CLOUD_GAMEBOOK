package com.example.Helena.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Helena on 01.11.2016.
 */

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private java.lang.Long _id ;

    private String num_seat ;
    private Game game;
    //private Customer customer;


    public java.lang.Long getId() {
        return _id;
    }

    public void setId(java.lang.Long id) {
        this._id = id;
    }

    public String getNum_seat() {
        return num_seat;
    }

    public void setNum_seat(String num_seat) {
        this.num_seat = num_seat;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
/*
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/
}
