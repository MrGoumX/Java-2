import java.io.*;
import java.text.*;
import java.util.*;
public class ActiveContractsList {
    //Class ActiveContractsList, This class adds an active contract to the list, prints all of its objects, and other various functions for the functionality of the program
    private ArrayList<ActiveContracts> ActiveContractsList = new ArrayList<ActiveContracts>();
    private ServiceList service;
    public ActiveContractsList(ServiceList services) {
        service = services;
    }

    public void loadFile(File data) {
        File f = null;
        BufferedReader reader = null;
        ActiveContracts active = null;
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
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("CONTRACT_LIST")) {
                    line = reader.readLine();
                    if (line.equals("{")) {
                        line = reader.readLine().trim();
                        while (!line.equals("}")) {
                            if (line.equalsIgnoreCase("CONTRACT")) {
                                line = reader.readLine().trim();
                                if (line.equals("{")) {
                                    line = reader.readLine().trim();
                                    Map<String, String> values = new Hashtable<>();
                                    Map<String, String> usageMap = new Hashtable<>();
                                    while (!line.equals("}")) {
                                        String key="",value="";
                                        if(line!=null) {
                                            key = line.indexOf(' ') != -1 ? line.substring(0, line.indexOf(' ')) : line;
                                            value = line.substring(line.indexOf(' ') + 1);
                                        }
                                        if (value.equals(key)) value = "";
                                        if (key.equalsIgnoreCase("MONTHLY_USAGE")) {
                                            line = reader.readLine().trim();
                                            if (line.equals("{")) {
                                                while (!(line = reader.readLine().trim()).equals("}")) {
                                                    if(line!=null) {
                                                        key = line.indexOf(' ') != -1 ? line.substring(0, line.indexOf(' ')) : line;
                                                        value = line.substring(line.indexOf(' ') + 1);
                                                    }
                                                    if (value.equals(key)) value = "";
                                                    usageMap.put(key, value);
                                                }
                                            }
                                        } else {
                                            values.put(key, value);
                                        }
                                        line = reader.readLine().trim();
                                    }
                                    if(values.containsKey("PROGRAM_TYPE")){
                                        if(values.get("PROGRAM_TYPE").equalsIgnoreCase("Contract") || values.get("PROGRAM_TYPE").equalsIgnoreCase("CardContract") ||values.get("PROGRAM_TYPE").equalsIgnoreCase("Internet")){
                                            active=new ActiveContracts();
                                        }
                                        else{
                                            System.err.println("No type specified!");
                                            line = reader.readLine().trim();
                                            continue;
                                        }
                                    }
                                    if(active==null){
                                        System.err.println("No contract specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    if (values.containsKey("PROGRAM_NAME") && values.get("PROGRAM_NAME").isEmpty()) {
                                        System.err.println("No name specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    } else if (values.containsKey("PROGRAM_NAME")) {
                                        if(values.get("PROGRAM_NAME").equalsIgnoreCase(service.stringByName(values.get("PROGRAM_NAME")))) {
                                            active.setCon(service.byName(values.get("PROGRAM_NAME")));
                                        }
                                        else{
                                            System.err.println("The service does not exist");
                                            line = reader.readLine().trim();
                                            continue;
                                        }
                                    }
                                    if (values.containsKey("ID")) {
                                        if(values.get("ID").matches("\\d+")) {
                                            if (values.get("ID").isEmpty()) {
                                                System.err.println("No ID specified!");
                                                line = reader.readLine().trim();
                                                continue;
                                            } else if (!ActiveContractsList.isEmpty() && (Integer.parseInt(values.get("ID")) <= ActiveContractsList.get(ActiveContractsList.size() - 1).getCode())) {
                                                System.err.println("ID is smaller from the last one in the list!");
                                                line = reader.readLine().trim();
                                                continue;
                                            } else {
                                                active.setCode(Integer.parseInt(values.get("ID")));
                                            }
                                        }
                                        else{
                                            System.err.println("ID cannot contain characters");
                                            line = reader.readLine().trim();
                                            continue;
                                        }
                                    }
                                    if (values.containsKey("OWNER") && values.get("OWNER").isEmpty()) {
                                        System.err.println("No owner specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("OWNER") && !(values.get("OWNER").matches("[A-Za-z ]+"))){
                                        System.err.println("The owner cannot contain special characters or numbers!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else{
                                        active.setName(values.get("OWNER"));
                                    }
                                    if (values.containsKey("PHONE") && values.get("PHONE").isEmpty()) {
                                        System.err.println("No phone specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("PHONE") && !(values.get("PHONE")).matches("\\d{10}+")){
                                        System.err.println("Phone number cannot contain normal or special characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else {
                                        active.setPhone(values.get("PHONE"));
                                    }
                                    if (values.containsKey("PAYMENT") && values.get("PAYMENT").isEmpty()) {
                                        System.err.println("No payment method specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    } else if (values.containsKey("PAYMENT")) {
                                        active.setPayway(values.get("PAYMENT"));
                                    }
                                    if (values.containsKey("ACTIVATION_DATE") && values.get("ACTIVATION_DATE").isEmpty()) {
                                        System.err.println("No activation date specified!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(values.containsKey("ACTIVATION_DATE")){
                                        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                                        try{
                                            df.setLenient(false);
                                            df.parse(values.get("ACTIVATION_DATE"));
                                        }
                                        catch (ParseException e) {
                                            System.err.println("Not valid Date!");
                                            line = reader.readLine().trim();
                                            continue;
                                        }
                                        active.setDate(values.get("ACTIVATION_DATE"));
                                    }
                                    if (usageMap.containsKey("SECONDS") && usageMap.get("SECONDS").isEmpty()) {
                                        System.err.println("No stats for minutes given!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("SECONDS") && !(usageMap.get("SECONDS").matches("[\\d]+"))){
                                        System.err.println("Seconds cannot contain characters!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("SECONDS")){
                                        active.getStats().setsecs(Integer.parseInt(usageMap.get("SECONDS")));
                                    }
                                    if (usageMap.containsKey("SMS") && usageMap.get("SMS").isEmpty()) {
                                        System.err.println("No stats for sms given!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("SMS") && !(usageMap.get("SMS")).matches("[\\d]+")){
                                        System.err.println("SMS cannot contain characters");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("SMS")){
                                        active.getStats().setSms(Integer.parseInt(usageMap.get("SMS")));
                                    }
                                    if (usageMap.containsKey("DATA") && usageMap.get("DATA").isEmpty()) {
                                        System.err.println("No stats for data given!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("DATA") && !(usageMap.get("DATA").matches("[\\d]+"))){
                                        System.err.println("DATA cannot contain characters");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("DATA")){
                                        active.getStats().setData(Integer.parseInt(usageMap.get("DATA")));
                                    }
                                    if (usageMap.containsKey("BALANCE") && usageMap.get("BALANCE").isEmpty()) {
                                        System.err.println("No extra balance given!");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("BALANCE") && !(usageMap.get("BALANCE").matches("[\\d.]+"))){
                                        System.err.println("BALANCE cannot contain characters");
                                        line = reader.readLine().trim();
                                        continue;
                                    }
                                    else if(usageMap.containsKey("BALANCE")){
                                        active.getStats().setBalance(Double.parseDouble(usageMap.get("BALANCE"))-((CardContract)active.getProgram()).getBalance());
                                    }
                                    ActiveContractsList.add(active);
                                    active=null;
                                    line = reader.readLine().trim();
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

    public void saveFile(String path) {
        File f = null;
        BufferedWriter writer = null;
        try {
            f = new File(path);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(f)));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file for writing!");
        }
        try {
            writer.write("CONTRACT_LIST");
            writer.newLine();
            writer.write("{");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ActiveContracts active: ActiveContractsList) {
            try{
                writer.write(" \tCONTRACT");
                writer.newLine();
                writer.write("\t{");
                writer.newLine();
                if (active.getProgram() instanceof Contract) {
                    try {
                        writer.write("\t\tPROGRAM_TYPE " + active.getProgram().getClass().getName());
                        writer.newLine();
                        writer.write("\t\tPROGRAM_NAME " + active.getProgram().getName());
                        writer.newLine();
                        writer.write("\t\tOWNER " + active.getOwner());
                        writer.newLine();
                        writer.write("\t\tID " + active.getCode());
                        writer.newLine();
                        writer.write("\t\tPHONE " + active.getPhone());
                        writer.newLine();
                        writer.write("\t\tACTIVATION_DATE " + active.getDate());
                        writer.newLine();
                        writer.write("\t\tPAYMENT " + active.getPayway());
                        writer.newLine();
                        writer.write("\t\tMONTHLY_USAGE");
                        writer.newLine();
                        writer.write("\t\t{");
                        writer.newLine();
                        writer.write("\t\t\tSECONDS " + active.getStats().getTotsecs());
                        writer.newLine();
                        writer.write("\t\t\tSMS " + active.getStats().getTotsms());
                        writer.newLine();
                        writer.write("\t\t}");
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (active.getProgram() instanceof CardContract) {
                    try {
                        writer.write("\t\tPROGRAM_TYPE " + active.getProgram().getClass().getName());
                        writer.newLine();
                        writer.write("\t\tPROGRAM_NAME " + active.getProgram().getName());
                        writer.newLine();
                        writer.write("\t\tOWNER " + active.getOwner());
                        writer.newLine();
                        writer.write("\t\tID " + active.getCode());
                        writer.newLine();
                        writer.write("\t\tPHONE " + active.getPhone());
                        writer.newLine();
                        writer.write("\t\tACTIVATION_DATE " + active.getDate());
                        writer.newLine();
                        writer.write("\t\tPAYMENT " + active.getPayway());
                        writer.newLine();
                        writer.write("\t\tMONTHLY_USAGE");
                        writer.newLine();
                        writer.write("\t\t{");
                        writer.newLine();
                        writer.write("\t\t\tSECONDS " + active.getStats().getTotsecs());
                        writer.newLine();
                        writer.write("\t\t\tSMS " + active.getStats().getTotsms());
                        writer.newLine();
                        writer.write("\t\t\tBALANCE " + (active.getStats().getTotbalance()+((CardContract) active.getProgram()).getBalance()));
                        writer.newLine();
                        writer.write("\t\t}");
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (active.getProgram() instanceof Internet) {
                    try {
                        writer.write("\t\tPROGRAM_TYPE " + active.getProgram().getClass().getName());
                        writer.newLine();
                        writer.write("\t\tPROGRAM_NAME " + active.getProgram().getName());
                        writer.newLine();
                        writer.write("\t\tOWNER " + active.getOwner());
                        writer.newLine();
                        writer.write("\t\tID " + active.getCode());
                        writer.newLine();
                        writer.write("\t\tPHONE " + active.getPhone());
                        writer.newLine();
                        writer.write("\t\tACTIVATION_DATE " + active.getDate());
                        writer.newLine();
                        writer.write("\t\tPAYMENT " + active.getPayway());
                        writer.newLine();
                        writer.write("\t\tMONTHLY_USAGE");
                        writer.newLine();
                        writer.write("\t\t{");
                        writer.newLine();
                        writer.write("\t\t\tDATA " + active.getStats().getTotdata());
                        writer.newLine();
                        writer.write("\t\t}");
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                writer.write("\t}");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }

    public void addActiveContract(ActiveContracts contract) {
        ActiveContractsList.add(contract);
    }

    public ArrayList<String> listContractTypes(String type) {
        ArrayList<String> cons = new ArrayList<String>();
        for (int i=0;i<ActiveContractsList.size();i++) {
            if ((type.equalsIgnoreCase("internet") && ActiveContractsList.get(i).getProgram() instanceof Internet) ||
                    (type.equalsIgnoreCase("card contract") && ActiveContractsList.get(i).getProgram() instanceof CardContract) ||
                    (type.equalsIgnoreCase("contract") && ActiveContractsList.get(i).getProgram() instanceof Contract)) {
                cons.add(ActiveContractsList.get(i).toString());
            }
        }
        return cons;
    }

    public boolean boolbyCode(int code) {
        boolean test = false;
        for (ActiveContracts actc : ActiveContractsList) {
            if (actc.getCode() == code) {
                test = true;
            }
        }
        return test;
    }

    public String findType(int code) {
        String test = "";
        for (ActiveContracts actc : ActiveContractsList) {
            if (actc.getCode() == code && actc.getProgram() instanceof Internet) {
                test = "Internet";
            } else if (actc.getCode() == code && actc.getProgram() instanceof Contract) {
                test = "Contract";
            } else if (actc.getCode() == code && actc.getProgram() instanceof CardContract) {
                test = "CardContract";
            }
        }
        return test;
    }

    public ActiveContracts findByCode(int code) {
        for (ActiveContracts actc : ActiveContractsList) {
            if (actc.getCode() == code) {
                return actc;
            }
        }
        return null;
    }

    public void listAll() {
        for (ActiveContracts actc : ActiveContractsList) {
            System.out.println(actc);
        }
    }

    public int Size() {
        return ActiveContractsList.size();
    }

    public void printStats() {
        for (ActiveContracts actc : ActiveContractsList) {
            System.out.println(actc.getStats().toString());
        }
    }
    public void removeAll(){
        ActiveContractsList.clear();
    }
    public ActiveContracts get(int i)
    {
        return ActiveContractsList.get(i);
    }
    final static String DATE_FORMAT = "dd/MM/yyyy";
}