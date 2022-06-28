package com.example.ctaaoop;

public class student {
    String usn;
    String name;
    String branch;
    String acyear;
    int ia1;
    int ia2;
    int ia3;
    int cta;
    int cie;

    public student(String usn, String name, String branch, String acyear, int ia1, int ia2, int ia3, int cta, int cie) {
        this.usn = usn;
        this.name = name;
        this.branch = branch;
        this.acyear = acyear;
        this.ia1 = ia1;
        this.ia2 = ia2;
        this.ia3 = ia3;
        this.cta = cta;
        this.cie = cie;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAcyear() {
        return acyear;
    }

    public void setAcyear(String acyear) {
        this.acyear = acyear;
    }

    public int getIa1() {
        return ia1;
    }

    public void setIa1(int ia1) {
        this.ia1 = ia1;
    }

    public int getIa2() {
        return ia2;
    }

    public void setIa2(int ia2) {
        this.ia2 = ia2;
    }

    public int getIa3() {
        return ia3;
    }

    public void setIa3(int ia3) {
        this.ia3 = ia3;
    }

    public int getCta() {
        return cta;
    }

    public void setCta(int cta) {
        this.cta = cta;
    }

    public int getCie() {
        return cie;
    }

    public void setCie(int cie) {
        this.cie = cie;
    }
}
