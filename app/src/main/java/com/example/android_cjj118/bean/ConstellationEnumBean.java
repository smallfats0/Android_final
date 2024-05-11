package com.example.android_cjj118.bean;


import com.example.android_cjj118.R;

public enum ConstellationEnumBean {
    Aries(0,"白羊座", R.drawable.aries),
    Taurus(1,"金牛座",R.drawable.taurus),
    Gemini(2,"双子座",R.drawable.gemini),
    Cancer(3,"巨蟹座",R.drawable.cancer),
    Leo(4,"狮子座",R.drawable.leo),
    Virgo(5,"处女座",R.drawable.virgo),
    Libra(6,"天枰座",R.drawable.libra),
    Scorpio(7,"天蝎座",R.drawable.scorpio),
    Sagittarius(8,"射手座",R.drawable.sagittarius),
    Capricorn(9,"摩羯座",R.drawable.capricorn),
    Aquarius(10,"水瓶座",R.drawable.aquarius),
    Pisces(11,"双鱼座",R.drawable.pisces);

    private int constellationID;//星座ID
    private String name;//星座名称
    private int imageID;//图像ID

    private ConstellationEnumBean(int constellationID, String name, int imageID) {
        this.constellationID = constellationID;
        this.name = name;
        this.imageID = imageID;
    }

    public int getConstellationID() {
        return constellationID;
    }

    public void setConstellationID(int constellationID) {
        this.constellationID = constellationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
