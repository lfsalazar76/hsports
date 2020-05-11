package com.lfsalazar.jsf;

import com.lfsalazar.*;
import java.io.Serializable;
import java.util.ArrayList ;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class CatalogItemFormBean implements Serializable{
  @Inject  //will be preferred to @EJB
  private CatalogLocal catalogBean;
  
  @Inject //another injection point
  @RemoteService
  private InventoryService inventoryService;
  
  private CatalogItem item = new CatalogItem() ;
  
  private List<CatalogItem> items = new ArrayList<>() ;
  
  private String searchText ;
  
  public void searchByName() {
	  this.items = this.catalogBean.searchByName(this.searchText);
  }

  public String addItem() {
	  

	  this.catalogBean.addItem(this.item);
	  
	  this.inventoryService.createItem(this.item.getItemId(), this.item.getName());
	  
	  return "list?faces-redirect=true";
  }
  
  public void init() {
	  this.items = this.catalogBean.getItems();
  }
  
  public CatalogItem getItem() {
		return item;
  }
	
  public void setItem(CatalogItem item) {
		this.item = item;
  }
	
  public List<CatalogItem> getItems() {
		return items;
  }
	
  public void setItems(List<CatalogItem> items) {
		this.items = items;
  }

  public String getSearchText() {
	  return searchText ;
  }
  
  public void setSearchText(String searchText) {
	  this.searchText = searchText ;
  }
	
}//CatalogItemForBean
