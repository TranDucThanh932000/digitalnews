/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author admin2
 */
public class Digital {

    private int id;
    private String title;
    private String content;
    private String shortDes;
    private String author;
    private Date timepublish;
    private String image;

    public Digital() {
    }

    public Digital(int id, String title, String content, String shortDes, String author, Date timepublish, String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortDes = shortDes;
        this.author = author;
        this.timepublish = timepublish;
        this.image = image;
    }

    public String getDateAfterFormat() {
        SimpleDateFormat formatDate1 = new SimpleDateFormat("MMM d yyyy - hh:mm");
        SimpleDateFormat formatDate2 = new SimpleDateFormat("a");
        return formatDate1.format(this.timepublish) + formatDate2.format(this.timepublish).toLowerCase();
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimepublish() {
        return timepublish;
    }

    public void setTimepublish(Date timepublish) {
        this.timepublish = timepublish;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return id + "\t" + title + "\t" + content + "\t" + shortDes + "\t" + author + "\t" + timepublish + "\t" + image;
    }

}
