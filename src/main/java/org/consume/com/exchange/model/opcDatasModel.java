package org.consume.com.exchange.model;

public class opcDatasModel {
    private String names;
    private boolean choice;
    private String v;
    private Long dates;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Long getDates() {
        return dates;
    }

    public void setDates(Long dates) {
        this.dates = dates;
    }

    public opcDatasModel() {
        super();
    }

    public opcDatasModel(String names, boolean choice, String v, Long dates) {
        this.names = names;
        this.choice = choice;
        this.v = v;
        this.dates = dates;
    }
}
