package io.yelp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUSINESS_ID")
    private Long business_id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE_URL")
    private String mobile_url;

    @Column(name ="PHONE_NUMBER")
    private String phone;

    @Column(name ="RATING")
    private String rating;

    @Column(name = "REVIEW_COUNT")
    private String review_count;

    @Column(name = "IS_CLOSE")
    private String is_close;

    @Column(name = "SNIPPET_TEXT")
    private String snippet_text;

    @Column(name = "IMAGE")
    private String image_url;

    @Column(name = "STAR_COUNT")
    private String rating_img_url_large;


    public Long getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Long business_id) {
        this.business_id = business_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public String getIs_close() {
        return is_close;
    }

    public void setIs_close(String is_close) {
        this.is_close = is_close;
    }

    public String getSnippet_text() {
        return snippet_text;
    }

    public void setSnippet_text(String snippet_text) {
        this.snippet_text = snippet_text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    public void setRating_img_url_large(String rating_img_url_large) {
        this.rating_img_url_large = rating_img_url_large;
    }

}

