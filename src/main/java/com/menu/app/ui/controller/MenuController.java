package com.menu.app.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.app.exception.MenuAppException;
import com.menu.app.ui.model.request.MenuRequest;
import com.menu.app.ui.model.request.MenuUpdateRequest;
import com.menu.app.ui.model.response.Menu;
import com.menu.app.ui.service.MenuService;
import java.util.Optional;

@RestController
@RequestMapping("menus")
public class MenuController {

	@Autowired
	MenuService mnuService;
	
	private List<Menu> mnuDtls;

	@GetMapping
	public List<Menu> getAllMenu() throws MenuAppException {

		mnuDtls = mnuService.getAllMenu();
		if (mnuDtls == null || mnuDtls.isEmpty()) {
			throw new MenuAppException("No Data Found");
		}
		return mnuDtls;
	}

	@GetMapping(path = "/{searchString}")
	public List<Menu> getMenu(@PathVariable String searchString) throws MenuAppException {
		mnuDtls = mnuService.getMenuFromDesc(searchString);

		if (mnuDtls == null || mnuDtls.isEmpty()) {
			throw new MenuAppException("No Match Found");
		}
		return mnuDtls;
	}

	@PostMapping
	public List<Menu> createMenu(@RequestBody MenuRequest menuRequest) {
		try {

			Menu newMenu = new Menu();
			newMenu.setId(menuRequest.getMenuId());
			newMenu.setDescription(menuRequest.getMenuDesc());

			mnuService.AddMenu(newMenu);

			mnuDtls = mnuService.getAllMenu();

		} catch (Exception exp) {
			throw new MenuAppException("Error to add a new Menu");
		}
		return mnuDtls;

	}

	@PutMapping(path = "/{menuId}")
	public List<Menu> updateMenu(@PathVariable String menuId, @RequestBody MenuUpdateRequest menuUpdRequest) {
		try {
			Optional<Menu> menu = mnuService.getMenuById(menuId);
			if (menu.get() != null) {
				menu.get().setDescription(menuUpdRequest.getMenuDesc());
				mnuService.updateMenu(menu.get());
			} else {
				Menu newMenu = new Menu();
				newMenu.setId(menuId);
				newMenu.setDescription(menuUpdRequest.getMenuDesc());
				mnuService.AddMenu(newMenu);
			}
			mnuDtls = mnuService.getAllMenu();
		} catch (Exception exp) {
			throw new MenuAppException("Error to update a Menu");
		}
		return mnuDtls;
	}
	
	@DeleteMapping(path ="/{menuId}")
	public List<Menu> deleteMenu(@PathVariable String menuId){
         mnuService.deletMenu(menuId);
         mnuDtls = mnuService.getAllMenu();
         return mnuDtls;
		
	}

}
