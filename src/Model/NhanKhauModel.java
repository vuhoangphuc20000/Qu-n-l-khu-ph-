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
public class NhanKhauModel {
    private int nhanKhauID;
    private String hoKhauID;
    private String quanHeVoiChuHo;
    private String hoTen;
    private String gioiTinh;
    private String nguyenQuan;
    private String diaChiHienTai;
    private String danToc;
    private String tonGiao;
    private String quocTich;
    private String ngheNghiep;
    private String noiLamViec;
    private Date ngayTao;
    private int idNguoiTao;
    private Date ngayXoa,ngaySinh;
    private int CMT;

    public int getCMT() {
        return CMT;
    }

    public void setCMT(int CMT) {
        this.CMT = CMT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    private int idNguoiXoa;
    private String lyDoXoa;

    public int getNhanKhauID() {
        return nhanKhauID;
    }

    public void setNhanKhauID(int nhanKhauID) {
        this.nhanKhauID = nhanKhauID;
    }

    public String getHoKhauID() {
        return hoKhauID;
    }

    public void setHoKhauID(String hoKhauID) {
        this.hoKhauID = hoKhauID;
    }

    public String getQuanHeVoiChuHo() {
        return quanHeVoiChuHo;
    }

    public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai = diaChiHienTai;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIdNguoiTao() {
        return idNguoiTao;
    }

    public void setIdNguoiTao(int idNguoiTao) {
        this.idNguoiTao = idNguoiTao;
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
