/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.NhanKhauModel;
import NhanKhauFrames.AddNewPeopleJFrame;
import NhanKhauFrames.ChinhSuaPeopleJFrame;
import NhanKhauFrames.DeleteNhanKhauJFrame;
import NhanKhauFrames.XemChiTietFrame;
import Support.DanhSachHoGiaDinh;
import Support.DanhSachNhanKhau;
import Support.GiaDinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class NhanKhauController {

    private JFrame parentFrame;
    private JPanel currentPanel;
    private JTable table;
    private DefaultTableModel dm;
    public ArrayList<NhanKhauModel> danhsach;
    private final String[] COLUMNS = {"STT", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ"};
    private ArrayList<GiaDinh> dsgiadinh;
    private DanhSachNhanKhau ds;
    private String searchbyname;
    
    public NhanKhauController(JPanel currentPanel, JFrame parentFrame) {

        this.ds = new DanhSachNhanKhau();
        this.parentFrame = parentFrame;
        this.currentPanel = currentPanel;
        this.dsgiadinh = new DanhSachHoGiaDinh().getDanhSachHoGiaDinh(""); // lấy danh sách tất cả các hộ gia đình
        this.danhsach = new ArrayList();
        this.searchbyname = "";
        setViewTable();
    }

    public void setSeachbyname(String s){
        this.searchbyname  = s;
    }
    
    public void setViewTable() { //mỗi lần gọi setViewTable là có sự thay đổi trong danhsach
        this.danhsach.clear();
        this.danhsach = ds.getDanhSach(this.searchbyname);
        
        currentPanel.removeAll();
        // currentPanel.setForeground(Color.black);
        currentPanel.setLayout(new BorderLayout());
        currentPanel.setPreferredSize(new Dimension(700, 0));
        dm = new DefaultTableModel();

        for (int i = 0; i < COLUMNS.length; i++) {
            dm.addColumn(COLUMNS[i]);
        }
        this.table = new JTable(dm);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setFont(new Font("NewellsHand", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(152, 251, 152));

        table.setFont(new Font("MVBoli", Font.PLAIN, 16));
        table.setRowHeight(40);
        table.setSelectionBackground(Color.blue);
        table.setSelectionForeground(Color.white);
        table.isCellEditable(0, 0);
        
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentPanel.add(sc);
        currentPanel.validate();
        currentPanel.repaint();
        //getDanhSach();
        for (int i = 0; i < danhsach.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(i+1);
            vec.add(danhsach.get(i).getHoTen());
            vec.add(danhsach.get(i).getNgaySinh());
            vec.add(danhsach.get(i).getGioiTinh());
            vec.add(danhsach.get(i).getDiaChiHienTai());
            dm.addRow(vec);
        }
    }


    public void xulyButton(String kind) {
        if (kind.equalsIgnoreCase("Khai Tu")) {
            int x = table.getSelectedRow();
            DeleteNhanKhauJFrame fr = new DeleteNhanKhauJFrame(danhsach.get(x), this);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (kind.equalsIgnoreCase("Add")) {
            AddNewPeopleJFrame fr = new AddNewPeopleJFrame(this);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if (kind.equalsIgnoreCase("Chinh Sua")) {
            int x = table.getSelectedRow();
            ChinhSuaPeopleJFrame chinhsua = new ChinhSuaPeopleJFrame(this, danhsach.get(x), x);
            chinhsua.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            chinhsua.setLocationRelativeTo(null);
            chinhsua.setVisible(true);
        }
        if(kind.equalsIgnoreCase("Chi tiet")){
            int x = table.getSelectedRow();
            int y = -1;
            for(int i = 0 ;i < dsgiadinh.size(); i++){
                if(dsgiadinh.get(i).getHokhau().getHoKhauID().equalsIgnoreCase(danhsach.get(x).getHoKhauID())){
                    y = i;
                    break;
                }
            }
            XemChiTietFrame fr;
            if(y != -1)fr = new XemChiTietFrame(danhsach.get(x),dsgiadinh.get(y));
            else fr = new XemChiTietFrame(danhsach.get(x), null);
            fr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
    }
}
