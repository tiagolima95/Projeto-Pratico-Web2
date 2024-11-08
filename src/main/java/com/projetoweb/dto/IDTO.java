package com.projetoweb.dto;

import java.util.List;

public interface IDTO<T> {
	
	void save(T obj);
    void update(T obj);
    List<T> list();

}
