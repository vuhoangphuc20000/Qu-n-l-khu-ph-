/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Model.NhanKhauModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import services.SQLConnection;

/**
 *
 * @author Admin
 */
public class DanhSachNhanKhau {
    public ArrayList<NhanKhauModel> danhsach;
    
    public DanhSachNhanKhau(){// trả về danh sách nhân khẩu nếu name = "" thì trả về toàn bộ nhân khẩu, còn name != "" thì  trả về có điều kiện
        this.danhsach = new ArrayList();
    }
    
    public ArrayList<NhanKhauModel> getDanhSach(String name){ //phục vụ cho cả search by name và không search by name
        try {
            danhsach.clear();
            System.out.println(name);
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery(
                    "select * from NHANKHAU where LYDOXOA IS NULL and HOTEN LIKE N'%"+name+"%' order by DIACHIHIENTAI"
            );
            while (result.next()) {
                System.out.println(result.getString("HOTEN"));
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
                danhsach.add(nhankhau);
            }
            conn.close();
            //System.out.println(danhsach.get(danhsach.size()).getHoTen());
            return danhsach;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
