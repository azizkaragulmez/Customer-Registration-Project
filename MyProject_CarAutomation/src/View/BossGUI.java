package View;

import Helper.Config;
import Helper.helper;
import Model.Boss;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox comboBox1;
    private JTextArea txtarea_boss_work;
    private JTextField fld_boss_money;
    private JButton btn_boss_added;


    private DefaultTableModel mdl_boss_list;   //verilerimizi modeller ile katarıyoruz
    private Object[] row_boss_list;   //Jtableın satrına ekleceklerini tutmak için oluşturduk


    private Boss boss; //biz veritabanından çekerken Boss türünde çekiceğimiz için bir değişken tanımladık
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
