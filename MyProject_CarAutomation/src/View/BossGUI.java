package View;

import Helper.Config;
import Helper.helper;
import Model.Boss;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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


    private DefaultTableModel mdl_boss_list;   //verilerimizi modeller ile katarıyoruz
    private Object[] row_boss_list;   //Jtableın satrına ekleceklerini tutmak için oluşturduk


    private Boss boss; //biz veritabanından çekerken Boss.java türünde çekiceğimiz için bir değişken tanımladık
    public BossGUI (Boss boss){
        this.boss=boss;


        add(wrapper);
        setSize(1200,600);
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

        //Çıkış Yap Butonu
        btn_boss_çıkış.addActionListener(e -> {
            dispose();
        });


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
                if (Boss.add(User_name,User_tel,User_car,User_carModel,User_carYear,User_work,User_money)){
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
            mdl_boss_list.addRow(row_boss_list);
        }
    }

    public static void main (String [] args){
            helper.setlayout();
            Boss boss1= new Boss();
            
            BossGUI bossGUI = new BossGUI(boss1);
    }
}
