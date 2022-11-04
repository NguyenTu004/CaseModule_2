package user;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable{
    private String account;
    private String password;
    private String username;
    private String numPhone;
    private String address;
    private String information;
    private double sumMoney;
    private LocalDate buyDate;

    public User() {
    }

    public User(String account, String password, String username, String numPhone) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.numPhone = numPhone;
    }

    public User(String username, String numPhone, String address, String information, double sumMoney,LocalDate buyDate) {
        this.username = username;
        this.numPhone = numPhone;
        this.address = address;
        this.information = information;
        this.sumMoney = sumMoney;
        this.buyDate = buyDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public String toStringDate() {
        return buyDate.getDayOfMonth() +"/"+buyDate.getMonth()+"/"+buyDate.getYear();
    }
    @Override
    public String toString() {
        return "username= " + username +
                ", numPhone= " + numPhone +
                ", address= " + address +
                ", information=\t[" + information +"]" +
                ", buyDate= " + buyDate +
                ", sumMoney= " + sumMoney;
    }
}
