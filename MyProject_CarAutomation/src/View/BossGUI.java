package View;

import Helper.Config;
import Helper.helper;
import Model.Boss;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BossGUI extends JFrame {
    private JPanel wrapper;
    private JPanel jPanel;
    private JTable tbl_boss_list;
    private JTextField fld_boss_welcome;
    private JButton btn_boss_çıkış;
    private JLabel lbl_boss_welcome;
    private JPanel JPanel_added;
    private JTextField fld_boss_name;
    private JTextField fld_boss_tel;
    private JComboBox cbm_boss_marka;
    private JTextField fld_boss_model;
    private JComboBox cmb_boss_year;
    private JTextArea txtarea_boss_work;
    private JTextField fld_boss_money;
    private JButton btn_boss_added;
    private JButton btn_boss_delete;
    private JTextField fld_boss_ID;
    private JTextField fld_boss_serachname;
    private JTextField fld_boss_search_tel;
    private JButton btn_boss_search;
    private JButton çıkışYapButton;


    private DefaultTableModel mdl_boss_list;   //verilerimizi modeller ile katarıyoruz
    private Object[] row_boss_list;   //Jtableın satrına ekleceklerini tutmak için oluşturduk


    private Boss boss; //biz veritabanından çekerken Boss.java türünde çekiceğimiz için bir değişken tanımladık
    public BossGUI (Boss boss){
        this.boss=boss;

        add(wrapper);
        setSize(1300,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TİTLE);
        setVisible(true);
        setResizable(false);



        mdl_boss_list = new DefaultTableModel();
        Object [] col_boss_list = {"ID","Ad Soyad"," Telefon","Araç Marka","Araç Model","Yılı","Yapılan İşlem","Ücret","Kayıt Tarihi"};
        mdl_boss_list.setColumnIdentifiers(col_boss_list);
        row_boss_list = new Object[col_boss_list.length];
        loadBossModel();
        tbl_boss_list.setModel(mdl_boss_list);
        tbl_boss_list.getTableHeader().setReorderingAllowed(false);


        lbl_boss_welcome.setText(Config.PROJECT_TİTLE+" Araç Kayıt Otomasyonu");

        fld_boss_ID.setEditable(false);  //Dışarıdan ID değer yazamazsın sadece mause ile tıklanan
        fld_boss_ID.setBackground(Color.LIGHT_GRAY);
       // fld_boss_ID.setText("ID Buraya Gelecek"); // ID değerini alıp yazabilirsin



        //textfieldların boyutlarını belirleyelim
        tbl_boss_list.getColumnModel().getColumn(0).setMaxWidth(25);
        tbl_boss_list.getColumnModel().getColumn(1).setMaxWidth(150);
        tbl_boss_list.getColumnModel().getColumn(2).setMaxWidth(100);
        tbl_boss_list.getColumnModel().getColumn(3).setMaxWidth(100);
        tbl_boss_list.getColumnModel().getColumn(4).setMaxWidth(75);
        tbl_boss_list.getColumnModel().getColumn(5).setMaxWidth(50);
        tbl_boss_list.getColumnModel().getColumn(6).setMaxWidth(450);
        tbl_boss_list.getColumnModel().getColumn(7).setMaxWidth(70);
        tbl_boss_list.getColumnModel().getColumn(8).setMaxWidth(80);

        txtarea_boss_work.setLineWrap(true);
        txtarea_boss_work.setWrapStyleWord(true);





        //Silme işleminde id yardımıylasiliyorduk ama string değerlerde yazılabiliyor ve bu hataya yol açıyor . Bizde tablodan seçerek tıklıyarak seçmek için model oluşturduk
        tbl_boss_list.getSelectionModel().addListSelectionListener(e -> {   //Bu şu demek seçilen değer üzerinde işlem yapmaya yarayan bir bölüm.(new ListSelectionListener())
            try {            //try catch içine almamızda ki neden seçerek yaptığımız silme işleminde seçili kaldığı için refresh edince hata veriyor
                String select_user_id = tbl_boss_list.getValueAt(tbl_boss_list.getSelectedRow(), 0).toString();    //Value et bize seçim yapılan konumu verir yani 0 sütun 1 satır gibi
                //tbl_user_list den satırı aldık neere seçildiyse id 0 da olduğu için 0. sütunu aldık ve toString yani object döndürdük
                fld_boss_ID.setText(select_user_id);
            }catch (Exception exception){

            }
        });

        //Çıkış Yap Butonu




        //Ekle Butonu
        btn_boss_added.addActionListener(e -> {

            if(fld_boss_name.getText().length()==0 || fld_boss_tel.getText().length()==0 || fld_boss_money.getText().length() == 0 || fld_boss_model.getText().length() == 0 ){
                helper.showMsg("fill");

            }else {
                String User_name =fld_boss_name.getText();
                String User_tel = fld_boss_tel.getText();
                String User_car = cbm_boss_marka.getSelectedItem().toString();
                String User_carModel= fld_boss_model.getText();
                int User_carYear = Integer.parseInt(cmb_boss_year.getSelectedItem().toString());
                String User_work = txtarea_boss_work.getText();
                int User_money= Integer.parseInt(fld_boss_money.getText());
                String User_Date  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                if (User_tel.length()!=11){
                    helper.showMsg("Lütfen telefon numarasını başında 0 olmak üzere 11 haneli giriniz");
                }
                else if(Boss.add(User_name,User_tel,User_car,User_carModel,User_carYear,User_work,User_money,User_Date)){
                    helper.showMsg("done");
                    loadBossModel();
                }
            }
        });

        //telefon textfieldı dinliyoruz
        fld_boss_tel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) { // Eğer rakam değilse
                    e.consume(); // Tuş girişini engelle
                }
            }
        });
        //Ad soyad textfield dinliyoruz
        fld_boss_name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (Character.isDigit(c)) { // Eğer rakam değilse
                    e.consume(); // Tuş girişini engelle
                }
            }
        });
        //Ücret Kısmını dinliyoruz
        fld_boss_money.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) { // Eğer rakam değilse
                    e.consume(); // Tuş girişini engelle
                }
            }
        });

        //Silme Butonu işlemleri
        btn_boss_delete.addActionListener(e -> {
                if (helper.isFieldEmpty(fld_boss_ID)){
                    helper.showMsg("fill");
                }else {
                    if (helper.confirm("sure")){
                    int User_id= Integer.parseInt(fld_boss_ID.getText());
                    if (Boss.delete(User_id)){
                        helper.showMsg("done");
                        loadBossModel();
                    }else {
                        helper.showMsg("error");
                    }
                }}
        });

        //Arama butonu
        btn_boss_search.addActionListener(e -> {
            String user_name = fld_boss_serachname.getText();
            String user_tel = fld_boss_search_tel.getText();

            if(helper.isFieldEmpty(fld_boss_serachname) && helper.isFieldEmpty(fld_boss_search_tel )){
                helper.showMsg("Arama Yapmak İçin Alanı Doldurunuz");
                loadBossModel();
            }else {
                String query = Boss.searchQuery(user_name,user_tel);
                loadBossModel(Boss.searchBossgetList(query));
            }

        });
        çıkışYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //Bizim loadModelimiz vardı ama biz burda ArrayList türünde oluşturarak overloading yaptık
    public void loadBossModel(ArrayList <Boss> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_boss_list.getModel();
        clearModel.setRowCount(0);

        for (Boss obj: list){
            System.out.println("çalıştı");
            row_boss_list[0]= obj.getUser_id();
            row_boss_list[1]= obj.getUser_name();
            row_boss_list[2]= obj.getUser_tel();
            row_boss_list[3]= obj.getUser_car();
            row_boss_list[4]= obj.getUser_carModel();
            row_boss_list[5]= obj.getUser_carYear();
            row_boss_list[6]= obj.getUser_work();
            row_boss_list[7]= obj.getUser_money();
            row_boss_list[8]=obj.getUser_Date();
            mdl_boss_list.addRow(row_boss_list);
        }
    }


    public void loadBossModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_boss_list.getModel();
        clearModel.setRowCount(0);
        for (Boss obj: Boss.getList()){
            row_boss_list[0]= obj.getUser_id();
            row_boss_list[1]= obj.getUser_name();
            row_boss_list[2]= obj.getUser_tel();
            row_boss_list[3]= obj.getUser_car();
            row_boss_list[4]= obj.getUser_carModel();
            row_boss_list[5]= obj.getUser_carYear();
            row_boss_list[6]= obj.getUser_work();
            row_boss_list[7]= obj.getUser_money();
            row_boss_list[8]=obj.getUser_Date();
            mdl_boss_list.addRow(row_boss_list);
        }
    }

    public static void main (String [] args){
            helper.setlayout();
            Boss boss1= new Boss();
            
            BossGUI bossGUI = new BossGUI(boss1);
    }


}
