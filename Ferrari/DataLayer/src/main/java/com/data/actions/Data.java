package com.data.actions;
// anders
//crud interface, could be seperated for each ServiceType
public interface Data <T, U>{
    public T create(U parameter);
    public T read(U parameter);
    public T readAll(U parameter);
    public T update(U parameter);
    public T delete(U parameter);
    
}
