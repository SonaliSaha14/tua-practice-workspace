package com.menu.app.ui.repository;

import org.springframework.data.repository.CrudRepository;

import com.menu.app.ui.model.response.Menu;

public interface MenuRepository extends CrudRepository<Menu, String>,MenuRepositoryCustom {

}
