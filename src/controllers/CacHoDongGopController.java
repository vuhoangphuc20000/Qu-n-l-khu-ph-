/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.DG_HKModel;
import Model.DongGopModel;
import Support.DanhSachHoGiaDinh;
import Support.DongGopSP;
import Support.GiaDinh;
import cacdotdonggopFrames.AddDongGopFrame;
import static controllers.DanhSachDongGopController.COLUMNS;
import danhsachdonggop.ChinhSuaDSDongGop;
import danhsachdonggop.XemChiTietDSDongGopFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import services.SQLConnection;

/**
 *
 * @author Admin
 */
public class CacHoDongGopController {

    private JPanel panel;
    private JTable table;
    private DefaultTableModel dt;
    static final String[] COLUMNS = {"STT", "Hộ khẩu ID", "Tên chủ hộ", "Tên đợt ủng hộ", "Số tiền ủng hộ"};
    private String searchname;
    private DanhSachHoGiaDinh laydanhsach;
    private ArrayList<GiaDinh> dsgiadinh;
    private ArrayList<DongGopModel> dsdonggop;
    private ArrayList<DongGopSP> danhsach;

    public CacHoDongGopController(JPanel panel) {
        this.panel = panel;
        this.laydanhsach = new DanhSachHoGiaDinh();
        this.searchname = "";
        this.dsgiadinh = laydanhsach.getDanhSachHoGiaDinh(this.searchname); // lấy tất cả các hộ về
        this.dsdonggop = new ArrayList<>();
        this.danhsach = new ArrayList<>();
        setView(); // lấy về danh sách các đợt đóng góp
    }

    public void setSearchName(String name) {
        this.searchname = name;
    }

    public void setView() {
        this.danhsach.clear();
        getdanhsach(searchname);
        panel.removeAll();
        // currentPanel.setForeground(Color.black);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(700, 0));
        dt = new DefaultTableModel();

        for (int i = 0; i < COLUMNS.length; i++) {
            dt.addColumn(COLUMNS[i]);
        }
        this.table = new JTable(dt);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setFont(new Font("NewellsHand", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(152, 251, 152));

        table.setFont(new Font("MVBoli", Font.PLAIN, 16));
        table.setRowHeight(40);
        table.setSelectionBackground(Color.blue);
        table.setSelectionForeground(Color.white);
        table.isCellEditable(0, 0);

        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(sc);
        panel.validate();
        panel.repaint();
        //getDanhSach();
        for (int i = 0; i < danhsach.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(i + 1);
            vec.add(danhsach.get(i).getGiadinh().getHokhau().getHoKhauID());
            vec.add(danhsach.get(i).getGiadinh().getDanhsach().get(0).getHoTen());
            vec.add(danhsach.get(i).getDotdonggop().getTenDotDongGop());
            vec.add(danhsach.get(i).getDg().getSoTienDong());
            dt.addRow(vec);
        }
    }

    public void getdanhsach(String name) {
        try {
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "select * from DG_HK,DONGGOP WHERE DONGGOP.DONGOPID = DG_HK.DONGGOPID and DONGGOP.NAM = 2020 AND HOKHAUID LIKE '%" + name + "%';";
            ResultSet result = stm.executeQuery(s);
            while (result.next()) {
                DG_HKModel dg = new DG_HKModel();
                dg.setDongGopID(result.getString("DONGGOPID"));
                dg.setHoKhauID(result.getString("HOKHAUID"));
                dg.setIdNguoiThu(1);
                dg.setNam(result.getInt("NAM"));
                dg.setNgayDong(result.getDate("NGAYDONG"));
                dg.setNguoiDong(result.getString("NGUOIDONG"));
                dg.setSoTienDong(result.getInt("SOTIENDONG"));
                DongGopModel dg1 = new DongGopModel();
                dg1.setDongGopID(dg.getDongGopID());
                dg1.setNam(dg.getNam());
                dg1.setTenDotDongGop(result.getString("TENDOTDONGGOP"));
                DongGopSP sp = new DongGopSP();
                sp.setDg(dg);
                sp.setDotdonggop(dg1);
                for (int i = 0; i < dsgiadinh.size(); i++) {
                    if (dsgiadinh.get(i).getHokhau().getHoKhauID().equals(dg.getHoKhauID())) {
                        sp.setGiadinh(dsgiadinh.get(i));
                        break;
                    }
                }
                danhsach.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDongGop(DG_HKModel dg) {
        try {
            Connection conn = SQLConnection.getConnection();
            String s = "delete from DG_HK where NAM = ? and HOKHAUID = ? and DONGGOPID = ?";
            PreparedStatement stm = conn.prepareStatement(s);
            stm.setInt(1, dg.getNam());
            stm.setString(2, dg.getHoKhauID());
            stm.setString(3, dg.getDongGopID());
            int x = stm.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!!");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xulyButton(String btnname) {
        if (btnname.equals("Chitiet")) {
            int x = table.getSelectedRow();
            XemChiTietDSDongGopFrame fr = new XemChiTietDSDongGopFrame(danhsach.get(x));
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (btnname.equals("Chinhsua")) {
            int x = table.getSelectedRow();
            ChinhSuaDSDongGop fr = new ChinhSuaDSDongGop(danhsach.get(x), this);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (btnname.equals("Xoa")) {
            if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa chứ???", "Warning!!", JOptionPane.YES_NO_OPTION) == 0) {
                int x = table.getSelectedRow();
                deleteDongGop(danhsach.get(x).getDg());
                setView();
            }
        }
    }
}
