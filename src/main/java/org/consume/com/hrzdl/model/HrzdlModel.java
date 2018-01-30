package org.consume.com.hrzdl.model;

public class HrzdlModel {
    private String names;
    private String acc_hea;
    private long dates;
    private String times;
    private String mj;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getAcc_hea() {
        return acc_hea;
    }

    public void setAcc_hea(String acc_hea) {
        this.acc_hea = acc_hea;
    }

    public long getDates() {
        return dates;
    }

    public void setDates(long dates) {
        this.dates = dates;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public HrzdlModel() {
        super();
    }

    public HrzdlModel(String names, String acc_hea, long dates, String times, String mj) {
        this.names = names;
        this.acc_hea = acc_hea;
        this.dates = dates;
        this.times = times;
        this.mj = mj;
    }

    @Override
    public String toString() {
        return "HrzdlModel{" +
                "names='" + names + '\'' +
                ", acc_hea='" + acc_hea + '\'' +
                ", dates=" + dates +
                ", times='" + times + '\'' +
                ", mj='" + mj + '\'' +
                '}';
    }
}
