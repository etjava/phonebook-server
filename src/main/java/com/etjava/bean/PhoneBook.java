package com.etjava.bean;

public class PhoneBook {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String teleNumber;
    private String email;
    private String workAddress;
    private String homeAddress;
    private String image;
    private String remark;
    private String initial;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTeleNumber() {
        return teleNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getImage() {
        return image;
    }

    public String getRemark() {
        return remark;
    }

    public String getInitial() {
        return initial;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", teleNumber='" + teleNumber + '\'' +
                ", email='" + email + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", image='" + image + '\'' +
                ", remark='" + remark + '\'' +
                ", initial='" + initial + '\'' +
                '}';
    }
}
