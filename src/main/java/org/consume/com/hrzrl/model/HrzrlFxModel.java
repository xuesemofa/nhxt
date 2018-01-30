package org.consume.com.hrzrl.model;


public class HrzrlFxModel {
	//换热站名称
    private String hrzmc;
    //换热站面积
    private String hrzmj;
    //热单耗
    private String hrzrdh;
    //指标
    private String hrzrzb;
    private String ywljrl;
    private Double wd;
    private String sj;
    private String cb;

    public String getHrzmc() {
        return hrzmc;
    }

    public void setHrzmc(String hrzmc) {
        this.hrzmc = hrzmc;
    }

    public String getHrzmj() {
        return hrzmj;
    }

    public void setHrzmj(String hrzmj) {
        this.hrzmj = hrzmj;
    }

    public String getHrzrdh() {
        return hrzrdh;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public void setHrzrdh(String hrzrdh) {
        if(hrzrdh!=null&&!"".equals(hrzrdh))
        {
            if (hrzrdh.contains(".")) {
                int i = hrzrdh.indexOf(".") + 3;
                if (i <= hrzrdh.length()){
                    hrzrdh = hrzrdh.substring(0,i);
                }else{
                    hrzrdh += "0";
                }
            }else{
                hrzrdh += ".00";
            }
        }else {
            hrzrdh = "0.00";
        }
        this.hrzrdh = hrzrdh;
    }

    public String getHrzrzb() {
        return hrzrzb;
    }

    public void setHrzrzb(String hrzrzb) {
        if(hrzrzb!=null&&!"".equals(hrzrzb))
        {
            if (hrzrzb.contains(".")) {
                int i = hrzrzb.indexOf(".") + 3;
                if (i <= hrzrzb.length()){
                    hrzrzb = hrzrzb.substring(0,i);
                }else{
                    hrzrzb += "0";
                }
            }else{
                hrzrzb += ".00";
            }
        }else {
            hrzrzb = "0.00";
        }
        this.hrzrzb = hrzrzb;
    }

    public String getYwljrl() {
        return ywljrl;
    }

    public void setYwljrl(String ywljrl) {
        this.ywljrl = ywljrl;
    }

    public Double getWd() {
        return wd;
    }

    public void setWd(Double wd) {
        this.wd = wd;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public HrzrlFxModel() {
        super();
    }

    public HrzrlFxModel(String hrzmc, String hrzmj, String hrzrdh, String hrzrzb, String ywljrl) {
        this.hrzmc = hrzmc;
        this.hrzmj = hrzmj;
        this.hrzrdh = hrzrdh;
        this.hrzrzb = hrzrzb;
        this.ywljrl = ywljrl;
    }

    public HrzrlFxModel(String hrzmc, String hrzmj, String hrzrdh, String hrzrzb, String ywljrl, Double wd) {
        this.hrzmc = hrzmc;
        this.hrzmj = hrzmj;
        this.hrzrdh = hrzrdh;
        this.hrzrzb = hrzrzb;
        this.ywljrl = ywljrl;
        this.wd = wd;
    }

    public HrzrlFxModel(String hrzmc, String hrzmj, String hrzrdh, String hrzrzb, String ywljrl, Double wd, String sj) {
        this.hrzmc = hrzmc;
        this.hrzmj = hrzmj;
        this.hrzrdh = hrzrdh;
        this.hrzrzb = hrzrzb;
        this.ywljrl = ywljrl;
        this.wd = wd;
        this.sj = sj;
    }

    public HrzrlFxModel(String hrzmc, String hrzmj, String hrzrdh, String hrzrzb, String ywljrl, Double wd, String sj, String cb) {
        this.hrzmc = hrzmc;
        this.hrzmj = hrzmj;
        this.hrzrdh = hrzrdh;
        this.hrzrzb = hrzrzb;
        this.ywljrl = ywljrl;
        this.wd = wd;
        this.sj = sj;
        this.cb = cb;
    }
}
