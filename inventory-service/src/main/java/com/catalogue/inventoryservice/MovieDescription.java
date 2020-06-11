package com.catalogue.inventoryservice;



public class MovieDescription {
    private String id;
    private String desc;
    public String getId(){
        return this.id;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public void setId(String id){
        this.id = id;
    }
    public MovieDescription(String id,String desc){
        this.id = id ;
        this.desc = desc;
    }
    public MovieDescription(){

    }


}
