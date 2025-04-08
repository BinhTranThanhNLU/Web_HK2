package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;

public class Address implements Serializable
{
    private int idAddress;
    private User idUser;
    private String address;
    private String ward;
    private String district;
    private String province;
    private boolean isDefault;

    public Address() {
    }

    public Address(boolean isDefault, String province, String district, String ward, String address, User idUser, int idAddress) {
        this.isDefault = isDefault;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.idUser = idUser;
        this.idAddress = idAddress;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "idAddress=" + idAddress +
                ", idUser=" + idUser +
                ", address='" + address + '\'' +
                ", ward='" + ward + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}

