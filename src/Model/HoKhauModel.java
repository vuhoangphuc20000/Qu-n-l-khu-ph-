/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoKhauModel {
    private String hoKhauID;
    private int idChuHo;
    private String diaChi;
    private Date ngayLap;
    private int idNguoiLap;
    private Date ngayXoa;
    private int idNguoiXoa;
    private String lyDoXoa;

    public String getHoKhauID() {
        return hoKhauID;
    }

    public void setHoKhauID(String hoKhauID) {
        this.hoKhauID = hoKhauID;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getIdNguoiLap() {
        return idNguoiLap;
    }

    public void setIdNguoiLap(int idNguoiLap) {
        this.idNguoiLap = idNguoiLap;
    }

    public Date getNgayXoa() {
        return ngayXoa;
    }

    public void setNgayXoa(Date ngayXoa) {
        this.ngayXoa = ngayXoa;
    }

    public int getIdNguoiXoa() {
        return idNguoiXoa;
    }

    public void setIdNguoiXoa(int idNguoiXoa) {
        this.idNguoiXoa = idNguoiXoa;
    }

    public String getLyDoXoa() {
        return lyDoXoa;
    }

    public void setLyDoXoa(String lyDoXoa) {
        this.lyDoXoa = lyDoXoa;
    }
    
}
