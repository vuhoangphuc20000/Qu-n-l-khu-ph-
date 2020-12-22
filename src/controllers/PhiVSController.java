/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.KP_HKModel;
import QLThuPhiFrames.ChinhSuaPhiVSFrame;
import QLThuPhiFrames.ThongKeThuPhiFrame;
import QLThuPhiFrames.XemChiTietPhiVSFrame;
import Support.DanhSachHoGiaDinh;
import Support.GiaDinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import services.SQLConnection;

/**
 *
 * @author Admin
 */
public class PhiVSController {
    private JPanel tablePanel;
    private ArrayList<KP_HKModel> danhsachdongphi;
    private DanhSachHoGiaDinh laydanhsachhogiadinh;
    private ArrayList<GiaDinh> dsgiadinh;
    private DefaultTableModel dt;
    private JTable table;
    static final String[] COLUMNS = {"Năm","Hộ khẩu ID","Chủ hộ","Địa chỉ","Ngày đóng"};
    private String nam;
    
    public PhiVSController(JPanel tablePanel){
        this.tablePanel = tablePanel;
        this.danhsachdongphi = new ArrayList();
        this.nam = "";
        laydanhsachhogiadinh = new DanhSachHoGiaDinh();
        dsgiadinh = laydanhsachhogiadinh.getDanhSachHoGiaDinh(""); // lấy thông tin các hộ về luôn
      //  for(int i = 0; i < dsgiadinh.size();i++)System.out.println(dsgiadinh.get(i).getHokhau().getHoKhauID());
        setTable();
    }
    
    public void setNam(String nam){
        this.nam = nam;
    }
    
    public void getDanhSachSQL(String nam){ // lấy danh sách thu phí
        try{
            Connection conn = SQLConnection.getConnection();
            String s = "select * from KP_HK where NAM like '%"+nam+"%'order by NAM DESC, HOKHAUID DESC";
           // System.out.println(s);
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery(s);
            while(result.next()){
                KP_HKModel a = new KP_HKModel();
                a.setNam(result.getInt("NAM"));
                a.setDaDong(result.getInt("DADONG"));
                a.setHoKhauID(result.getString("HOKHAUID"));
                a.setIdNguoiThu(result.getInt("IDNGUOITHU"));
                a.setNgayDong(result.getDate("NGAYDONG"));
                a.setNguoiDong(result.getString("NGUOIDONG"));
                danhsachdongphi.add(a);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void setTable(){
        this.danhsachdongphi.clear();
        getDanhSachSQL(this.nam);
        
        tablePanel.removeAll();
        // currentPanel.setForeground(Color.black);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(700, 0));
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
        tablePanel.add(sc);
        tablePanel.validate();
        tablePanel.repaint();
        //getDanhSach();
        for (int i = 0; i < danhsachdongphi.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(danhsachdongphi.get(i).getNam());
            vec.add(danhsachdongphi.get(i).getHoKhauID());
           // System.out.println(danhsachdongphi.get(i).getHoKhauID());
            for(int j = dsgiadinh.size()-1; j >= 0; j--){
                if(danhsachdongphi.get(i).getHoKhauID().equals(dsgiadinh.get(j).getHokhau().getHoKhauID())){
                    System.out.println(dsgiadinh.get(j).getHokhau().getHoKhauID());
                    vec.add(dsgiadinh.get(j).getDanhsach().get(0).getHoTen());
                    vec.add(dsgiadinh.get(j).getHokhau().getDiaChi());
                    break;
                }
                
            }
            vec.add(danhsachdongphi.get(i).getNgayDong());
            dt.addRow(vec);
        }
    }
    
    public void deleteBill(KP_HKModel bill){
        try{
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "delete from KP_HK where NAM = "+bill.getNam()+"AND HOKHAUID = '"+bill.getHoKhauID()+"'";
            int x = stm.executeUpdate(s);
            if(x > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công");
                setTable();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void xulyButton(String name){
        if(name.equals("ChiTiet")){
            int x = table.getSelectedRow();
            int y = 0;
            for(int i = 0; i < dsgiadinh.size(); i++) if(danhsachdongphi.get(x).getHoKhauID().equals(dsgiadinh.get(i).getHokhau().getHoKhauID())){
                y = i;
                break;
            }
            System.out.println(x +" "+ danhsachdongphi.get(x).getHoKhauID());
            System.out.println(y+ " " +dsgiadinh.get(y).getHokhau().getHoKhauID());

            XemChiTietPhiVSFrame fr = new XemChiTietPhiVSFrame(dsgiadinh.get(y),danhsachdongphi.get(x));
            fr.setLocationRelativeTo(null);
            fr.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            fr.setVisible(true);
        }
        if(name.equals("delete")){
            int x = table.getSelectedRow();
            deleteBill(danhsachdongphi.get(x));
        }
        if(name.equals("Thongke")){
            ThongKeThuPhiFrame fr = new ThongKeThuPhiFrame();
            fr.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
        if(name.equals("Chinhsua")){
            int x = table.getSelectedRow();
            int y = 0;
            for(int i = 0; i < dsgiadinh.size(); i++) if(danhsachdongphi.get(x).getHoKhauID().equals(dsgiadinh.get(i).getHokhau().getHoKhauID())){
                y = i;
                break;
            }
            ChinhSuaPhiVSFrame fr = new ChinhSuaPhiVSFrame(dsgiadinh.get(y),danhsachdongphi.get(x),this);
            fr.setLocationRelativeTo(null);
            fr.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            fr.setVisible(true);
        }
    }
}
