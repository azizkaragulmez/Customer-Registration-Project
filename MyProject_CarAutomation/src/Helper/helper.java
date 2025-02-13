package Helper;

import javax.swing.*;

public class helper {


    public static void setlayout(){   //Bura da yazarak oluşturacağımız diğer tablolarda kolayca çekerek kod düzenini sağlıyabiliyoruz.
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }


    //JTextFieldların boş olup olmadığını kontrol ediyor
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().length() ==0;   //Burda JTextFieldın boş olup olmaıdğını sorguluyoruz direk kod düzeni için bura yazdık
    }



    public static void showMsg (String str){
        optionPageTR();  //ok için yazdığımızı burda çağırdık içinde de yazabilirdik ama sonra da çağrabiliriz
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!";
                title ="Hata";
                break;
            case "done":
                msg ="İşlem Başarılı!";
                title="Sonuç";
                break;
            case "error":
                msg ="Bir hata oluştu !";
                title="HATA";
                break;
            default:
                msg = str;
                title="Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    //Ok yazan yeri değiştirmek için metod
    public static void optionPageTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");  //Bu swing dökümasyondan baktı ve ok yazan yeri tamamla değiştirdik
    }

}
