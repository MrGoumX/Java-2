public class Statistics  {
    //Class Statistics, Gets the statistics of an active contract accordingly to the type of contract
    private ActiveContracts active;
    private int totsms;
    private int totsecs;
    private int totdata;
    private double totbalance;
    public Statistics(ActiveContracts active) {
    	this.active = active;
    }
    public int getTotdata() {
        return totdata;
    }
    public int getTotsecs() {
        return totsecs;
    }
    public int getTotsms() {
        return totsms;
    }
    public void setsecs(int secs)
    {
    	totsecs=secs;
    }
    public void setSms(int sms){
        totsms=sms;
    }
    public void setData(int data){
        totdata=data;
    }
    public double getTotbalance(){
        return totbalance;
    }
    public void setBalance(double balance){
    	totbalance=balance;
    }
    public String toString(){
    	String fin = "Code ID: " + active.getCode();
    	if(active.getProgram() instanceof Contract){
    		fin = fin +" || Total Used Seconds: " + totsecs + " || Total Used Sms: " + totsms;
    	}
    	else if(active.getProgram() instanceof CardContract){
    		fin = fin +" || Total Used Seconds: " + totsecs + " || Total Used Sms: " + totsms + " || Balance before calculating cost: " + (((CardContract) active.getProgram()).getBalance()+totbalance);
    	}
    	else if(active.getProgram() instanceof Internet){
    		fin = fin +" || Total Used Data: " + totdata;
    	}
    	return fin.trim();
    }
}

