package com.example.jose.todo;

/**
 * Created by jose on 3/13/2015.
 */
public class Details {
    private int Did ;
    private String Dtitle ;
    private String Ddesc;
    private String Ddate;
    private int Dstatus ;

    public Details() {
    }

    public Details(String dtitle, String ddesc, String ddate) {
        Dtitle = dtitle;
        Ddesc = ddesc;
        Ddate = ddate;
    }

    public String getDtitle() {
        return Dtitle;
    }

    public void setDtitle(String dtitle) {
        Dtitle = dtitle;
    }

    public String getDdesc() {
        return Ddesc;
    }

    public void setDdesc(String ddesc) {
        Ddesc = ddesc;
    }

    public String getDdate() {
        return Ddate;
    }

    public void setDdate(String ddate) {
        Ddate = ddate;
    }
}
