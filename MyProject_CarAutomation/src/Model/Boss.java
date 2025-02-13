package Model;

import Helper.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Boss {


    private int User_id;
    private String User_name;
    private String User_tel;
    private String User_car;
    private String User_carModel;
    private int User_carYear;
    private String User_work;
    private Float User_money;
    private Date User_Date;


    public Boss(){

    }
    public Boss(int user_id, String user_name, String user_tel, String user_car, String user_carModel, int user_carYear, String user_work, Float user_money) {
        User_id = user_id;
        User_name = user_name;
        User_tel = user_tel;
        User_car = user_car;
        User_carModel = user_carModel;
        User_carYear = user_carYear;
        User_work = user_work;
        User_money = user_money;


    }
    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_tel() {
        return User_tel;
    }

    public void setUser_tel(String user_tel) {
        User_tel = user_tel;
    }

    public String getUser_car() {
        return User_car;
    }

    public void setUser_car(String user_car) {
        User_car = user_car;
    }

    public String getUser_carModel() {
        return User_carModel;
    }

    public void setUser_carModel(String user_carModel) {
        User_carModel = user_carModel;
    }

    public int getUser_carYear() {
        return User_carYear;
    }

    public void setUser_carYear(int user_carYear) {
        User_carYear = user_carYear;
    }

    public String getUser_work() {
        return User_work;
    }

    public void setUser_work(String user_work) {
        User_work = user_work;
    }

    public Float getUser_money() {
        return User_money;
    }

    public void setUser_money(Float user_money) {
        User_money = user_money;
    }

    public Date getUser_Date() {
        return User_Date;
    }

    public void setUser_Date(Date user_Date) {
        User_Date = user_Date;
    }


    public static ArrayList<Boss> getList () {
        ArrayList<Boss> bossList = new ArrayList<>();
        String query = "SELECT * FROM user";
        Boss obj;
        try {
            Statement st = DB.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Boss();
                obj.setUser_id(rs.getInt("User_id"));
                obj.setUser_name(rs.getString("User_name"));
                obj.setUser_tel(rs.getString("User_tel"));
                obj.setUser_car(rs.getString("User_car"));
                obj.setUser_carModel(rs.getString("User_carModel"));
                obj.setUser_carYear(rs.getInt("User_carYear"));
                obj.setUser_work(rs.getString("User_work"));
                obj.setUser_money(rs.getFloat("User_money"));
                obj.setUser_Date(rs.getDate("User_Date"));
                bossList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bossList;
    }



}
