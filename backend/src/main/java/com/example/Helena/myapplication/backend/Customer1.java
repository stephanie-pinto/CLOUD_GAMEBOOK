package com.example.Helena.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Helena on 08.01.2017.
 */

@Entity
public class Customer1 {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private java.lang.Long _id ;

    private String nom ;
    private String prenom ;
    private String email ;
    private String mdp ;

    public java.lang.Long getId() {
        return _id;
    }

    public void setId(java.lang.Long id) {
        this._id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
