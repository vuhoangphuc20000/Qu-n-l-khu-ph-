/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class DanhMucSp {
    private String tenPanel;
    private JPanel panel;
    private JLabel label;

    public DanhMucSp(String tenPanel, JPanel panel, JLabel label) {
        this.tenPanel = tenPanel;
        this.panel = panel;
        this.label = label;
    }

    public String getTenPanel() {
        return tenPanel;
    }

    public void setTenPanel(String tenPanel) {
        this.tenPanel = tenPanel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

   
}
