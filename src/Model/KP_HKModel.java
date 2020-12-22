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
public class KP_HKModel {
    private int nam;
    private String hoKhauID;
    private int daDong; // kiểu 0 - 1
    private int conPhaiDong;
    private Date ngayDong;
    private String nguoiDong;
    private int idNguoiThu;

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getHoKhauID() {
        return hoKhauID;
    }

    public void setHoKhauID(String hoKhauID) {
        this.hoKhauID = hoKhauID;
    }

    public int getDaDong() {
        return daDong;
    }

    public void setDaDong(int daDong) {
        this.daDong = daDong;
    }

    public int getConPhaiDong() {
        return conPhaiDong;
    }

    public void setConPhaiDong(int conPhaiDong) {
        this.conPhaiDong = conPhaiDong;
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
