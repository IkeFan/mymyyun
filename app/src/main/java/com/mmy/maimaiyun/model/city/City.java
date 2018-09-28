package com.mmy.maimaiyun.model.city;

public class City {
	public String name;
	public String pinyi;
    public int cityID;

    public City(String name, String pinyi) {
        super();
        this.name = name;
        this.pinyi = pinyi;
    }

    public City(String name, String pinyi,int cityID) {
        super();
        this.name = name;
        this.pinyi = pinyi;
        this.cityID = cityID;
    }

    public City() {
        super();
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}

}
