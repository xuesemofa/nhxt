package org.consume.com.hrzrl.model;

public class HrzrlModel {
    private String names;
    private String acc_hea;
    private long dates;

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

    public HrzrlModel() {
        super();
    }

    public HrzrlModel(String names, String acc_hea) {
        this.names = names;
        this.acc_hea = acc_hea;
    }

    public HrzrlModel(String names, String acc_hea, long dates) {
        this.names = names;
        this.acc_hea = acc_hea;
        this.dates = dates;
    }
}
