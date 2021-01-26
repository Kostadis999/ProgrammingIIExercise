/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;
import java.sql.ResultSet;
public class Case {
    private String ID;
    private String name;
    private String surname;
    private int age;
    private String address;
    private String city;
    private String AMKA;
    private String phone;
    private String Date;
    
    public Case(String ID,String name ,String sname,int age, String ad, String city, String amka, String phone, String Date){
        this.ID = ID;
        this.name=name;
        this.surname=sname;
        this.age=age;
        this.address=ad;
        this.city=city;
        this.AMKA=amka;
        this.phone=phone;
        this.Date = Date;
    }

 

    Case(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getID(){
        return this.ID;
    }
    public void setID(String n){
        this.ID=n;
    }
    public String getDATE(){
        return this.Date;
    }
    public void setDATE(String n){
        this.Date=n;
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
    public String getAmka(){
        return this.AMKA;
    }
    public void setAmka(String a){
        this.AMKA=a;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String p){
        this.phone=p;
    }
  
   
   
   
}

