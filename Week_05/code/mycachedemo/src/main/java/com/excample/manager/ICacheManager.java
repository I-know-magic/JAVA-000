package com.excample.manager;

public interface ICacheManager {
    public void expire(String key, long time);
    public void setObj(String key , Object o);
    public Object getObj(String key);
}
