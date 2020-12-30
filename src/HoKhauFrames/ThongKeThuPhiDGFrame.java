/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HoKhauFrames;

import Model.DG_HKModel;
import Model.DongGopModel;
import Support.DongGopSP;
import Support.GiaDinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import services.SQLConnection;

/**
 *
 * @author Admin
 */
public class ThongKeThuPhiDGFrame extends javax.swing.JFrame {

    /**
     * Creates new form ThongKeThuPhiDGFrame
     */
    private GiaDinh giadinh;
    private ArrayList<Integer> dsnam;
    private ArrayList<DongGopSP> donggop;
    private JTable table1, table2;
    private DefaultTableModel dt1, dt2;
    static final String[] COLUMNS1 = {"STT", "Năm đã đóng"};
    static final String[] COLUMNS2 = {"STT", "Mã đóng góp", "Tên đợt đóng góp", "Số tiền đóng góp"};

    public ThongKeThuPhiDGFrame(GiaDinh giadinh) {
        this.giadinh = giadinh;
        this.donggop = new ArrayList<>();
        this.dsnam = new ArrayList<>();

        initComponents();
        setView();
        setViewTable1();
        setViewTable2();
        this.setTitle("Thống kê thu phí, đóng góp");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void setView() {
        mahokhaulb.setText(giadinh.getHokhau().getHoKhauID());
        diachilb.setText(giadinh.getHokhau().getDiaChi());
        chuholb.setText(giadinh.getDanhsach().get(0).getHoTen());
        nguyenquanlb.setText(giadinh.getDanhsach().get(0).getNguyenQuan());
        ngaysinhlb.setText(giadinh.getDanhsach().get(0).getNgaySinh()+"");
    }

    public void getThuPhi() {
        try {
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "select NAM from KP_HK where HOKHAUID = '" + giadinh.getHokhau().getHoKhauID()+"'"
                    + "";
            ResultSet result = stm.executeQuery(s);
            while (result.next()) {
                dsnam.add(result.getInt("NAM"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewTable1() {
        getThuPhi();
        tablepanel1.removeAll();
        // currentPanel.setForeground(Color.black);
        tablepanel1.setLayout(new BorderLayout());
        //tablepanel.setPreferredSize(new Dimension(700, 0));
        dt1 = new DefaultTableModel();

        for (int i = 0; i < COLUMNS1.length; i++) {
            dt1.addColumn(COLUMNS1[i]);
        }
        this.table1 = new JTable(dt1);
        table1.setDefaultEditor(Object.class, null);
        table1.getTableHeader().setFont(new Font("NewellsHand", Font.BOLD, 16));
        table1.getTableHeader().setBackground(new Color(152, 251, 152));

        table1.setFont(new Font("MVBoli", Font.PLAIN, 16));
        table1.setRowHeight(40);
        table1.setSelectionBackground(Color.blue);
        table1.setSelectionForeground(Color.white);
        table1.isCellEditable(0, 0);

        JScrollPane sc = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablepanel1.add(sc);
        tablepanel1.validate();
        tablepanel1.repaint();
        //getDanhSach();
        for (int i = 0; i < dsnam.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(i + 1);
            vec.add(dsnam.get(i));
            dt1.addRow(vec);
        }
    }

    public void getdanhsach() {
        try {
            Connection conn = SQLConnection.getConnection();
            Statement stm = conn.createStatement();
            String s = "select * from DG_HK,DONGGOP WHERE DONGGOP.DONGOPID = DG_HK.DONGGOPID and DONGGOP.NAM = YEAR(GETDATE()) AND DG_HK.HOKHAUID = '" + giadinh.getHokhau().getHoKhauID() + "'";
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
                donggop.add(sp);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void setViewTable2() {
        getdanhsach();
        tablepanel2.removeAll();
        // currentPanel.setForeground(Color.black);
        tablepanel2.setLayout(new BorderLayout());
        //tablepanel.setPreferredSize(new Dimension(700, 0));
        dt2 = new DefaultTableModel();

        for (int i = 0; i < COLUMNS2.length; i++) {
            dt2.addColumn(COLUMNS2[i]);
        }
        this.table2 = new JTable(dt2);
        table2.setDefaultEditor(Object.class, null);
        table2.getTableHeader().setFont(new Font("NewellsHand", Font.BOLD, 16));
        table2.getTableHeader().setBackground(new Color(152, 251, 152));

        table2.setFont(new Font("MVBoli", Font.PLAIN, 16));
        table2.setRowHeight(40);
        table2.setSelectionBackground(Color.blue);
        table2.setSelectionForeground(Color.white);
        table2.isCellEditable(0, 0);

        JScrollPane sc = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablepanel2.add(sc);
        tablepanel2.validate();
        tablepanel2.repaint();
        //getDanhSach();
        for (int i = 0; i < donggop.size(); i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(i + 1);
            vec.add(donggop.get(i).getDg().getDongGopID());
            vec.add(donggop.get(i).getDotdonggop().getTenDotDongGop());
            vec.add(donggop.get(i).getDg().getSoTienDong());
            dt2.addRow(vec);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tablepanel1 = new javax.swing.JPanel();
        tablepanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mahokhaulb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        diachilb = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chuholb = new javax.swing.JLabel();
        nguyenquanlb = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ngaysinhlb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ THU PHÍ, ĐÓNG GÓP");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Thu phí vệ sinh:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Những năm đã đóng:");

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Đóng góp (năm hiện tại) :");

        javax.swing.GroupLayout tablepanel1Layout = new javax.swing.GroupLayout(tablepanel1);
        tablepanel1.setLayout(tablepanel1Layout);
        tablepanel1Layout.setHorizontalGroup(
            tablepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        tablepanel1Layout.setVerticalGroup(
            tablepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tablepanel2Layout = new javax.swing.GroupLayout(tablepanel2);
        tablepanel2.setLayout(tablepanel2Layout);
        tablepanel2Layout.setHorizontalGroup(
            tablepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablepanel2Layout.setVerticalGroup(
            tablepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Thông tin hộ gia đình:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Mã hộ khẩu:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Địa chỉ:");

        mahokhaulb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        diachilb.setEditable(false);
        diachilb.setColumns(20);
        diachilb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diachilb.setLineWrap(true);
        diachilb.setRows(5);
        diachilb.setWrapStyleWord(true);
        jScrollPane1.setViewportView(diachilb);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Chủ hộ:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("Nguyên quán:");

        chuholb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        nguyenquanlb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/unnamed2.jpg"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Ngày sinh:");

        ngaysinhlb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(534, 534, 534)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chuholb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nguyenquanlb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ngaysinhlb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGap(82, 82, 82)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tablepanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mahokhaulb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tablepanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mahokhaulb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chuholb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nguyenquanlb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngaysinhlb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablepanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tablepanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chuholb;
    private javax.swing.JTextArea diachilb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mahokhaulb;
    private javax.swing.JLabel ngaysinhlb;
    private javax.swing.JLabel nguyenquanlb;
    private javax.swing.JPanel tablepanel1;
    private javax.swing.JPanel tablepanel2;
    // End of variables declaration//GEN-END:variables
}
