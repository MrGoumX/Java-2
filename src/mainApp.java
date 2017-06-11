//Main Class and Program
//CHRISTOS GKOUMAS,EKSAMINO 2, 3160026
//ANTONIS KANELLOPOULOS, EKSAMINO 2, 3160050
//GEORGIA GRIGORIADOU, EKSAMINO 2, 3160029
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.text.*;
import java.util.*;
public class mainApp extends JFrame implements ActionListener,MouseListener{
    private ServiceList services;
    private ActiveContractsList list;
    private JToolBar toolbar;
    private JTextField priceField;
    private JTabbedPane tabs;
    private JPanel servPan,btnPan,activePan,pricePan;
    private JScrollPane servScroll,activeScroll;
    private GridBagLayout gbl_btnPan;
    private JButton con,cardcon,intern,addCon,updStats,calcCost,openServ,openAct,openServAct,viewActCon,showStats,save,saveExit,remFree,getDisc;
    private GridBagConstraints gbc,gbc_1,gbc_2;
    private FileFilter filter;
    private JFileChooser chooser;
    private File F,F2;
    private DefaultListModel conModel,ccModel,intModel,allModels,actModels;
    private JList listall,finalList;
    private ActiveContracts active;
    private Statistics stat;
    private CostCount cost;
    private Date sdate = new Date();
    public static void main(String[] args) {
        new mainApp();
    }
    public mainApp() {
        initialize();
    }
    private void initialize() {
        active = null;
        stat = null;
        cost = new CostCount();
        services = new ServiceList();
        toolbar = new JToolBar();
        tabs = new JTabbedPane(JTabbedPane.TOP);
        servPan = new JPanel();
        activePan = new JPanel();
        btnPan = new JPanel();
        pricePan = new JPanel();
        gbl_btnPan = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc_1 = new GridBagConstraints();
        gbc_2 = new GridBagConstraints();
        priceField = new JTextField();
        conModel = new DefaultListModel();
        ccModel = new DefaultListModel();
        intModel = new DefaultListModel();
        allModels = new DefaultListModel();
        listall = new JList();
        finalList = new JList();
        actModels = new DefaultListModel();
        servScroll = new JScrollPane(listall);
        activeScroll = new JScrollPane(finalList);
        filter = new FileNameExtensionFilter("Text Files","txt");
        F = new File("");
        F2 = new File("");
        chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        con = new JButton("Contract");
        cardcon = new JButton("CardContract");
        intern = new JButton("Internet");
        addCon = new JButton("Add Contract");
        updStats = new JButton("Update Statistics");
        calcCost = new JButton("Calculate Cost");
        openServ = new JButton(("Open Services"));
        openAct = new JButton("Open Active Contracts");
        openServAct = new JButton("Open Services & Active Contracts");
        viewActCon = new JButton("View active contracts of specific type");
        showStats = new JButton("Show Statistics");
        remFree = new JButton("Remaining free services");
        getDisc = new JButton("Get Discount");
        save = new JButton("Save");
        saveExit = new JButton("Save & Exit");
        con.addActionListener(this);
        cardcon.addActionListener(this);
        intern.addActionListener(this);
        addCon.addActionListener(this);
        updStats.addActionListener(this);
        calcCost.addActionListener(this);
        openServ.addActionListener(this);
        openAct.addActionListener(this);
        openServAct.addActionListener(this);
        viewActCon.addActionListener(this);
        showStats.addActionListener(this);
        remFree.addActionListener(this);
        getDisc.addActionListener(this);
        save.addActionListener(this);
        saveExit.addActionListener(this);
        listall.addMouseListener(this);
        finalList.addMouseListener(this);
        priceField.setEditable(false);
        tabs.addTab("Services", null, servPan, null);
        toolbar.add(openServ);
        toolbar.add(openAct);
        openAct.setEnabled(false);
        toolbar.add(openServAct);
        toolbar.add(viewActCon);
        toolbar.add(showStats);
        toolbar.add(remFree);
        toolbar.add(getDisc);
        save.setEnabled(false);
        saveExit.setEnabled(false);
        toolbar.add(save);
        toolbar.add(saveExit);
	    toolbar.setFloatable(false);
        servPan.setLayout(new BorderLayout(0, 0));
        servPan.add(servScroll);
        servPan.add(btnPan, BorderLayout.WEST);
        gbl_btnPan.columnWidths = new int[]{89, 0};
        gbl_btnPan.rowHeights = new int[]{23, 70, 23, 0};
        gbl_btnPan.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_btnPan.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        btnPan.setLayout(gbl_btnPan);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnPan.add(con, gbc);
        gbc_1.fill = GridBagConstraints.VERTICAL;
        gbc_1.insets = new Insets(0, 0, 5, 0);
        gbc_1.gridx = 0;
        gbc_1.gridy = 1;
        btnPan.add(cardcon, gbc_1);
        gbc_2.fill = GridBagConstraints.BOTH;
        gbc_2.gridx = 0;
        gbc_2.gridy = 2;
        btnPan.add(intern, gbc_2);
        tabs.addTab("Active Contracts", null, activePan, null);
        tabs.setEnabledAt(1,false);
        activePan.setLayout(new BorderLayout(0, 0));
        activePan.add(addCon, BorderLayout.WEST);
        activePan.add(activeScroll, BorderLayout.CENTER);
        activePan.add(updStats, BorderLayout.EAST);
        activePan.add(pricePan, BorderLayout.SOUTH);
        pricePan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pricePan.add(calcCost);
        pricePan.add(priceField);
        priceField.setColumns(10);
        list = new ActiveContractsList(services);
        finalList.setModel(actModels);
        listall.setModel(allModels);
        servScroll.setViewportView(null);
        setTitle("GUI by p3160026-p3160029-p3160050");
        setBounds(100, 100, 1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setVisible(true);
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(tabs);
    }
    public void setMinsAndSms(){
        while (true) {
            String secs = (String)JOptionPane.showInputDialog(null,"Please give the total seconds talked this month");
            if (secs.matches("[\\d]+") && Integer.parseInt(secs)>=0) {
                stat.setsecs(Integer.parseInt(secs));
                break;
            } else {
                JOptionPane.showMessageDialog(null,"The number must be positive. Try again","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
        while (true) {
            String sms = (String)JOptionPane.showInputDialog(null,"Please give the total sms talked this month");
            if (sms.matches("[\\d]+") && Integer.parseInt(sms)>=0) {
                stat.setSms(Integer.parseInt(sms));
                break;
            } else {
                JOptionPane.showMessageDialog(null,"The number must be positive. Try again","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public void loadModels(){
        for(int i = 0;i<services.size();i++){
            if(services.get(i) instanceof Contract){
                conModel.addElement(services.get(i).getContents());
            }
            else if(services.get(i) instanceof CardContract){
                ccModel.addElement(services.get(i).getContents());
            }
            else if(services.get(i) instanceof Internet){
                intModel.addElement(services.get(i).getContents());
            }
            allModels.addElement(services.get(i).getContents());
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openServ){
            conModel.removeAllElements();;
            ccModel.removeAllElements();
            intModel.removeAllElements();
            allModels.removeAllElements();
            actModels.removeAllElements();
            list.removeAll();
            if(services!=null){
                services.removeAll();
            }
            JOptionPane.showMessageDialog(null,"Please choose services file");
            int Checker;
            chooser.setCurrentDirectory(F);
            Checker = chooser.showOpenDialog(null);
            if(Checker == JFileChooser.APPROVE_OPTION) {
                services.loadFile(chooser.getSelectedFile());
                F = new File(chooser.getSelectedFile().toString());
                loadModels();
                if(services.size()!=0) {
                    openAct.setEnabled(true);
                }
            }
        }
        else if(e.getSource() == openAct){
            actModels.removeAllElements();
            if(list!=null){
                list.removeAll();
            }
            JOptionPane.showMessageDialog(null,"Please choose active contracts file");
            int Checker2;
            chooser.setCurrentDirectory(F2);
            Checker2 = chooser.showOpenDialog(null);
            if(Checker2 == JFileChooser.APPROVE_OPTION){
                list.loadFile(chooser.getSelectedFile());
                F2 = new File(chooser.getSelectedFile().toString());
                for(int i=0;i<list.Size();i++){
                    actModels.addElement(list.get(i).getContents());
                }
                tabs.setEnabledAt(1,true);
            }
            if(list.Size()!=0){
                save.setEnabled(true);
                saveExit.setEnabled(true);
            }
        }
        else if(e.getSource() == openServAct){
            conModel.removeAllElements();;
            ccModel.removeAllElements();
            intModel.removeAllElements();
            allModels.removeAllElements();
            actModels.removeAllElements();
            if(services!=null){
                services.removeAll();
            }
            if(list!=null){
                list.removeAll();
            }
            JOptionPane.showMessageDialog(null,"Please choose services file");
            int Checker;
            chooser.setCurrentDirectory(F);
            Checker = chooser.showOpenDialog(null);
            if(Checker == JFileChooser.APPROVE_OPTION){
                services.loadFile(chooser.getSelectedFile());
                F = new File(chooser.getSelectedFile().toString());
            }
            JOptionPane.showMessageDialog(null,"Please choose active contracts file");
            int Checker2;
            chooser.setCurrentDirectory(F2);
            Checker2 = chooser.showOpenDialog(null);
            if(Checker2 == JFileChooser.APPROVE_OPTION){
                list.loadFile(chooser.getSelectedFile());
                F2 = new File(chooser.getSelectedFile().toString());
                loadModels();
                for(int i=0;i<list.Size();i++){
                    actModels.addElement(list.get(i).getContents());
                }
                tabs.setEnabledAt(1,true);
            }
			if(services.size()!=0) {
				openAct.setEnabled(true);
			}
            if(list.Size()!=0){
                save.setEnabled(true);
                saveExit.setEnabled(true);
            }
        }
        else if(e.getSource() == viewActCon) {
            if(list.Size()!=0) {
                String[] names = {"Contract", "Card Contract", "Internet"};
                int con = (int) JOptionPane.showOptionDialog(null, "Choose service type", "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, names, names[0]);
                if (con != -1) {
                    if(list.listContractTypes(names[con]).size()==0) {
                        JOptionPane.showMessageDialog(null,"No active " + names[con] + " where found!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, new JList(list.listContractTypes(names[con]).toArray()));
                    }
                }
            }
        }
        else if(e.getSource() == showStats){
            int i = finalList.getSelectedIndex();
            if(i!=-1) {
                JOptionPane.showMessageDialog(null, list.get(i).getStats(), "Active Contract Statistics", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(e.getSource() == remFree){
            int i = finalList.getSelectedIndex();
            if(i!=-1){
                JOptionPane.showMessageDialog(null, cost.finRemain(list.get(i)),"Remaining free services", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(e.getSource() == getDisc){
            int i = finalList.getSelectedIndex();
            if(i!=-1){
                JOptionPane.showMessageDialog(null,cost.setDiscount(list.findByCode(list.get(i).getCode())),"Discount", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(e.getSource() == save) {
            if (list.Size() != 0){
                list.saveFile(F2.getAbsolutePath());
            }
            else{
                JOptionPane.showMessageDialog(null,"The list is empty, you cannot save","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == saveExit){
            if (list.Size() != 0) {
                list.saveFile(F2.getAbsolutePath());
                System.exit(0);
            }
            else{
                String[] opts = {"Yes","No"};
                int opt = (int)JOptionPane.showOptionDialog(null,"The list is empty, do you want to exit?","Error",JOptionPane.ERROR_MESSAGE,0,null,opts,opts[0]);
                if(opt == 0){
                    System.exit(0);
                }
            }
        }
        else if(e.getSource() == con){
            listall.setModel(conModel);
            servScroll.setViewportView(listall);
        }
        else if(e.getSource() == cardcon){
            listall.setModel(ccModel);
            servScroll.setViewportView(listall);
        }
        else if(e.getSource() == intern){
            listall.setModel(intModel);
            servScroll.setViewportView(listall);
        }
        else if(e.getSource() == addCon) {
            if(list.Size()==0){
                save.setEnabled(true);
                saveExit.setEnabled(true);
            }
            active = new ActiveContracts();
            String name, phone, date;
            String[] programs = new String[services.size()];
            for (int i = 0; i < services.size(); i++) {
                programs[i] = services.get(i).getName();
            }
            int con = (int) JOptionPane.showOptionDialog(null, "Choose contract", "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, programs, programs[0]);
            active.setCon(services.byName(programs[con]));
            while (true) {
                name = JOptionPane.showInputDialog(this, "Please give name");
                if (name.matches("[A-Za-z ]+")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,"A name cannot contain numbers or symbols","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            active.setName(name);
            active.setCode(list.Size() + 1);
            while (true) {
                phone = (String) JOptionPane.showInputDialog(null, "Give your 10 digit phone");
                if (phone.matches("\\d{10}+")) {
                    active.setPhone(phone);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,"Not a phone number. Try again","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            while (true) {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                boolean flag = false;
                date = (String) JOptionPane.showInputDialog(null, "Give current Date",df.format(sdate));
                try {
                    df.setLenient(false);
                    df.parse(date);
                    active.setDate(date);
                    flag = true;
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Wrong date or format. Try again","Error",JOptionPane.ERROR_MESSAGE);
                    flag = false;
                }
                if (flag) {
                    break;
                }
            }
            String[] payments = {"Cash", "Credit Card", "E-bank"};
            int payway = (int) JOptionPane.showOptionDialog(null, "Please choose way of payment", "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, payments, payments[0]);
            active.setPayway(payments[payway]);
            String[] yn = {"Yes add contract", "No discard contract"};
            int yno = (int)JOptionPane.showOptionDialog(null,active.toString(),"Confirmation of active contract",JOptionPane.WARNING_MESSAGE,0,null,yn,yn[0]);
            if(yno == 0){
                list.addActiveContract(active);
                actModels.addElement(active.getContents());
            }
            else{
                active=null;
            }
        }
        else if(e.getSource() == updStats){
            int i = finalList.getSelectedIndex();
            if(i!=-1){
                active = list.findByCode(list.get(i).getCode());
                stat = active.getStats();
                if(active.getProgram() instanceof Contract){
                    setMinsAndSms();
                }
                else if(active.getProgram() instanceof CardContract){
                    setMinsAndSms();
                    while (true) {
                        String balance = (String)JOptionPane.showInputDialog(null,"Please give the extra balance added this month");
                        if (balance.matches("[\\d.]+") && Double.parseDouble(balance)>=0) {
                            stat.setBalance(Double.parseDouble(balance));
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"The number must be positive. Try again","Error",JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
                else if(active.getProgram() instanceof Internet){
                    while (true) {
                        String data = (String)JOptionPane.showInputDialog(null,"Please give the total megabytes(MB) used this month");
                        if (data.matches("[\\d]+") && Integer.parseInt(data)>=0) {
                            stat.setData(Integer.parseInt(data));
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"The number must be positive. Try again","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
        else if(e.getSource() == calcCost){
            int i = finalList.getSelectedIndex();
            if(i!=-1){
                priceField.setText(cost.findCost(list.findByCode(list.get(i).getCode())) + " Euros");
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            if(e.getSource() == listall){
                Object i = listall.getSelectedValue();
                if(i!=null) {
                    for (int j = 0; j < services.size(); j++) {
                        if (i.equals(services.get(j).getContents())) {
                            JOptionPane.showMessageDialog(null, services.get(j).toString(), "Extensive Program Details", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
            else if(e.getSource() == finalList){
                Object i = finalList.getSelectedValue();
                for (int j = 0; j < list.Size(); j++) {
                    if (i.equals(list.get(j).getContents())) {
                        JOptionPane.showMessageDialog(null, list.get(j).toString(), "Extensive Active Contract Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    final static String DATE_FORMAT = "dd/MM/yyyy";
}
