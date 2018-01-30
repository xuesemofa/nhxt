package org.consume.com.exchange.model;

public class QueryModel {
    private String uuid;
    private String text;
    private String names;
    private boolean boo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public boolean isBoo() {
        return boo;
    }

    public void setBoo(boolean boo) {
        this.boo = boo;
    }

    public QueryModel() {
        super();
    }

    public QueryModel(String uuid, String text, String names, boolean boo) {
        this.uuid = uuid;
        this.text = text;
        this.names = names;
        this.boo = boo;
    }
}
