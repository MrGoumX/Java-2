public class CostCount {
    //Class CostCount, This class calculates the cost of an active program for a month, returns the free services left after a month of use of a contract and returns the discount by percentage
    private double price,balance;
    public String finRemain(ActiveContracts active) {
        Statistics stat = active.getStats();
        Program program = active.getProgram();
        String total="";
        if (program instanceof Contract) {
            Contract contr = (Contract) program;
            if (contr.getMins()*60 > stat.getTotsecs()) {
                total += "The remaining free time remaining is minutes: "+(((contr.getMins()*60)-(stat.getTotsecs()))/60)+" and seconds: "+(((contr.getMins()*60)-(stat.getTotsecs()))%60) + "\n";
            }
            else {
                total += "No free minutes left" + "\n";
            }
            if (contr.getSms() > stat.getTotsms()) {
                total += "The remaining  free sms is " + (contr.getSms() - stat.getTotsms());
            }
            else {
                total += "No free sms left";
            }
        }
        else if (program instanceof CardContract) {
            CardContract contr = (CardContract) program;
            if (contr.getMins()*60 > stat.getTotsecs()) {
                total += "The remaining free time remaining is  minutes: "+(((contr.getMins()*60)-(stat.getTotsecs()))/60)+" and seconds:"+(((contr.getMins()*60)-(stat.getTotsecs()))%60) +"\n";
            }
            else {
                total += "No free minutes left" + "\n";
            }
            if (contr.getSms() > stat.getTotsms()) {
                total += "The remaining  free sms is " + (contr.getSms() - stat.getTotsms()) + "\n";
            }
            else {
                total += "No free sms left" + "\n";
            }
            total += "The remaining balance is" + stat.getTotbalance();
        }
        else if (program instanceof Internet) {
            Internet contr = (Internet) program;
            if (contr.getData() > stat.getTotdata()/1024) {
                total += "The remaining  free data is "+(((contr.getData()*1024)-stat.getTotdata())/1024)+" GB  and "+ (((contr.getData()*1024)-stat.getTotdata())%1024)+"MB";
            }
            else {
                total += "No free data left";
            }
        }
        return total;
    }
    public double findCost(ActiveContracts active) {
        Statistics stat = active.getStats();
        Program program = active.getProgram();
        price = program.getPrice();

        if (program instanceof Contract) {
            Contract contr = (Contract) program;
            if (contr.getMins()*60 < stat.getTotsecs()) {
                price += ((stat.getTotsecs() - contr.getMins()*60) * .0055);
            }
            if (contr.getSms() < stat.getTotsms()) {
                price += (stat.getTotsms() - contr.getSms()) * .15;
            }
            price -= price * ((Contract) program).getDiscount();

        }
        else if (program instanceof CardContract) {
            CardContract contr = (CardContract) program;
            balance = stat.getTotbalance() + contr.getBalance();
            int smsUsedAfterQuote = Math.max(stat.getTotsms() - contr.getSms(), 0);
            int minsUsedAfterQuote = Math.max(stat.getTotsecs() - (contr.getMins()*60), 0);
            balance -= smsUsedAfterQuote * .15;
            balance -= minsUsedAfterQuote * .0055;
            price += Math.max(-balance, 0);
            price -= price * ((CardContract) program).getDiscount();
        }
        else if (program instanceof Internet) {
            Internet contr = (Internet) program;
            if (contr.getData()*1024 < stat.getTotdata()) {
                price += (stat.getTotdata() - contr.getData()*1024) * .05;
            }
            price -= price * ((Internet) program).getDiscount();
        }
        return price;
    }
    public String setDiscount(ActiveContracts active) {
        Statistics stat = active.getStats();
        Program program = active.getProgram();
        if (program instanceof Contract) {
            return "The Discount you get for a normal Contract is: " + (((Contract) program).getDiscount()) * 100 + "%";
        }
        else if (program instanceof CardContract) {
            return "The Discount you get for a Card Contract is: " + (((CardContract) program).getDiscount()) * 100 + "%";
        }
        else if (program instanceof Internet) {
            return "The Discount you get for an Internet Contract is: " + (((Internet) program).getDiscount()) * 100 + "%";
        }
        return null;
    }

    public String toString() {
        return ("The monthly cost of this contract is :"+price);
    }
    public double getPrice(){
        return price;
    }
    public double getBalance(){
        return balance;
    }
    public String getBalance(double balance) {
        String bal;
        if(balance>0){
            bal="The remaining balance is: "+balance;
        }
        else {
            bal="There is not any balance left";
        }
        return bal;
    }

}