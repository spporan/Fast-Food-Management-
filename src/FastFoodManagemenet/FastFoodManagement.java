package FastFoodManagemenet;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author poran
 */
public class FastFoodManagement extends javax.swing.JFrame {

    public double sum = 0.0;
    public double ptotal = 0;
    public double tax = 0;
    public double pamount = 0;
    public double cBalence=0 ;
    public double returnb = 0;
    int c;
    Connection conn = null;
    PreparedStatement stt = null;
    ResultSet rs = null;
    Connection connection;
    PreparedStatement ps;
    
    public FastFoodManagement() {
        initComponents();
       
        

    }
    
    public void datasave(){
         try {
            conn = Mysql_connect.connectDB();
            Statement stt = conn.createStatement();

            PreparedStatement updateemp = conn.prepareStatement("INSERT INTO customerinfo(ordern,recipt) VALUES (?,?)");
            updateemp.setString(1, orderField.getText());

            updateemp.setString(2, txtarea.getText());
            updateemp.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Acount Has Successfully Created!");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry,Duplicate Order Number!");
            System.out.print("Do not connect to DB - Error:" + e);
        }
    }
        
        
    public void showBalence(){
        String sql = "SELECT balence,accountNo FROM customerCaditCard WHERE accountNo =?";
          conn = Mysql_connect.connectDB();
        try {
            stt = conn.prepareStatement(sql);
            stt.setString(1, acFeild.getText());

            rs = stt.executeQuery();

            if(rs.next()){
                 ctxt.setText(rs.getString("balence")+" BDT");
                 plb.setText(rs.getString("balence")+" BDT");
            }
            else{
                //JOptionPane.showMessageDialog(null, "Not Found of Your AC");
                initBalence();
                showBalence();
                
            }
           
            

            
        } catch (SQLException ex) {
            

        }
    }
    public void initBalence() {

        conn = Mysql_connect.connectDB();
        try {
            String sql = "SELECT accountNo FROM customerCaditCard WHERE accountNo =?";

            stt = conn.prepareStatement(sql);
            stt.setString(1, acFeild.getText());

            rs = stt.executeQuery();

            if (rs.next()) {
                
                balenceQuery();
                //ctxt.setText(String.valueOf(cBalence));

            } else {
                if(acFeild.getText().equals("AC")){
                    JOptionPane.showMessageDialog(null, "Faild Payment,Please Try Again!");
                }

                addAC();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FastFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBalence(double addb) {
        conn = Mysql_connect.connectDB();
        String sqlUpdate = "UPDATE customerCaditCard "
                + "SET balence = ? "
                + "WHERE accountNo = ?";
        try {
            //double b = Integer.parseInt(rs.getString("balence"));
            balenceQuery();
            //double addb = Integer.parseInt(addtxt.getText());
            cBalence= cBalence+ addb;

            stt = conn.prepareStatement(sqlUpdate);
            stt.setDouble(1, cBalence);
            stt.setString(2, acFeild.getText());
            stt.executeUpdate();
        } catch (SQLException ex) {

        }
    }
    public void updateBalence(double returnable){
         conn = Mysql_connect.connectDB();
        String sqlUpdate = "UPDATE customerCaditCard "
                + "SET balence = ? "
                + "WHERE accountNo = ?";
        try {
            //double b = Integer.parseInt(rs.getString("balence"));
            //balenceQuery();
            //double addb = Integer.parseInt(addtxt.getText());
            //cBalence= cBalence+ addb;

            stt = conn.prepareStatement(sqlUpdate);
            stt.setDouble(1, returnable);
            stt.setString(2, acFeild.getText());
            stt.executeUpdate();
        } catch (SQLException ex) {

        }
        
    }
    public void balenceQuery(){
         String sql = "SELECT balence,accountNo FROM customerCaditCard WHERE accountNo =?";
          conn = Mysql_connect.connectDB();
        try {
            stt = conn.prepareStatement(sql);
            stt.setString(1, acFeild.getText());

            rs = stt.executeQuery();

            while(rs.next()){
                 cBalence = Integer.parseInt(rs.getString("balence"));
            }
           
            

            
        } catch (SQLException ex) {

        }
    }

    public void addAC() {
        try {
                    conn = Mysql_connect.connectDB();
                    Statement stt = conn.createStatement();

                    PreparedStatement updateemp = conn.prepareStatement("INSERT INTO customerCaditCard(accountNO,balence) VALUES (?,?)");
                    updateemp.setString(1, acFeild.getText());
                    updateemp.setString(2, addtxt.getText());

                    updateemp.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "Acount Has Successfully Created!");
                    conn.close();
                } catch (Exception e) {
                    System.out.print("Do not connect to DB - Error:" + e);
                }

    }

    public boolean payment() {
        if (pamount <= cBalence&&pamount!=0) {
            returnb = cBalence - pamount;
            c = 1;
            updateBalence(returnb);

            JOptionPane.showMessageDialog(null, "Your payment is Successfull");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Sorry,Insufficient Balence,Please deposite Money !");
            return false;
        }
    }

    public void cBalence(double balence) {
        cBalence = cBalence + balence;
    }

    public void sum(double t) {
        sum = sum + t;
    }

    public void sub(double t) {
        sum = sum - t;
    }

    public void tax(double tsum) {
        tax = (tsum * 15) / 100;
    }
    
//    public void print(){
//    try {
//            
//            Document doc=new Document();
//            try {
//                PdfWriter.getInstance(doc, new FileOutputStream("Report.pdf"));
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(FastFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            doc.open();
//            doc.add(new Paragraph(txtarea.getText().toString()));
//            doc.close();
//        } catch (DocumentException ex) {
//            Logger.getLogger(FastFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        orderField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        searchField = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        acFeild = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        showB = new javax.swing.JButton();
        ctxt = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbox3 = new javax.swing.JCheckBox();
        vspinner14 = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbox1 = new javax.swing.JCheckBox();
        vspinner12 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbox2 = new javax.swing.JCheckBox();
        vspinner13 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbox4 = new javax.swing.JCheckBox();
        vspinner15 = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        fbox = new javax.swing.JCheckBox();
        vspinner21 = new javax.swing.JSpinner();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        cbox5 = new javax.swing.JCheckBox();
        vspinner16 = new javax.swing.JSpinner();
        jButton10 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        cbox9 = new javax.swing.JCheckBox();
        vspinner22 = new javax.swing.JSpinner();
        jPanel11 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbox7 = new javax.swing.JCheckBox();
        vspinner18 = new javax.swing.JSpinner();
        jButton8 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        cbox6 = new javax.swing.JCheckBox();
        vspinner17 = new javax.swing.JSpinner();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbox8 = new javax.swing.JCheckBox();
        vspinner19 = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        vbox = new javax.swing.JCheckBox();
        vspinner = new javax.swing.JSpinner();
        jButton15 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        hbox = new javax.swing.JCheckBox();
        vspinner2 = new javax.swing.JSpinner();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        cbox10 = new javax.swing.JCheckBox();
        vspinner1 = new javax.swing.JSpinner();
        jPanel34 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        kbox = new javax.swing.JCheckBox();
        vspinner3 = new javax.swing.JSpinner();
        jPanel35 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        kbox1 = new javax.swing.JCheckBox();
        vspinner4 = new javax.swing.JSpinner();
        jPanel36 = new javax.swing.JPanel();
        jLabel146 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        bbox1 = new javax.swing.JCheckBox();
        vspinner6 = new javax.swing.JSpinner();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        vbox1 = new javax.swing.JCheckBox();
        vspinner5 = new javax.swing.JSpinner();
        jButton22 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        zbox = new javax.swing.JCheckBox();
        vspinner7 = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        cbox11 = new javax.swing.JCheckBox();
        vspinner11 = new javax.swing.JSpinner();
        jButton13 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        sbox1 = new javax.swing.JCheckBox();
        vspinner10 = new javax.swing.JSpinner();
        jPanel18 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        mbox = new javax.swing.JCheckBox();
        vspinner8 = new javax.swing.JSpinner();
        jButton14 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        sbox = new javax.swing.JCheckBox();
        vspinner9 = new javax.swing.JSpinner();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        jPanel25 = new javax.swing.JPanel();
        ttxt = new javax.swing.JTextField();
        ttax = new javax.swing.JTextField();
        ptxt = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        rbtn = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        addtxt = new javax.swing.JTextField();
        plb = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        rbtn1 = new javax.swing.JButton();
        ltlbl = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14), new java.awt.Color(51, 255, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 255));
        jLabel1.setText("Customer Name:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Order No:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 255, 255));
        jLabel3.setText("Date:");

        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });

        searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchField.setText("Enter Order No");
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldMouseClicked(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(0, 204, 204));
        jButton29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(153, 0, 51));
        jButton29.setText("Search");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 255, 255));
        jLabel64.setText("Address");

        acFeild.setText("AC");

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 255, 255));
        jLabel73.setText("Credit Card No");

        showB.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        showB.setForeground(new java.awt.Color(255, 0, 51));
        showB.setText("Current Balence");
        showB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBActionPerformed(evt);
            }
        });

        ctxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ctxt.setForeground(new java.awt.Color(255, 0, 51));

        jLabel60.setForeground(new java.awt.Color(255, 0, 51));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("(Current Amount)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(orderField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addressField)
                    .addComponent(dateField, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acFeild, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel60)
                        .addComponent(ctxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acFeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showB)
                    .addComponent(ctxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton29)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(orderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64)
                                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/fsd.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("HOME", jPanel4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c2.jpg"))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Combo 3");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("1 Spicy Burger");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("1 F/Fries (R)");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("1 Soft Drink");

        jLabel14.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("TK.290");

        cbox3.setText("Order");
        cbox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox3ActionPerformed(evt);
            }
        });

        vspinner14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner14.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner14StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbox3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(vspinner14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbox3)
                    .addComponent(vspinner14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Combo 1");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("2 Pcs Chicken");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("1 Regular Fries");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("1 Soft Drink");

        jLabel23.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("TK.280");

        cbox1.setText("Order");
        cbox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbox1StateChanged(evt);
            }
        });
        cbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox1ActionPerformed(evt);
            }
        });

        vspinner12.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner12.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner12StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbox1))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vspinner12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vspinner12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c1.jpg"))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Combo 2");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("1 Pc Chicken");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("1 Mini Burger");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("1 Soft Drink");

        jLabel19.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("TK.220");

        cbox2.setText("Order");
        cbox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox2ActionPerformed(evt);
            }
        });

        vspinner13.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner13StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(cbox2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(vspinner13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(vspinner13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c4.jpg"))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/c3.jpg"))); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Combo 4");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("1 Beef Burger (Super)");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("1 Soft Drink");

        jLabel27.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 51));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("TK.290");

        cbox4.setText("Order");
        cbox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox4ActionPerformed(evt);
            }
        });

        vspinner15.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner15.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner15StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(cbox4))
                        .addGap(18, 18, 18)
                        .addComponent(vspinner15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbox4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vspinner15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c6.jpg"))); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel46.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Fried Rice MEAL");

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("1 Pc Chicken");

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Freid Rice");

        jLabel49.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 0, 51));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("TK.200");

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        fbox.setText("Order");
        fbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fboxActionPerformed(evt);
            }
        });

        vspinner21.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner21.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner21StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(fbox)
                                .addGap(0, 19, Short.MAX_VALUE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel47)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vspinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(vspinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c8.jpg"))); // NOI18N

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/frc.jpg"))); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Combo 5");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chopped Chicken");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Plain Rice");

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("1 Soft Drink");

        jLabel36.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 0, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("TK.180");

        cbox5.setText("Order");
        cbox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox5ActionPerformed(evt);
            }
        });

        vspinner16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner16.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner16StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(cbox5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addComponent(vspinner16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36))
                    .addComponent(vspinner16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbox5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/c9.jpg"))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/c7.jpg"))); // NOI18N

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel51.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Combo 9");

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Chicken Stir Fry");

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Fried Rice");

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("1 Soft Drink");

        jLabel55.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 0, 51));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("TK.290");

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("1 F/Fries (R)");

        cbox9.setText("Order");
        cbox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox9ActionPerformed(evt);
            }
        });

        vspinner22.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner22.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner22StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(18, 18, 18)
                                .addComponent(vspinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbox9)
                        .addGap(30, 30, 30))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addGap(5, 5, 5)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(vspinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(cbox9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Couple Combo");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("2 Pcs Chicken");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("1 Mini Burger");

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("1 Soft Drink");

        jLabel32.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 51));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("TK.580");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("1 F/Fries (R)");

        cbox7.setText("Order");
        cbox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox7ActionPerformed(evt);
            }
        });

        vspinner18.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner18.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner18StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbox7))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(vspinner18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(5, 5, 5)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vspinner18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbox7)
                        .addContainerGap())))
        );

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/c5.jpg"))); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel41.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Combo 6");

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Chicken Salsa Wrap");

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("1 Mini Burger");

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("1 Soft Drink");

        jLabel45.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 0, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("TK.200");

        cbox6.setText("Order");
        cbox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox6ActionPerformed(evt);
            }
        });

        vspinner17.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner17.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner17StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(vspinner17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cbox6))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(5, 5, 5)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45))
                    .addComponent(vspinner17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbox6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel37.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Combo 8");

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("8 Pcs Chicken");

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("4 Soft Drink");

        jLabel40.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 0, 51));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("TK.1100");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("2 F/Fries (R)");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("4 Bun ");

        cbox8.setText("Order");
        cbox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox8ActionPerformed(evt);
            }
        });

        vspinner19.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner19.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner19StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cbox8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel40))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vspinner19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbox8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40))
                    .addComponent(vspinner19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(230, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel6);

        jTabbedPane1.addTab("Food Manu", jScrollPane1);

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel81.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 0, 0));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("vegetable green salad");

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel85.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 0, 51));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("TK.120");

        vbox.setText("Order");
        vbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vboxActionPerformed(evt);
            }
        });

        vspinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vbox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel81)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vspinner)
                                .addGap(28, 28, 28))
                            .addComponent(jLabel82))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel84)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85)
                            .addComponent(vbox, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(vspinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/hotwings.jpg"))); // NOI18N

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel86.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("hot wings");

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("4 pics ");

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel90.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 0, 51));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("TK.290");

        hbox.setText("Order");
        hbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hboxActionPerformed(evt);
            }
        });

        vspinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner2StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vspinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel90)
                        .addGap(18, 18, 18)
                        .addComponent(hbox, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel87))))
                    .addComponent(vspinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(hbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/grsalad.jpg"))); // NOI18N

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/cstrips.jpg"))); // NOI18N

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/km1.jpg"))); // NOI18N

        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel91.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("crispy strips");

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("4 pics");

        jLabel95.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 0, 51));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("TK.240");

        cbox10.setText("Order");
        cbox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox10ActionPerformed(evt);
            }
        });

        vspinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbox10))))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(vspinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vspinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(cbox10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel136.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 0, 0));
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel136.setText("KIDS 1");

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("1 Pc Chicken");

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel138.setText("1 F/Fries");

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText("1 Soft Drink");

        jLabel140.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 0, 51));
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("TK.190");

        kbox.setText("Order");
        kbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kboxActionPerformed(evt);
            }
        });

        vspinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner3StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel137)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel138)
                        .addGap(18, 18, 18)
                        .addComponent(vspinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel140)
                                .addGap(18, 18, 18)
                                .addComponent(kbox)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel137)
                .addGap(6, 6, 6)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138)
                    .addComponent(vspinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel141.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 0, 0));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("Kids 2");

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel142.setText("4 Pcs Nuggets");

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel143.setText("1 F/Fries (R)");

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("1 Soft Drink");

        jLabel145.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(255, 0, 51));
        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel145.setText("TK.220");

        kbox1.setText("Order");
        kbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kbox1ActionPerformed(evt);
            }
        });

        vspinner4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner4StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142)
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel144)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vspinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel141)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(jLabel145)
                                .addGap(27, 27, 27)
                                .addComponent(kbox1)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel142)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel143)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vspinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(kbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel146.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(255, 0, 0));
        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel146.setText("beef burger");

        jLabel150.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(255, 0, 51));
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setText("TK.195");

        bbox1.setText("Order");
        bbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbox1ActionPerformed(evt);
            }
        });

        vspinner6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner6StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel150)
                        .addGap(18, 18, 18)
                        .addComponent(vspinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(bbox1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vspinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/km2.jpg"))); // NOI18N

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/vegb.jpg"))); // NOI18N

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beb.jpg"))); // NOI18N

        jPanel37.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel151.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(255, 0, 0));
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel151.setText("vegetable burger");

        jLabel155.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 0, 51));
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel155.setText("TK.195");

        vbox1.setText("Order");
        vbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vbox1ActionPerformed(evt);
            }
        });

        vspinner5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner5StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel155)
                        .addGap(18, 18, 18)
                        .addComponent(vbox1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(vspinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vspinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/zingpc.jpg"))); // NOI18N

        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel156.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 0, 0));
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel156.setText("zing pop chicken");

        jLabel160.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 0, 51));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("TK.145");

        zbox.setText("Order");
        zbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zboxActionPerformed(evt);
            }
        });

        vspinner7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner7StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel156)
                            .addComponent(zbox))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel160)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(vspinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel160))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(vspinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(zbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/salsa.jpg"))); // NOI18N

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel57.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("chicken salsa wrap");

        jLabel61.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 0, 51));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("TK.165");

        cbox11.setText("Order");
        cbox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox11ActionPerformed(evt);
            }
        });

        vspinner11.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner11.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner11StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(cbox11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(18, 18, 18)
                                .addComponent(vspinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(vspinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(cbox11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/sbeb.jpg"))); // NOI18N

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel71.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("super beef burger");

        jLabel75.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 0, 51));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("TK.285");

        sbox1.setText("Order");
        sbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbox1ActionPerformed(evt);
            }
        });

        vspinner10.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner10StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(18, 18, 18)
                        .addComponent(vspinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(sbox1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(vspinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(sbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel62.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 0, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("mini burger");

        jLabel65.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 0, 51));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("TK.280");

        mbox.setText("Order");
        mbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mboxActionPerformed(evt);
            }
        });

        vspinner8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner8StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(mbox))
                        .addGap(18, 18, 18)
                        .addComponent(vspinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(mbox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(vspinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/mb.jpg"))); // NOI18N

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FastFoodManagemenet/bucket.jpg"))); // NOI18N

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel66.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 0, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("spicy chicken");

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("21 PCS BUCKET");

        jLabel70.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 0, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("TK.1700");

        sbox.setText("Order");
        sbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sboxActionPerformed(evt);
            }
        });

        vspinner9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        vspinner9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vspinner9StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addGap(18, 18, 18)
                                .addComponent(vspinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sbox))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(vspinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(sbox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(115, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel22);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("More Items", jPanel2);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255), 2), "Recipts"));

        txtarea.setEditable(false);
        txtarea.setColumns(20);
        txtarea.setRows(5);
        txtarea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(txtarea);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(0, 153, 153));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2), "Calculation Method", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 255, 255))); // NOI18N

        ttxt.setEditable(false);
        ttxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ttxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        ttax.setEditable(false);
        ttax.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ttax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttax.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        ptxt.setEditable(false);
        ptxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ptxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        jLabel58.setForeground(new java.awt.Color(255, 0, 102));
        jLabel58.setText("(without Tax)");

        jLabel59.setForeground(new java.awt.Color(255, 0, 51));
        jLabel59.setText("(Included Tax)");

        jLabel67.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 0, 51));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Total");
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 0, 51));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Tax");
        jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(204, 0, 51));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("Total Payable");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ptxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel59))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(ttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel58))
                            .addComponent(ttax, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58))
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ttax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(0, 153, 153));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255), 2), "Payment System", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N

        jButton27.setBackground(new java.awt.Color(102, 204, 255));
        jButton27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 0, 51));
        jButton27.setText("Payment");
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        rbtn.setBackground(new java.awt.Color(204, 204, 255));
        rbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtn.setForeground(new java.awt.Color(255, 0, 51));
        rbtn.setText("Reset");
        rbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 0, 51));
        jButton24.setText("Deposite AC");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        addtxt.setText("0");

        jLabel74.setText("(Your Currrent Balence)");

        rbtn1.setBackground(new java.awt.Color(204, 204, 255));
        rbtn1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtn1.setForeground(new java.awt.Color(255, 0, 51));
        rbtn1.setText("Print");
        rbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(rbtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn)
                            .addComponent(jButton24))))
                .addGap(95, 95, 95))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(plb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton27)
                            .addComponent(jButton24)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(addtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtn)
                    .addComponent(rbtn1))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        ltlbl.setForeground(new java.awt.Color(255, 0, 51));

        jLabel63.setForeground(new java.awt.Color(255, 0, 51));
        jLabel63.setText("Live Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(jLabel63)
                                .addGap(18, 18, 18)
                                .addComponent(ltlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ltlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox1ActionPerformed
        if ((Integer) vspinner12.getValue() == 0 && cbox1.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please,Select Quantity!");
        } else {
            double qnty = (Integer) vspinner12.getValue();
            double ct1 = 280.00 * qnty;
            if (cbox1.isSelected()) {
                sum(ct1);
                tax(sum);
                pamount = sum + tax;
                ttxt.setText(String.valueOf(sum));
                vspinner12.setEnabled(false);
                ptxt.setText(String.valueOf(pamount) + "   BDT");
                ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");

            } else {
                sub(ct1);
                tax(sum);
                pamount = sum + tax;
                ptxt.setText(String.valueOf(pamount) + "   BDT");
                ttxt.setText(String.valueOf(sum));
                ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
                vspinner12.setEnabled(true);

            }
        }

    }//GEN-LAST:event_cbox1ActionPerformed

    private void cbox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox3ActionPerformed
        double qnty = (Integer) vspinner14.getValue();
        double ct2 = 290.00 * qnty;
        if (cbox3.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner14.setEnabled(false);

        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner14.setEnabled(true);

        }
    }//GEN-LAST:event_cbox3ActionPerformed

    private void cbox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox2ActionPerformed
        double qnty = (Integer) vspinner13.getValue();
        double ct2 = 220.00 * qnty;
        if (cbox2.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;

            ptxt.setText(String.valueOf(pamount) + "   BDT");
            vspinner13.setEnabled(false);
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner13.setEnabled(true);
        }
    }//GEN-LAST:event_cbox2ActionPerformed

    private void cbox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox4ActionPerformed
        double qnty = (Integer) vspinner15.getValue();
        double ct2 = 290.00 * qnty;
        if (cbox4.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");

            vspinner15.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner15.setEnabled(true);
        }
    }//GEN-LAST:event_cbox4ActionPerformed

    private void cbox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox5ActionPerformed
        double qnty = (Integer) vspinner16.getValue();
        double ct2 = 180.00 * qnty;
        if (cbox5.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner16.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner16.setEnabled(true);
        }
    }//GEN-LAST:event_cbox5ActionPerformed

    private void cbox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox6ActionPerformed
        double qnty = (Integer) vspinner17.getValue();
        double ct2 = 200.00 * qnty;
        if (cbox6.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner17.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner17.setEnabled(true);
        }
    }//GEN-LAST:event_cbox6ActionPerformed

    private void cbox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox7ActionPerformed
        double qnty = (Integer) vspinner18.getValue();
        double ct2 = 580.00 * qnty;
        if (cbox7.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner18.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner18.setEnabled(true);
        }
    }//GEN-LAST:event_cbox7ActionPerformed

    private void cbox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox8ActionPerformed
        double qnty = (Integer) vspinner19.getValue();
        double ct2 = 1100.00 * qnty;
        if (cbox8.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner19.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner19.setEnabled(true);
        }
    }//GEN-LAST:event_cbox8ActionPerformed

    private void cbox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox9ActionPerformed
        double qnty = (Integer) vspinner22.getValue();
        double ct2 = 290.00 * qnty;
        if (cbox9.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner22.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner22.setEnabled(true);
        }
    }//GEN-LAST:event_cbox9ActionPerformed

    private void fboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fboxActionPerformed
        double qnty = (Integer) vspinner21.getValue();
        double ct2 = 200.00 * qnty;
        if (fbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner21.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner21.setEnabled(true);
        }
    }//GEN-LAST:event_fboxActionPerformed

    private void vboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vboxActionPerformed
        double qnty = (Integer) vspinner.getValue();
        double ct2 = 120.00 * qnty;
        if (vbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner.setEnabled(true);
        }
    }//GEN-LAST:event_vboxActionPerformed

    private void cbox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox10ActionPerformed
        double qnty = (Integer) vspinner1.getValue();
        double ct2 = 240.00 * qnty;
        if (cbox10.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner1.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner1.setEnabled(true);
        }
    }//GEN-LAST:event_cbox10ActionPerformed

    private void hboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hboxActionPerformed
        double qnty = (Integer) vspinner2.getValue();
        double ct2 = 290.00 * qnty;
        if (hbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner2.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner2.setEnabled(true);
        }
    }//GEN-LAST:event_hboxActionPerformed

    private void kboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kboxActionPerformed
        double qnty = (Integer) vspinner3.getValue();
        double ct2 = 190.00 * qnty;
        if (kbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner3.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner3.setEnabled(true);
        }
    }//GEN-LAST:event_kboxActionPerformed

    private void kbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kbox1ActionPerformed
        double qnty = (Integer) vspinner4.getValue();
        double ct2 = 220.00 * qnty;
        if (kbox1.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner4.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner4.setEnabled(true);
        }
    }//GEN-LAST:event_kbox1ActionPerformed

    private void vbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vbox1ActionPerformed
        double qnty = (Integer) vspinner5.getValue();
        double ct2 = 195.00 * qnty;
        if (vbox1.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner5.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner5.setEnabled(true);
        }
    }//GEN-LAST:event_vbox1ActionPerformed

    private void zboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zboxActionPerformed
        double qnty = (Integer) vspinner7.getValue();
        double ct2 = 145.00 * qnty;
        if (zbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner7.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner7.setEnabled(true);
        }
    }//GEN-LAST:event_zboxActionPerformed

    private void bbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbox1ActionPerformed
        double qnty = (Integer) vspinner6.getValue();
        double ct2 = 195.00 * qnty;
        if (bbox1.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner6.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner6.setEnabled(true);
        }
    }//GEN-LAST:event_bbox1ActionPerformed

    private void mboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mboxActionPerformed
        double qnty = (Integer) vspinner8.getValue();
        double ct2 = 280.00 * qnty;
        if (mbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner8.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner8.setEnabled(true);
        }
    }//GEN-LAST:event_mboxActionPerformed

    private void sboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sboxActionPerformed
        double qnty = (Integer) vspinner9.getValue();
        double ct2 = 1700.00 * qnty;
        if (sbox.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner9.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner9.setEnabled(true);
        }
    }//GEN-LAST:event_sboxActionPerformed

    private void cbox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox11ActionPerformed
        double qnty = (Integer) vspinner11.getValue();
        double ct2 = 165.00 * qnty;
        if (cbox11.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner11.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner11.setEnabled(true);
        }
    }//GEN-LAST:event_cbox11ActionPerformed

    private void sbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbox1ActionPerformed
        double qnty = (Integer) vspinner10.getValue();
        double ct2 = 285.00 * qnty;
        if (sbox1.isSelected()) {
            sum(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner10.setEnabled(false);
        } else {
            sub(ct2);
            tax(sum);
            pamount = sum + tax;
            ptxt.setText(String.valueOf(pamount) + "   BDT");
            ttxt.setText(String.valueOf(sum));
            ttax.setText(String.valueOf(tax) + " BDT" + "         (15% Vat included)");
            vspinner10.setEnabled(true);
        }
    }//GEN-LAST:event_sbox1ActionPerformed

    private void vspinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinnerStateChanged
        double qnty = (Integer) vspinner.getValue();
        ptotal = 120.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinnerStateChanged

    private void vspinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner1StateChanged
        double qnty = (Integer) vspinner1.getValue();
        ptotal = 240.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner1StateChanged

    private void vspinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner2StateChanged
        double qnty = (Integer) vspinner2.getValue();
        ptotal = 290.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner2StateChanged

    private void vspinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner3StateChanged
        double qnty = (Integer) vspinner3.getValue();
        ptotal = 190.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner3StateChanged

    private void vspinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner4StateChanged
        double qnty = (Integer) vspinner4.getValue();
        ptotal = 220.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner4StateChanged

    private void vspinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner5StateChanged
        double qnty = (Integer) vspinner5.getValue();
        ptotal = 195.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner5StateChanged

    private void vspinner6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner6StateChanged
        double qnty = (Integer) vspinner6.getValue();
        ptotal = 195.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner6StateChanged

    private void vspinner7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner7StateChanged
        double qnty = (Integer) vspinner7.getValue();
        ptotal = 145.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner7StateChanged

    private void vspinner8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner8StateChanged
        double qnty = (Integer) vspinner8.getValue();
        ptotal = 280.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner8StateChanged

    private void vspinner9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner9StateChanged
        double qnty = (Integer) vspinner9.getValue();
        ptotal = 1700.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner9StateChanged

    private void vspinner10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner10StateChanged
        double qnty = (Integer) vspinner10.getValue();
        ptotal = 285.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner10StateChanged

    private void vspinner11StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner11StateChanged
        double qnty = (Integer) vspinner11.getValue();
        ptotal = 165.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner11StateChanged

    private void vspinner12StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner12StateChanged

        double qnty = (Integer) vspinner12.getValue();

        ptotal = 280.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
        //ttxt.setText(String.valueOf(ptotal));

    }//GEN-LAST:event_vspinner12StateChanged

    private void vspinner13StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner13StateChanged
        double qnty = (Integer) vspinner13.getValue();

        ptotal = 220.00 * qnty;

        //ttxt.setText(String.valueOf(ptotal));
        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner13StateChanged

    private void vspinner14StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner14StateChanged
        double qnty = (Integer) vspinner14.getValue();
        ptotal = 290.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner14StateChanged

    private void vspinner15StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner15StateChanged
        double qnty = (Integer) vspinner15.getValue();
        ptotal = 290.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner15StateChanged

    private void vspinner16StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner16StateChanged
        double qnty = (Integer) vspinner16.getValue();
        ptotal = 180.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner16StateChanged

    private void vspinner17StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner17StateChanged
        double qnty = (Integer) vspinner17.getValue();
        ptotal = 200.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner17StateChanged

    private void vspinner18StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner18StateChanged
        double qnty = (Integer) vspinner18.getValue();
        ptotal = 580.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner18StateChanged

    private void vspinner19StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner19StateChanged
        double qnty = (Integer) vspinner19.getValue();
        ptotal = 1100.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner19StateChanged

    private void vspinner22StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner22StateChanged
        double qnty = (Integer) vspinner22.getValue();
        ptotal = 290.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner22StateChanged

    private void vspinner21StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vspinner21StateChanged
        double qnty = (Integer) vspinner21.getValue();
        ptotal = 200.00 * qnty;

        ltlbl.setText(String.valueOf(ptotal));
    }//GEN-LAST:event_vspinner21StateChanged

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        addtxt.setText("0");
        initBalence();
        //payment();
        
        //rttxt.setText(String.valueOf(returnb) + " BDT");
        //int n = random.nextInt(max - min + 1) + min;
        if( payment()){
        orderField.setText(orderField.getText() );
        txtarea.setText("Order No:" + orderField.getText() + " \t" + "Date :" + dateField.getText() + "\n" + "Name: " + nameField.getText() + "\n" + "Address: " + addressField.getText() + "\n--------------------------------------------------\n");

        if (c == 1) {
            cBalence = cBalence - cBalence;

            if (cbox1.isSelected()) {
                if ((Integer) vspinner12.getValue() != 0) {

                    txtarea.append("Combo 1 Price\t" + 280.00 * (Integer) vspinner12.getValue() + " BDT  " + vspinner12.getValue() + "   Piece\n");

                }
            }
            if (cbox2.isSelected()) {
                if ((Integer) vspinner13.getValue() != 0) {

                    txtarea.append("Combo2 Price\t" + 220.00 * (Integer) vspinner13.getValue() + " BDT  " + vspinner13.getValue() + "   Piece\n");

                }
            }
            if (cbox3.isSelected()) {
                if ((Integer) vspinner14.getValue() != 0) {

                    txtarea.append("Combo3 Price\t" + 290.00 * (Integer) vspinner14.getValue() + " BDT  " + vspinner14.getValue() + "   Piece\n");

                }
            }
            if (cbox4.isSelected()) {
                if ((Integer) vspinner15.getValue() != 0) {

                    txtarea.append("Combo4 Price\t" + 290.00 * (Integer) vspinner15.getValue() + " BDT  " + vspinner15.getValue() + "   Piece\n");

                }
            }
            if (cbox5.isSelected()) {
                if ((Integer) vspinner16.getValue() != 0) {

                    txtarea.append("Combo5 Price\t" + 180.00 * (Integer) vspinner16.getValue() + " BDT  " + vspinner16.getValue() + "   Piece\n");

                }
            }
            if (cbox6.isSelected()) {
                if ((Integer) vspinner17.getValue() != 0) {

                    txtarea.append("Combo6 Price\t" + 200.00 * (Integer) vspinner17.getValue() + " BDT  " + vspinner17.getValue() + "   Piece\n");

                }
            }
            if (cbox7.isSelected()) {
                if ((Integer) vspinner18.getValue() != 0) {

                    txtarea.append("Couple Combo  Price\t" + 580.00 * (Integer) vspinner18.getValue() + " BDT  " + vspinner18.getValue() + "   Piece\n");

                }
            }
            if (cbox8.isSelected()) {
                if ((Integer) vspinner19.getValue() != 0) {

                    txtarea.append("Combo8 Price\t" + 1100.00 * (Integer) vspinner19.getValue() + " BDT  " + vspinner19.getValue() + "   Piece\n");

                }
            }
            if (cbox9.isSelected()) {
                if ((Integer) vspinner22.getValue() != 0) {

                    txtarea.append("Combo9 Price\t" + 290.00 * (Integer) vspinner22.getValue() + " BDT  " + vspinner22.getValue() + "   Piece\n");

                }
            }
            if (fbox.isSelected()) {
                if ((Integer) vspinner21.getValue() != 0) {

                    txtarea.append("Fried Rice Meal Price\t" + 200.00 * (Integer) vspinner21.getValue() + " BDT  " + vspinner21.getValue() + "   Piece\n");

                }
            }
            if (vbox.isSelected()) {
                if ((Integer) vspinner.getValue() != 0) {

                    txtarea.append("Vegetable green salad Price\t" + 120.00 * (Integer) vspinner.getValue() + " BDT  " + vspinner.getValue() + "   Piece\n");

                }
            }
            if (cbox10.isSelected()) {
                if ((Integer) vspinner1.getValue() != 0) {

                    txtarea.append("Crispy Strips Price\t" + 240.00 * (Integer) vspinner1.getValue() + " BDT  " + vspinner1.getValue() + "   Piece\n");

                }
            }
            if (hbox.isSelected()) {
                if ((Integer) vspinner2.getValue() != 0) {

                    txtarea.append("Hot Wings Price\t" + 290.00 * (Integer) vspinner2.getValue() + " BDT  " + vspinner2.getValue() + "   Piece\n");

                }
            }
            if (kbox.isSelected()) {
                if ((Integer) vspinner3.getValue() != 0) {

                    txtarea.append("Kids1 Price\t" + 190.00 * (Integer) vspinner3.getValue() + " BDT  " + vspinner3.getValue() + "   Piece\n");

                }
            }
            if (kbox1.isSelected()) {
                if ((Integer) vspinner4.getValue() != 0) {

                    txtarea.append("Kids2 Price\t" + 220.00 * (Integer) vspinner4.getValue() + " BDT  " + vspinner4.getValue() + "   Piece\n");

                }
            }
            if (vbox1.isSelected()) {
                if ((Integer) vspinner5.getValue() != 0) {

                    txtarea.append("vegetable burger Price\t" + 195.00 * (Integer) vspinner5.getValue() + " BDT  " + vspinner5.getValue() + "   Piece\n");

                }
            }
            if (zbox.isSelected()) {
                if ((Integer) vspinner7.getValue() != 0) {

                    txtarea.append("zing pop chicken Price\t" + 145.00 * (Integer) vspinner7.getValue() + " BDT  " + vspinner7.getValue() + "   Piece\n");

                }
            }
            if (bbox1.isSelected()) {
                if ((Integer) vspinner6.getValue() != 0) {

                    txtarea.append("beef burger Price\t" + 195.00 * (Integer) vspinner6.getValue() + " BDT  " + vspinner6.getValue() + "   Piece\n");

                }
            }
            if (mbox.isSelected()) {
                if ((Integer) vspinner8.getValue() != 0) {

                    txtarea.append("mini burger Price\t" + 280.00 * (Integer) vspinner8.getValue() + " BDT  " + vspinner8.getValue() + "   Piece\n");

                }
            }
            if (sbox.isSelected()) {
                if ((Integer) vspinner9.getValue() != 0) {

                    txtarea.append("spicy chicken Price\t" + 1700.00 * (Integer) vspinner9.getValue() + " BDT  " + vspinner9.getValue() + "   Piece\n");

                }
            }
            if (cbox11.isSelected()) {
                if ((Integer) vspinner11.getValue() != 0) {

                    txtarea.append("chicken salsa wrap Price\t" + 165.00 * (Integer) vspinner11.getValue() + " BDT  " + vspinner11.getValue() + "   Piece\n");

                }
            }
            if (sbox1.isSelected()) {
                if ((Integer) vspinner10.getValue() != 0) {

                    txtarea.append("super beef burger Price\t" + 285.00 * (Integer) vspinner10.getValue() + " BDT  " + vspinner10.getValue() + "   Piece\n");

                }
            }
            txtarea.append("\n\n\n" + "Total Amount\t" + sum + " BDT (without vat)\nTotal Vat\t" + tax + " BDT (15% vat including)\nTotal Amount\t" + pamount + " BDT (vat included)\n" + "Status :Paid");

        }
           showBalence();
           datasave();
        }
       
        


    }//GEN-LAST:event_jButton27ActionPerformed

    private void rbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnActionPerformed
        sum = sum - sum;
        plb.setText("");
        ttxt.setText("");
        ptxt.setText("");
        ttax.setText("");
        searchField.setText("");
        nameField.setText("");
        ctxt.setText("");
        acFeild.setText("AC");
        addressField.setText("");
        addtxt.setText("0");
        orderField.setText("");
        dateField.setText("");
        //rttxt.setText("");
       
        txtarea.setText("");
        if (cbox1.isSelected()) {
            cbox1.setSelected(false);
            vspinner12.setValue(0);
            vspinner12.setEnabled(true);
        }
        if (cbox2.isSelected()) {
            cbox2.setSelected(false);
            vspinner13.setValue(0);
            vspinner13.setEnabled(true);
        }
        if (cbox3.isSelected()) {
            cbox3.setSelected(false);
            vspinner14.setValue(0);
            vspinner14.setEnabled(true);
        }
        if (cbox5.isSelected()) {
            cbox5.setSelected(false);
            vspinner16.setValue(0);
            vspinner16.setEnabled(true);
        }
        if (cbox6.isSelected()) {
            cbox6.setSelected(false);
            vspinner17.setValue(0);
            vspinner17.setEnabled(true);
        }
        if (cbox7.isSelected()) {
            cbox7.setSelected(false);
            vspinner18.setValue(0);
            vspinner18.setEnabled(true);
        }
        if (cbox8.isSelected()) {
            cbox8.setSelected(false);
            vspinner19.setValue(0);
            vspinner19.setEnabled(true);
        }
        if (cbox9.isSelected()) {
            cbox9.setSelected(false);
            vspinner22.setValue(0);
            vspinner22.setEnabled(true);
        }
        if (fbox.isSelected()) {
            fbox.setSelected(false);
            vspinner21.setValue(0);
            vspinner21.setEnabled(true);
        }
        if (cbox1.isSelected()) {
            cbox1.setSelected(false);
            vspinner12.setValue(0);
            vspinner12.setEnabled(true);
        }
        if (vbox.isSelected()) {
            vbox.setSelected(false);
            vspinner.setValue(0);
            vspinner.setEnabled(true);
        }
        if (cbox10.isSelected()) {
            cbox10.setSelected(false);
            vspinner1.setValue(0);
            vspinner1.setEnabled(true);
        }
        if (hbox.isSelected()) {
            hbox.setSelected(false);
            vspinner2.setValue(0);
            vspinner2.setEnabled(true);
        }
        if (kbox.isSelected()) {
            kbox.setSelected(false);
            vspinner3.setValue(0);
            vspinner3.setEnabled(true);
        }
        if (kbox1.isSelected()) {
            kbox1.setSelected(false);
            vspinner4.setValue(0);
            vspinner4.setEnabled(true);
        }
        if (vbox1.isSelected()) {
            vbox1.setSelected(false);
            vspinner5.setValue(0);
            vspinner5.setEnabled(true);
        }
        if (zbox.isSelected()) {
            zbox.setSelected(false);
            vspinner7.setValue(0);
            vspinner7.setEnabled(true);
        }
        if (bbox1.isSelected()) {
            bbox1.setSelected(false);
            vspinner6.setValue(0);
            vspinner6.setEnabled(true);
        }
        if (mbox.isSelected()) {
            mbox.setSelected(false);
            vspinner8.setValue(0);
            vspinner8.setEnabled(true);
        }
        if (sbox.isSelected()) {
            sbox.setSelected(false);
            vspinner9.setValue(0);
            vspinner9.setEnabled(true);
        }
        if (cbox11.isSelected()) {
            cbox11.setSelected(false);
            vspinner11.setValue(0);
            vspinner11.setEnabled(true);
        }
        if (sbox1.isSelected()) {
            sbox1.setSelected(false);
            vspinner10.setValue(0);
            vspinner10.setEnabled(true);
        } else {
            vspinner12.setValue(0);
            vspinner13.setValue(0);
            vspinner14.setValue(0);
            vspinner15.setValue(0);
            vspinner16.setValue(0);
            vspinner17.setValue(0);
            vspinner18.setValue(0);
            vspinner19.setValue(0);
            vspinner21.setValue(0);
            vspinner22.setValue(0);
            vspinner10.setValue(0);
            vspinner11.setValue(0);
            vspinner1.setValue(0);
            vspinner2.setValue(0);
            vspinner3.setValue(0);
            vspinner4.setValue(0);
            vspinner5.setValue(0);
            vspinner6.setValue(0);
            vspinner7.setValue(0);
            vspinner8.setValue(0);
            vspinner9.setValue(0);
            vspinner.setValue(0);

        }


    }//GEN-LAST:event_rbtnActionPerformed

    private void searchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldMouseClicked
        searchField.setText("");
    }//GEN-LAST:event_searchFieldMouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        String s = searchField.getText();

        if (s.equalsIgnoreCase("Enter Your AC NO")) {
            JOptionPane.showMessageDialog(null, "Please Enter Order NO");
        } else {
            ShowCustomerInfo p = new ShowCustomerInfo();
            p.showdata(s);
            p.setVisible(true);

        }


    }//GEN-LAST:event_jButton29ActionPerformed

    private void cbox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbox1StateChanged

    }//GEN-LAST:event_cbox1StateChanged

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed

    }//GEN-LAST:event_dateFieldActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if(addtxt.getText().equals("0")){
            JOptionPane.showMessageDialog(null, "Please,Add deposite!");
        }
        else{
            addBalence(Double.parseDouble(addtxt.getText()));
            showBalence();
            
        }
        addtxt.setText("0");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void showBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBActionPerformed
      showBalence();
    }//GEN-LAST:event_showBActionPerformed

    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
      
    }//GEN-LAST:event_jButton27MouseClicked

    private void rbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn1ActionPerformed
       try{
          boolean complete=txtarea.print();
          if(complete){
              JOptionPane.showMessageDialog(null, "Done Printing","Information",JOptionPane.INFORMATION_MESSAGE);
              
          }
          else{
              JOptionPane.showMessageDialog(null, "Printing","printer",JOptionPane.ERROR_MESSAGE);
                      
       }
       }catch(PrinterException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_rbtn1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FastFoodManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FastFoodManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FastFoodManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FastFoodManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FastFoodManagement().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acFeild;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField addtxt;
    private javax.swing.JCheckBox bbox1;
    private javax.swing.JCheckBox cbox1;
    private javax.swing.JCheckBox cbox10;
    private javax.swing.JCheckBox cbox11;
    private javax.swing.JCheckBox cbox2;
    private javax.swing.JCheckBox cbox3;
    private javax.swing.JCheckBox cbox4;
    private javax.swing.JCheckBox cbox5;
    private javax.swing.JCheckBox cbox6;
    private javax.swing.JCheckBox cbox7;
    private javax.swing.JCheckBox cbox8;
    private javax.swing.JCheckBox cbox9;
    private javax.swing.JLabel ctxt;
    private javax.swing.JTextField dateField;
    private javax.swing.JCheckBox fbox;
    private javax.swing.JCheckBox hbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox kbox;
    private javax.swing.JCheckBox kbox1;
    private javax.swing.JLabel ltlbl;
    private javax.swing.JCheckBox mbox;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField orderField;
    private javax.swing.JLabel plb;
    private javax.swing.JTextField ptxt;
    private javax.swing.JButton rbtn;
    private javax.swing.JButton rbtn1;
    private javax.swing.JCheckBox sbox;
    private javax.swing.JCheckBox sbox1;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton showB;
    private javax.swing.JTextField ttax;
    private javax.swing.JTextField ttxt;
    private javax.swing.JTextArea txtarea;
    private javax.swing.JCheckBox vbox;
    private javax.swing.JCheckBox vbox1;
    private javax.swing.JSpinner vspinner;
    private javax.swing.JSpinner vspinner1;
    private javax.swing.JSpinner vspinner10;
    private javax.swing.JSpinner vspinner11;
    private javax.swing.JSpinner vspinner12;
    private javax.swing.JSpinner vspinner13;
    private javax.swing.JSpinner vspinner14;
    private javax.swing.JSpinner vspinner15;
    private javax.swing.JSpinner vspinner16;
    private javax.swing.JSpinner vspinner17;
    private javax.swing.JSpinner vspinner18;
    private javax.swing.JSpinner vspinner19;
    private javax.swing.JSpinner vspinner2;
    private javax.swing.JSpinner vspinner21;
    private javax.swing.JSpinner vspinner22;
    private javax.swing.JSpinner vspinner3;
    private javax.swing.JSpinner vspinner4;
    private javax.swing.JSpinner vspinner5;
    private javax.swing.JSpinner vspinner6;
    private javax.swing.JSpinner vspinner7;
    private javax.swing.JSpinner vspinner8;
    private javax.swing.JSpinner vspinner9;
    private javax.swing.JCheckBox zbox;
    // End of variables declaration//GEN-END:variables
}
