public class Contract extends Program{
    //Class Contract inherited from Program
    private int mins;
    private int sms;
    public Contract(String name, double price, int mins, int sms){
        super(name,price);
        this.mins=mins;
        this.sms=sms;
    }
    public Contract(){
        super();
    }
    public void setMinutes(int mins){
        this.mins=mins;
    }
    public void setSms(int sms){
        this.sms=sms;
    }
    public int getMins(){
        return mins;
    }
    public int getSms(){
        return sms;
    }
    public double getPrice(){
        return price;
    }
    public double getDiscount(){
        return 0.2;
    }
    public String getName(){
    	return name;
    }
    public String toString(){
        return "Contract Type: Normal Contract ||"+super.toString() + " || Free minutes: " + getMins() + " || Free sms: " + getSms();
    }
}
