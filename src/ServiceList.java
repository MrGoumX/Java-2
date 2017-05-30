import java.io.*;
import java.util.*;
public class ServiceList {
    //Class ServiceList, This class adds services to the list
    private ArrayList <Program> ServiceList = new ArrayList<Program>();
    public void loadFile(File data) {
        File f = null;
        BufferedReader reader = null;
        Program service = null;
        String line;
        try {
            f = data;
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }
        try {
            while((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("SERVICE_LIST")) {
                    line = reader.readLine();
                    if (line.equals("{")) {
                        line = reader.readLine().trim();
                        while(!line.equals("}")){
                            if (line.equalsIgnoreCase("SERVICE")) {
                                line = reader.readLine().trim();
                                if (line.equals("{")) {
                                    line = reader.readLine().trim();
                                    Map<String, String> values = new Hashtable<>();
                                    while (!line.equals("}")) {
                                        String key = "", value = "";
                                        if (line != null){
                                            key = line.indexOf(' ') != -1 ? line.substring(0, line.indexOf(' ')) : line;
                                            value = line.substring(line.indexOf(' ') + 1);
                                        }
                                        if(value.equals(key)) value="";
                                        values.put(key, value);
                                        line = reader.readLine().trim();
                                    }
                                    if (values.containsKey("TYPE")) {
                                        if(values.get("TYPE").matches("[A-Za-z ]+")) {
                                            if (values.get("TYPE").equalsIgnoreCase("Contract")) {
                                                service = new Contract();
                                            } else if (values.get("TYPE").equalsIgnoreCase("CardContract")) {
                                                service = new CardContract();
                                            } else if (values.get("TYPE").equalsIgnoreCase("Internet")) {
                                                service = new Internet();
                                            }
                                        }
                                        else{
                                            System.err.println("Type cannot contain numbers or special characters!");
                                            line=reader.readLine().trim();
                                            continue;
                                        }
                                    }
                                    if (service == null) {
                                        System.err.println("No type specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    if((values.containsKey("SERVICE_NAME") && !values.get("SERVICE_NAME").matches("[A-Za-z_0-9]+"))){
                                        System.err.println("Service name cannot contain numbers or special characters or is not specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("SERVICE_NAME")) {
                                        service.setName(values.get("SERVICE_NAME"));
                                    }
                                    if(values.containsKey("MONTHLY_PRICE") && values.get("MONTHLY_PRICE").isEmpty()){
                                        System.err.println("Price is not specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("MONTHLY_PRICE") && !(values.get("MONTHLY_PRICE").matches("[\\d.]+"))){
                                        System.err.println("Price cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else{
                                        service.setPrice(Double.parseDouble(values.get("MONTHLY_PRICE")));
                                    }
                                    if(values.containsKey("FREE_MOBILE_MINUTES") && values.get("FREE_MOBILE_MINUTES").isEmpty()){
                                        System.err.println("No free mobile minutes specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("FREE_MOBILE_MINUTES") && !(values.get("FREE_MOBILE_MINUTES").matches("[\\d]+"))){
                                        System.err.println("Free mobile minutes cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if ((values.containsKey("FREE_MOBILE_MINUTES")) && service instanceof Contract) {
                                        ((Contract) service).setMinutes(Integer.parseInt(values.get("FREE_MOBILE_MINUTES")));
                                    }
                                    else if ((values.containsKey("FREE_MOBILE_MINUTES")) && service instanceof CardContract) {
                                        ((CardContract) service).setMinutes(Integer.parseInt(values.get("FREE_MOBILE_MINUTES")));
                                    }
                                    if(values.containsKey("FREE_SMS") && values.get("FREE_SMS").isEmpty()){
                                        System.err.println("No free sms specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("FREE_SMS") && !(values.get("FREE_SMS").matches("[\\d]+"))){
                                        System.err.println("Free sms cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if ((values.containsKey("FREE_SMS")) && service instanceof Contract) {
                                        ((Contract) service).setSms(Integer.parseInt(values.get("FREE_SMS")));
                                    }
                                    else if ((values.containsKey("FREE_SMS")) && service instanceof CardContract) {
                                        ((CardContract) service).setSms(Integer.parseInt(values.get("FREE_SMS")));
                                    }
                                    if(values.containsKey("BALANCE") && values.get("BALANCE").isEmpty()){
                                        System.err.println("No balance specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("BALANCE") && !(values.get("BALANCE").matches("[\\d.]+"))){
                                        System.err.println("Balance cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if (values.containsKey("BALANCE") && service instanceof CardContract) {
                                        ((CardContract) service).setBalance(Double.parseDouble(values.get("BALANCE")));
                                    }
                                    if(values.containsKey("FREE_GB") && values.get("FREE_GB").isEmpty()){
                                        System.err.println("No free data specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("FREE_GB") && !(values.get("FREE_GB").matches("[\\d]+"))){
                                        System.err.println("Free GBs cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if (values.containsKey("FREE_GB") && service instanceof Internet) {
                                        ((Internet) service).setData(Integer.parseInt(values.get("FREE_GB")));
                                    }
                                    ServiceList.add(service);
                                    line=reader.readLine().trim();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }
    public Program get(int i){
        return ServiceList.get(i);
    }
    public int size(){
        return ServiceList.size();
    }

    public Program byName(String program_name) {
        for (Program program : ServiceList) {
            if (program.getName().equals(program_name))
                return program;
        }
        return null;
    }
    public void removeAll(){
        ServiceList.clear();
    }
    public String stringByName(String program_name) {
        String name="";
        for (Program program : ServiceList) {
            if (program.getName().equals(program_name))
                name=program.getName();
        }
        return name;
    }
}