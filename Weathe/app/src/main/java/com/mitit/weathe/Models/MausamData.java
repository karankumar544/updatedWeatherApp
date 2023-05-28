package com.mitit.weathe.Models;

import java.util.List;

public class MausamData {
     public List<weather> weather;
    public main main;
    public wind wind;
    public sys sys;
    public String name;

    public MausamData(List<com.mitit.weathe.Models.weather> weather,
                      com.mitit.weathe.Models.main main, com.mitit.weathe.Models.wind wind,
                      com.mitit.weathe.Models.sys sys, String name) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.sys = sys;
        this.name = name;
    }

    public void setWeather(List<com.mitit.weathe.Models.weather> weather) {
        this.weather = weather;
    }

    public void setMain(com.mitit.weathe.Models.main main) {
        this.main = main;
    }

    public void setWind(com.mitit.weathe.Models.wind wind) {
        this.wind = wind;
    }

    public void setSys(com.mitit.weathe.Models.sys sys) {
        this.sys = sys;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.mitit.weathe.Models.weather> getWeather() {
        return weather;
    }

    public com.mitit.weathe.Models.main getMain() {
        return main;
    }

    public com.mitit.weathe.Models.wind getWind() {
        return wind;
    }

    public com.mitit.weathe.Models.sys getSys() {
        return sys;
    }

    public String getName() {
        return name;
    }
}
