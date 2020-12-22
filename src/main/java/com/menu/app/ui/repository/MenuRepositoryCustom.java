package com.menu.app.ui.repository;

import java.util.List;

import com.menu.app.ui.model.response.Menu;

public interface MenuRepositoryCustom {
	
	List<Menu> getMenuFromDesc(String matchTxt);
	
	public void updateMenu(Menu menu);

}
