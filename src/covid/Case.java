/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

/**
 *
 * @author stefm
 */
public class Case {
    private String name;
    private String surname;
    private int age;
    private String address;
    private String city;
    private int AMKA;
    private int phone;
    private String gender;
    public Case(String name,String sname,int age, String ad, String city, int amka, int phone,String gender){
        this.name=name;
        this.surname=sname;
        this.age=age;
        this.address=ad;
        this.city=city;
        this.AMKA=amka;
        this.phone=phone;
        this.gender=gender;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name=n;
    }
    public String getSurname(){
        return this.surname;
    }
    public void setSurname(String sn){
        this.surname=sn;
    }
    public int getAge(){
        return this.age;
    }
    public void setName(int age){
        this.age=age;
    }
    public String getAd(){
        return this.address;
    }
    public void setAd(String ad){
        this.address=ad;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String c){
        this.city=c;
    }
    public int getAmka(){
        return this.AMKA;
    }
    public void setAmka(int a){
        this.AMKA=a;
    }
    public int getPhone(){
        return this.phone;
    }
    public void setPhone(int p){
        this.phone=p;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String g){
        this.gender=g;
    }   
}

