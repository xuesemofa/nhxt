package org.consume.com.gwgrzq.model;

import java.sql.Timestamp;

public class GwgrzqModel {
    private Timestamp strat_day;
    private Timestamp end_day;

    public Timestamp getStrat_day() {
        return strat_day;
    }

    public void setStrat_day(Timestamp strat_day) {
        this.strat_day = strat_day;
    }

    public Timestamp getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Timestamp end_day) {
        this.end_day = end_day;
    }

    public GwgrzqModel() {
        super();
    }

    public GwgrzqModel(Timestamp strat_day, Timestamp end_day) {
        this.strat_day = strat_day;
        this.end_day = end_day;
    }
}
