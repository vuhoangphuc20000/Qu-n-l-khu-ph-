/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Model.DG_HKModel;
import Model.DongGopModel;

/**
 *
 * @author Admin
 */
public class DongGopSP {
    private GiaDinh giadinh;
    private DG_HKModel dg;
    private DongGopModel dotdonggop;

    public DongGopSP(){
        this.giadinh = new GiaDinh();
        this.dg = new DG_HKModel();
        this.dotdonggop = new DongGopModel();
    }
    public GiaDinh getGiadinh() {
        return giadinh;
    }

    public void setGiadinh(GiaDinh giadinh) {
        this.giadinh = giadinh;
    }

    public DG_HKModel getDg() {
        return dg;
    }

    public void setDg(DG_HKModel dg) {
        this.dg = dg;
    }

    public DongGopModel getDotdonggop() {
        return dotdonggop;
    }

    public void setDotdonggop(DongGopModel dotdonggop) {
        this.dotdonggop = dotdonggop;
    }
    
}
