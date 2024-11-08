package com.projetoweb.dto;

import java.util.List;

public interface IDTO<T> {
	
	void save(T obj);
	List<T> list();
    void update(T obj);
    void delete(int id);

}
