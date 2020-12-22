/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HoKhauFrames;

import Model.NhanKhauModel;
import Support.DanhSachNhanKhau;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import services.SQLConnection;

/**
 * không hẳn là 1 frame chọn chủ hộ mà chọn cả thành viên trong gia đình luôn!!
 * @author Admin
 */
public class ChonChuHoFrame extends javax.swing.JFrame {

    /**
     * Creates new form ChonChuHoFrame
     */
    private AddHoKhauFrame parentFrame;
    private DefaultTableModel dt;
    private JTable table;
    static final String[] COLUMNS = {"Họ và tên","Ngày sinh","Giới tính","Nguyên quán"};
    private DanhSachNhanKhau nhankhaus; //danh sách các nhân khẩu
    private String namebtn;
    
    public ChonChuHoFrame(AddHoKhauFrame parentFrame, String namebtn) {
        this.parentFrame = parentFrame;
        this.namebtn = namebtn;
        nhankhaus = new DanhSachNhanKhau();
        initComponents();
        searchChuHo(jtfSearch.getText());
         this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close??", "Warning!!", JOptionPane.YES_NO_OPTION) == 0) {
                    dispose();
                };
            }
        });
    }
    
    public void setViewTable() { // hiển thị bảng để người dùng chọn
        tablepn.removeAll();
        // currentPanel.setForeground(Color.black);
        tablepn.setLayout(new BorderLayout());
        tablepn.setPreferredSize(new Dimension(700, 0));
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
        tablepn.add(sc);
        tablepn.validate();
        tablepn.repaint();
        for (int i = 0; i < nhankhaus.danhsach.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(nhankhaus.danhsach.get(i).getHoTen());
            vec.add(nhankhaus.danhsach.get(i).getNgaySinh());
            vec.add(nhankhaus.danhsach.get(i).getGioiTinh());
            vec.add(nhankhaus.danhsach.get(i).getDiaChiHienTai());
            dt.addRow(vec);
        }
    }
    
    public void searchChuHo(String name){ //tìm kiếm thành viên chứ không riêng chủ hộ, mới đầu search textbox = "" nên trả về toàn bộ nhân khẩu
        nhankhaus.getDanhSach(name);
        setViewTable();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablepn = new javax.swing.JPanel();
        jtfSearch = new javax.swing.JTextField();
        searchlb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout tablepnLayout = new javax.swing.GroupLayout(tablepn);
        tablepn.setLayout(tablepnLayout);
        tablepnLayout.setHorizontalGroup(
            tablepnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablepnLayout.setVerticalGroup(
            tablepnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        jtfSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchActionPerformed(evt);
            }
        });
        jtfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfSearchKeyPressed(evt);
            }
        });

        searchlb.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        searchlb.setText("Search by name:");
        searchlb.setToolTipText("");

        jButton1.setText("Hủy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Chọn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchlb, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 449, Short.MAX_VALUE))
                    .addComponent(tablepn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchlb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablepn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchActionPerformed

    private void jtfSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10){
            searchChuHo(jtfSearch.getText());
            //JOptionPane.showMessageDialog(null, "Press enter");
        }
    }//GEN-LAST:event_jtfSearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int x = table.getSelectedRow();
        if(nhankhaus.danhsach.get(x).getHoKhauID() != null) JOptionPane.showMessageDialog(null, "Nhân khẩu đã nằm trong hộ khác");
        else {
            if(this.namebtn.equalsIgnoreCase("chuho")){
                nhankhaus.danhsach.get(x).setQuanHeVoiChuHo("Chủ hộ");
                parentFrame.setChuHo(nhankhaus.danhsach.get(x));
                parentFrame.setViewChuHo();
                dispose();}
            else{
                parentFrame.addThanhVien(nhankhaus.danhsach.get(x));
                parentFrame.setViewTableThanhVien();
                dispose();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JLabel searchlb;
    private javax.swing.JPanel tablepn;
    // End of variables declaration//GEN-END:variables
}
