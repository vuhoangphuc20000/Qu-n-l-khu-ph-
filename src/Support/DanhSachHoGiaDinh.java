/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Model.HoKhauModel;
import Model.NhanKhauModel;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import services.SQLConnection;

/**
 * lấy danhsach các hộ gia đình
 * @author Admin
 */
public class DanhSachHoGiaDinh {

    private ArrayList<GiaDinh> danhsach;

    public DanhSachHoGiaDinh() {
        danhsach = new ArrayList();
    }

    public ArrayList<GiaDinh> getDanhSachHoGiaDinh(String hokhauID) {
        try {
            danhsach.clear();
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select * from NHANKHAU,HOKHAU where NHANKHAU.HOKHAUID = HOKHAU.HOKHAUID and HOKHAU.HOKHAUID like '%"+hokhauID+"%'"
                    + "ORDER BY HOKHAU.HOKHAUID DESC,NHANKHAU.QUANHEVOICHUHO ASC");
            while (result.next()) {
                if (danhsach.size() == 0 || !danhsach.get(danhsach.size()-1).getHokhau().getHoKhauID().equalsIgnoreCase(result.getString("HOKHAUID"))) {
                    GiaDinh gd = new GiaDinh();
                    gd.getHokhau().setHoKhauID(result.getString("HOKHAUID"));
                    gd.getHokhau().setIdChuHo(result.getInt("IDCHUHO"));
                    gd.getHokhau().setDiaChi(result.getString("DIACHI"));
                    gd.getHokhau().setIdNguoiLap(result.getInt("IDNGUOILAP"));
                    gd.getHokhau().setNgayLap(result.getDate("NGAYLAP"));
                    gd.getHokhau().setIdNguoiXoa(result.getInt("IDNGUOIXOA"));
                    gd.getHokhau().setLyDoXoa(result.getString("LYDOXOA"));
                    gd.getHokhau().setNgayXoa(result.getDate("NGAYXOA"));
                    danhsach.add(gd);
                }
                NhanKhauModel nhankhau = new NhanKhauModel();
                nhankhau.setNhanKhauID(result.getInt("NHANKHAUID"));
                nhankhau.setHoKhauID(result.getString("HOKHAUID"));
                nhankhau.setQuanHeVoiChuHo(result.getString("QUANHEVOICHUHO"));
                nhankhau.setDanToc(result.getString("DANTOC"));
                nhankhau.setHoTen(result.getString("HOTEN"));
                nhankhau.setGioiTinh(result.getString("GIOITINH"));
                nhankhau.setDiaChiHienTai(result.getString("DIACHIHIENTAI"));
                nhankhau.setIdNguoiTao(result.getInt("IDNGUOITAO"));
                nhankhau.setIdNguoiXoa(result.getInt("IDNGUOIXOA"));
                nhankhau.setLyDoXoa(result.getString("LYDOXOA"));
                nhankhau.setNgayTao(result.getDate("NGAYTAO"));
                nhankhau.setNgayXoa(result.getDate("NGAYXOA"));
                nhankhau.setNgheNghiep(result.getString("NGHENGHIEP"));
                nhankhau.setNguyenQuan(result.getString("NGUYENQUAN"));
                nhankhau.setTonGiao(result.getString("TONGIAO"));
                nhankhau.setQuocTich(result.getString("QUOCTICH"));
                nhankhau.setNgaySinh(result.getDate("NGAYSINH"));
                nhankhau.setCMT(result.getInt("CMT"));
                nhankhau.setNoiLamViec(result.getString("NOILAMVIEC"));
                danhsach.get(danhsach.size()-1).addNhanKhau(nhankhau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsach;
    }
    
    
}
