package covid;
public class ProbableCase extends Case{
    String RelatedId;
    public ProbableCase(String RelatedId,String ID,String name ,String sname,int age, String ad, String city, String amka, String phone, String Date){
        super(ID,name,sname,age,ad,city,amka,phone,Date);
        this.RelatedId = RelatedId;
    }
    public String getRelID(){
        return this.RelatedId;
    }
    public void setRelID(String RelId){
        this.RelatedId= RelId;
    }


}



