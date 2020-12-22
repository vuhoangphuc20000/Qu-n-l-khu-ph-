/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.UserModel;
import Support.DanhMucSp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import services.SQLConnection;
import views.DongGopPanel;
import views.HoKhauManagePanel;
import views.HomePagePanel;
import views.NhanKhauManagePanel;
import views.ThongKePanel;
import views.ThuPhiManagePanel;

/**
 *
 * @author Admin
 */
public class MainController {

    private JPanel mainPanel;
    private JFrame mainFrame;
    
    // ghi nhận lại tên, panel, label của nút bấm chọn;
    private String namePanel;
    private JPanel xulyPanel=null;
    private JLabel xulyLabel=null;
    public UserModel user;
    private ArrayList<DanhMucSp> danhmuc;

    public MainController(JPanel mainPanel, JFrame mainFrame) { 
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        themNamThuPhi();
    }
    
    public void themNamThuPhi(){
        try{
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "select * from KHOANPHI where NAM = YEAR(GETDATE()) order by NAM DESC";
            ResultSet result = stm.executeQuery(s);
            if(!result.next())
            {
                s = "insert into KHOANPHI values(YEAR(GETDATE()))";
                int x = stm.executeUpdate(s);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setView(String namePanel, JPanel panel, JLabel lb) {
        // chuyển trạng thái của nút đã bấm về màu ban đầu
        if(this.xulyLabel != null && this.xulyPanel != null){ 
            this.xulyPanel.setBackground(new Color(102,102,102));
            this.xulyLabel.setBackground(new Color(102,102,102));
            this.xulyLabel.setForeground(Color.white);
        };
        
        //ghi nhận lại nút vừa bấm.
        this.namePanel = namePanel;
        this.xulyPanel = panel;
        this.xulyLabel = lb;
        
        //chuyển màu nút vừa bấm.
        panel.setBackground(new Color(155,251,152));
        lb.setBackground(new Color(155,251,152));
        lb.setForeground(Color.darkGray);
        
        JPanel view = new JPanel();
        
        switch (namePanel) {
            case ("Home"): {
                view = new HomePagePanel();
                break;
            }
            case ("NhanKhau"): {
                view = new NhanKhauManagePanel(this.mainFrame);
                break;
            }
            case ("HoKhau"): {
                view = new HoKhauManagePanel(this.mainFrame);
                break;
            }
            case("DongGop"):{
                view = new DongGopPanel();
                break;
            }
            case("ThongKe"):{
                view = new ThongKePanel();
                break;
            }
            case("ThuPhi"):{
                view = new ThuPhiManagePanel();
                break;
            }
        }
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(view);
        mainPanel.validate();
        mainPanel.repaint();
    }
    
    public void setEvent(ArrayList<DanhMucSp> danhmuc) {
        this.danhmuc = danhmuc;
        this.danhmuc.forEach((t) -> {
            t.getLabel().addMouseListener(new addEvent(t.getTenPanel(),t.getPanel(),t.getLabel()));
        });
    }
    
   //tự định nghĩa lại lắng nghe sự kiện chuột.
   class addEvent implements MouseListener {
   
        private String tenPanel;
        private JPanel panel;
        private JLabel label;

        public addEvent(String tenPanel, JPanel panel, JLabel label) {
            this.tenPanel = tenPanel;
            this.label = label;
            this.panel = panel;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
           setView(tenPanel,panel,label);
        }        

        @Override
        public void mousePressed(MouseEvent e) {
           // namePanel = tenPanel;
           // panel.setBackground(Color.BLACK);
            //label.setBackground(Color.BLACK);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
