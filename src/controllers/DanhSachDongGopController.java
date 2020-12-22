/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.DongGopModel;
import Support.DanhMucSp;
import cacdotdonggopFrames.AddDongGopFrame;
import cacdotdonggopFrames.ThongKeDongGopFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
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
public class DanhSachDongGopController {

    private JPanel panel;
    private JTable table;
    private DefaultTableModel dt;
    private ArrayList<DongGopModel> danhsach;
    private String searchname;

    static final String[] COLUMNS = {"STT", "Mã đóng góp", "Tên đợt đóng góp", "Năm"};
    private CacHoDongGopController nextController;
    public DanhSachDongGopController(JPanel panel, CacHoDongGopController nextController) {
        this.nextController = nextController;
        this.panel = panel;
        this.searchname = "";
        this.danhsach = new ArrayList<>();
        setView();
    }

    public void setSearchName(String name) {
        this.searchname = name;
    }

    public void setView() {
        this.danhsach.clear();
        getDanhSach(searchname);
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
            vec.add(danhsach.get(i).getDongGopID());
            vec.add(danhsach.get(i).getTenDotDongGop());
            vec.add(danhsach.get(i).getNam());
            dt.addRow(vec);
        }
    }

    public void getDanhSach(String name) {
        try {
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "select * from DONGGOP where TENDOTDONGGOP like N'%" + name + "%'";
            ResultSet result = stm.executeQuery(s);
            while (result.next()) {
                DongGopModel dg = new DongGopModel();
                dg.setDongGopID(result.getString("DONGOPID"));
                dg.setTenDotDongGop(result.getString("TENDOTDONGGOP"));
                dg.setNam(result.getInt("NAM"));
                danhsach.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDotDongGop(DongGopModel donggop) {
        try {
            Connection conn = SQLConnection.getConnection();
            String s = "delete from DONGGOP where DONGOPID = ? AND NAM = ?";
            String s2 = "DELETE FROM DG_HK WHERE DONGGOPID = ? AND NAM = ?";
            PreparedStatement stm = conn.prepareStatement(s);
            stm.setString(1, donggop.getDongGopID());
            stm.setInt(2, donggop.getNam());
            int x = stm.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!!");
            }
            stm = conn.prepareStatement(s2);
            stm.setString(1, donggop.getDongGopID());
            stm.setInt(2, donggop.getNam());
            x = stm.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xulyButton(String btnname) {
        if (btnname.equals("Add")) {
            AddDongGopFrame fr = new AddDongGopFrame(this);
            fr.setLocationRelativeTo(null);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setVisible(true);
        }
        if (btnname.equals("Thongke")) {
            int x = table.getSelectedRow();
            ThongKeDongGopFrame fr = new ThongKeDongGopFrame(danhsach.get(x));
            fr.setLocationRelativeTo(null);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setVisible(true);
        }
        if (btnname.equals("Xoa")) {
            int x = table.getSelectedRow();
            deleteDotDongGop(danhsach.get(x));
            setView();
            nextController.setView();
        }
    }
}
