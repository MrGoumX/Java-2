 public class CardContract extends Program{
    //Class CardContract inherited from Program
    private double balance;
    private int mins,sms;
    public CardContract(String name, double price, int mins, int sms, double balance){
        super(name,price);
        this.mins=mins;
        this.sms=sms;
        this.balance=balance;
    }
    public CardContract(){
        super();
    }
     public void setBalance(double balance){
        this.balance=balance;
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
     public double getBalance(){
            return balance;
        }
     public double getDiscount(){
            return 0.25;
        }
     public String getName(){
            return name;
        }
     public String toString(){
         return "Contract Type: Card Contract ||"+super.toString() + " || Free minutes: " + getMins() + " || Free sms: " + getSms()+ " || Balance: " + getBalance() + " Euro";
    }
}