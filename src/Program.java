public class Program{
    //Basic Program class and constructor
    protected String name;
    protected double price;
    public Program(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Program(){
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public String getName(){
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String toString(){
        return "  Name: " + getName() + "|| Price: " + getPrice() + " Euro";
    }
    public String getContents(){
        return "  Name: " + getName() + "|| Price: " + getPrice() + " Euro";
    }
}
