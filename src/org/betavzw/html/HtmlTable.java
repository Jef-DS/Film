package org.betavzw.html;


import java.util.ArrayList;
import java.util.List;

public class HtmlTable {
    private String tableClass;
    private String headerRowClass;
    private String bodyRowClass;
    private String[] titels;
    private List<String[]> rijen = new ArrayList<>();

    public HtmlTable(String[] titels) {
        this.titels = titels.clone();
    }

    public String getTableClass() {
        return tableClass;
    }

    public void setTableClass(String tableClass) {
        this.tableClass = tableClass;
    }

    public String getHeaderRowClass() {
        return headerRowClass;
    }

    public void setHeaderRowClass(String headerRowClass) {
        this.headerRowClass = headerRowClass;
    }

    public String getBodyRowClass() {
        return bodyRowClass;
    }

    public void setBodyRowClass(String bodyRowClass) {
        this.bodyRowClass = bodyRowClass;
    }

    public void AddRow(String[] elementen) {
        rijen.add(elementen.clone());
    }

    public String getHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table ");
        sb.append(">\n");
        sb.append("<tr ");
        sb.append(">\n");
        for(String titel: titels) {
            sb.append("<th>");
            sb.append(titel);
            sb.append("</th>");
        }
        sb.append("</tr>\n");
        for(String[] rij : rijen) {
            sb.append("<tr ");
            sb.append(">");
            for(String el: rij){
                sb.append("<td>");
                sb.append(el);
                sb.append("</td>");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }
}

