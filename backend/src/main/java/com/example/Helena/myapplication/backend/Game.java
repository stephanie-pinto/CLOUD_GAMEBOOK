package com.example.Helena.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Helena on 01.11.2016.
 */


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long _id ;

    private String date ;
    private String heure ;
    private String team_res ;
    private String team_ext ;
    private String quantity ;
    //private String stade ;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getDate() {
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);*/
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {

        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getTeam_res() {
        return team_res;
    }

    public void setTeam_res(String team_res) {
        this.team_res = team_res;
    }

    public String getTeam_ext() {
        return team_ext;
    }

    public void setTeam_ext(String team_ext) {
        this.team_ext = team_ext;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
/*
    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }
*/

}





