package com.farmhike.mokisan.Models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("type_home")
    @Expose
    private TypeHome typeHome;
    @SerializedName("type_business")
    @Expose
    private TypeBusiness typeBusiness;
    @SerializedName("primary")
    @Expose
    private String primary;

    public TypeHome getTypeHome() {
        return typeHome;
    }

    public void setTypeHome(TypeHome typeHome) {
        this.typeHome = typeHome;
    }

    public TypeBusiness getTypeBusiness() {
        return typeBusiness;
    }

    public void setTypeBusiness(TypeBusiness typeBusiness) {
        this.typeBusiness = typeBusiness;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

}




