public class Internet extends Program{
    //Class Internet inherited from Program
    private int data;
    public Internet(String name, double price, int data){
        super(name,price);
        this.data=data;
    }
    public Internet(){
        super();
    }
    public void setData(int data){
        this.data=data;
    }
    public double getDiscount(){
        return 0.3;
    }
    public int getData() {
        return data;
    }
    public String getName(){
    	return name;
    }
    public String toString(){
        return "Contract Type: Internet Contract ||"+ super.toString() + " || Free data: " + getData()+" GB";
    }
}