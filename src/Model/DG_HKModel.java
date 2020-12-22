/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class DG_HKModel {
    private int nam;
    private String dongGopID;
    private String hoKhauID;
    private int soTienDong;
    private Date ngayDong;
    private String nguoiDong;
    private int idNguoiThu;

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
    
    public String getDongGopID() {
        return dongGopID;
    }

    public void setDongGopID(String dongGopID) {
        this.dongGopID = dongGopID;
    }

    public String getHoKhauID() {
        return hoKhauID;
    }

    public void setHoKhauID(String hoKhauID) {
        this.hoKhauID = hoKhauID;
    }

    public int getSoTienDong() {
        return soTienDong;
    }

    public void setSoTienDong(int soTienDong) {
        this.soTienDong = soTienDong;
    }

    public Date getNgayDong() {
        return ngayDong;
    }

    public void setNgayDong(Date ngayDong) {
        this.ngayDong = ngayDong;
    }

    public String getNguoiDong() {
        return nguoiDong;
    }

    public void setNguoiDong(String nguoiDong) {
        this.nguoiDong = nguoiDong;
    }

    public int getIdNguoiThu() {
        return idNguoiThu;
    }

    public void setIdNguoiThu(int idNguoiThu) {
        this.idNguoiThu = idNguoiThu;
    }
    
}
