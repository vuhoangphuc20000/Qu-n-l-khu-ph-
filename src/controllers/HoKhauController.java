/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import HoKhauFrames.AddHoKhauFrame;
import HoKhauFrames.ChiTietHoKhauFrame;
import HoKhauFrames.ThongKeThuPhiDGFrame;
import Model.NhanKhauModel;
import NhanKhauFrames.AddNewPeopleJFrame;
import Support.DanhSachHoGiaDinh;
import Support.GiaDinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import services.DongGopServiceFrame;
import services.ThuPhiFrames;

/**
 *
 * @author Admin
 */
public class HoKhauController {

    private JPanel pn;
    private JTable table;
    private DefaultTableModel dt;
    private DanhSachHoGiaDinh danhsach;
    private ArrayList<GiaDinh> dsgiadinh;
    private String search;
    private final String[] COLUMNS = {"Hộ khẩu ID", "Tên chủ hộ", "Địa chỉ"};

    public HoKhauController(JPanel pn) {
        this.pn = pn;
        this.danhsach = new DanhSachHoGiaDinh();
        this.dsgiadinh = danhsach.getDanhSachHoGiaDinh("");
        taoBang();
    }

    public void setSeach(String search) {
        this.search = search;
    }

    public void taoBang() {
        dt = new DefaultTableModel();
        for (int i = 0; i < 3; i++) {
            dt.addColumn(COLUMNS[i]);
        }
        table = new JTable(dt);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setFont(new Font("NewellsHand", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(152, 251, 152));

        table.setFont(new Font("MVBoli", Font.PLAIN, 16));
        table.setRowHeight(40);
        table.setSelectionBackground(Color.blue);
        table.setSelectionForeground(Color.white);

        if (this.dsgiadinh != null) {
            this.dsgiadinh.clear();
        }
        this.dsgiadinh = danhsach.getDanhSachHoGiaDinh(search);

        for (int i = 0; i < dsgiadinh.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(dsgiadinh.get(i).getHokhau().getHoKhauID());
            vec.add(dsgiadinh.get(i).getDanhsach().get(0).getHoTen());
            vec.add(dsgiadinh.get(i).getHokhau().getDiaChi());
            dt.addRow(vec);
        }
        JScrollPane sc = new JScrollPane(table);

        pn.removeAll();
        pn.setLayout(new BorderLayout());
        pn.add(sc);
        pn.validate();
        pn.repaint();
    }

    public void xuLyButton(String namebtn) {
        if (namebtn.equalsIgnoreCase("Add")) {
            AddHoKhauFrame fr = new AddHoKhauFrame(this);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (namebtn.equalsIgnoreCase("Chitiet")) {
            int x = table.getSelectedRow();
            ChiTietHoKhauFrame fr = new ChiTietHoKhauFrame(dsgiadinh.get(x));
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (namebtn.equalsIgnoreCase("Xoa")) {
            if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa chứ???", "Warning!!", JOptionPane.YES_NO_OPTION) == 0) {
                int x = table.getSelectedRow();
                dsgiadinh.get(x).deleteGiaDinh();
                taoBang();
            };

        }
        if (namebtn.equalsIgnoreCase("NopPhiVS")) {
            int x = table.getSelectedRow();
            ThuPhiFrames fr = new ThuPhiFrames(dsgiadinh.get(x));
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (namebtn.equalsIgnoreCase("DongGop")) {
            int x = table.getSelectedRow();
            DongGopServiceFrame fr = new DongGopServiceFrame(dsgiadinh.get(x));
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (namebtn.equalsIgnoreCase("Thongke")) {
            int x = table.getSelectedRow();
            ThongKeThuPhiDGFrame fr = new ThongKeThuPhiDGFrame(dsgiadinh.get(x));
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
    }
}
