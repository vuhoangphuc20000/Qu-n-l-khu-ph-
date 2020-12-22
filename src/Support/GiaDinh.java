/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Model.HoKhauModel;
import Model.NhanKhauModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import services.SQLConnection;

/**
 *
 * @author Admin
 * Trả về 1 hộ gia đình
 */

public class GiaDinh {
    private HoKhauModel hokhau; //1 hộ khẩu và nhiều thành viên
    private ArrayList<NhanKhauModel> danhsach;
    
    public GiaDinh(){
        hokhau = new HoKhauModel();
        danhsach = new ArrayList();
    }
    
    public HoKhauModel getHokhau() {
        return hokhau;
    }

    public void setHokhau(HoKhauModel hokhau) {
        this.hokhau = hokhau;
    }

    public ArrayList<NhanKhauModel> getDanhsach() {
        return danhsach;
    }
    
    public void setDanhSach(ArrayList<NhanKhauModel> ds) {
         this.danhsach = ds;
    }
    public void addNhanKhau(NhanKhauModel nhankhau){
        danhsach.add(danhsach.size(), nhankhau);
    }
    
    public void suaNhanKhau(NhanKhauModel nhankhau){
        for(int i = 0; i < danhsach.size();i++){
            if(danhsach.get(i).getNhanKhauID() == nhankhau.getNhanKhauID()){
                danhsach.remove(i);
                danhsach.add(i,nhankhau);
                break;
            }
        }
    }
    
    public void addGiaDinh(){
        addHoKhauSQL();
        updateMultipleNhanKhauSQL();
    }
    
    public void addHoKhauSQL(){ //thêm mới phần hộ khẩu
        try{
            Connection conn = SQLConnection.getConnection();
            String sql = "insert into HOKHAU (HOKHAUID,DIACHI,IDCHUHO,IDNGUOILAP,NGAYLAP) values(?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,hokhau.getHoKhauID());
            stm.setString(2,hokhau.getDiaChi());
            stm.setInt(3,hokhau.getIdChuHo());
            stm.setInt(4, hokhau.getIdNguoiLap());
            stm.setDate(5, (Date) hokhau.getNgayLap());
            int x = stm.executeUpdate();
            if(x > 0) JOptionPane.showMessageDialog(null, "Lưu OK");
            conn.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateNhanKhauSQL(NhanKhauModel nhankhau){ //cập nhật 1 cá nhân
        try{
            Connection conn = SQLConnection.getConnection();
            String sql = "update NHANKHAU SET HOKHAUID=?,QUANHEVOICHUHO=? WHERE NHANKHAUID=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,hokhau.getHoKhauID());
            stm.setString(2,nhankhau.getQuanHeVoiChuHo());
            stm.setInt(3,nhankhau.getNhanKhauID());
            int x = stm.executeUpdate();
            //if(x > 0) JOptionPane.showMessageDialog(null, "Lưu OK");
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   
    public void updateMultipleNhanKhauSQL(){
        for(int i = 0; i < danhsach.size(); i++){
            updateNhanKhauSQL(danhsach.get(i));
        }
    }
    
    public void deleteGiaDinh(){
        deleteHoKhau();
        updateDeleteMultipleHoKhauID();
    }
    public void updateDeleteHoKhauID(NhanKhauModel nhankhau){
        try{
            Connection conn = SQLConnection.getConnection();
            String sql = "update NHANKHAU SET HOKHAUID=? WHERE NHANKHAUID=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,null);
            stm.setInt(2,nhankhau.getNhanKhauID());
            int x = stm.executeUpdate();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateDeleteMultipleHoKhauID(){
        for(int i = 0; i < danhsach.size(); i++){
            updateDeleteHoKhauID(danhsach.get(i));
        }
    }
    
    public void deleteHoKhau(){
        try{
            Connection conn = SQLConnection.getConnection();
            String sql = "delete from HOKHAU WHERE HOKHAUID=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,hokhau.getHoKhauID());
            int x = stm.executeUpdate();
            conn.close();
            if(x>0) JOptionPane.showMessageDialog(null, "Xóa thành công");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
