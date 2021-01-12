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
public class Region {
    private int reg_id;
    private String reg_name;
    public Region(String rn,int id){
        this.reg_id=id;
        this.reg_name=rn;
    }
    public String getRegName(){
        return this.reg_name;
    }
    public void setRegName(String rn){
        this.reg_name=rn;
    }
    public int getRegId(){
        return this.reg_id;
    }
    public void setRegId(int rd){
        this.reg_id=rd;
    }
}
