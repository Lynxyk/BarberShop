package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StorageData {
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    private String column7;
    private String column8;
    private String column9;

    public StorageData(String column1, String column2) {
        this.column1 = column1;
        this.column2 = column2;
    }


    public StorageData(String column1, String column2, String column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public StorageData(String column1, String column2, String column3, String column4) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
    }

    public StorageData(String column1, String column2, String column3, String column4, String column5) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
    }

    public StorageData(String column1, String column2, String column3, String column4, String column5, String column6) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
    }

    public StorageData(String column1, String column2, String column3, String column4, String column5, String column6, String column7, String column8, String column9) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
        this.column7 = column7;
        this.column8 = column8;
        this.column9 = column9;
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

    public String getColumn4() {
        return column4;
    }

    public String getColumn5() {
        return column5;
    }

    public String getColumn6() {
        return column6;
    }

    public String getColumn7() {
        return column7;
    }

    public String getColumn8() {
        return column8;
    }

    public String getColumn9() {
        return column9;
    }

    public StringProperty column1Property() {
        return new SimpleStringProperty(column1);
    }

    public StringProperty column2Property() {
        return new SimpleStringProperty(column2);
    }

    public StringProperty column3Property() {
        return new SimpleStringProperty(column3);
    }

    public StringProperty column4Property() {
        return new SimpleStringProperty(column4);
    }

    public StringProperty column5Property() {
        return new SimpleStringProperty(column5);
    }

    public StringProperty column6Property() {
        return new SimpleStringProperty(column6);
    }

    public StringProperty column7Property() {
        return new SimpleStringProperty(column7);
    }

    public StringProperty column8Property() {
        return new SimpleStringProperty(column8);
    }

    public StringProperty column9Property() {
        return new SimpleStringProperty(column9);
    }


}
