public class ActiveContracts {
    //Class ActiveContracts, Creates an object that declares an active contract
    private Program con;
    private Statistics stat;
    private String name;
    private String phone;
    private String date;
    private String payway;
    private int code;
    public ActiveContracts(Program con, String name,int code, String phone, String date, String payway) {
        this.con = con;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.payway = payway;
        this.code=code;
        this.stat = new Statistics(this);
    }
    public ActiveContracts() {
        this.stat = new Statistics(this);
    }
    public void setCon(Program con){
        this.con=con;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setCode(int code){
        this.code=code;
    }
    public void setPayway(String payway){
        this.payway=payway;
    }
    public Program getProgram() {
    	return con;
    }
    public int getCode(){
    	return code;
    }
    public Statistics getStats() {
    	return stat;
    }
    public String getOwner(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getDate(){
        return date;
    }
    public String getPayway(){
        return payway;
    }
    public String toString(){
        return  con + " || ID Code: "+getCode() + " || Owner: " + getOwner() + " || Phone number: "  + getPhone() + " || Activation Date: " + getDate() + " || Payment method: " + getPayway();
    }
    public String getContents(){
        return  con + " || ID Code: "+getCode() + " || Owner: " + getOwner();
    }
}
