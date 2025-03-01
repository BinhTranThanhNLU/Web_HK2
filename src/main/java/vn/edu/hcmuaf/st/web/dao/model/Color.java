package vn.edu.hcmuaf.st.web.dao.model;

import java.io.Serializable;

public class Color implements Serializable {

    private int idColor;
    private String color;
    private String hexcode;

    public Color() {}

    public Color(int idColor, String color, String hexcode) {
        this.idColor = idColor;
        this.color = color;
        this.hexcode = hexcode;
    }

    //Get & Set

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHexcode() {
        return hexcode;
    }

    public void setHexcode(String hexcode) {
        this.hexcode = hexcode;
    }
}
