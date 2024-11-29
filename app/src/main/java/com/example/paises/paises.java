package com.example.paises;

public class paises {
    private String name;
    private String flagUrl;

    public paises(String name, String flagUrl){
        this.name = name;
        this.flagUrl=flagUrl;
    }
    public String getName(){
        return name;
    }
    public String getflagUrl(){
        return flagUrl;
    }
}
