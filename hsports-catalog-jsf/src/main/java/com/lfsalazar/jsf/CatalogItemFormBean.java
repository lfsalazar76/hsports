package com.lfsalazar.jsf;

import com.lfsalazar.*;
import java.io.Serializable;
import java.util.ArrayList ;
import java.util.List;

//import javax.ejb.EJB;not needed anymore as we took out the @EJB annotation
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class CatalogItemFormBean implements Serializable{
	
  @Inject  //will be preferred to @EJB
  private CatalogLocal catalogBean;
  
  @Inject //another injection point
  private InventoryService inventoryService;

  private CatalogItem item = new CatalogItem() ;
  
  private List<CatalogItem> items = new ArrayList<>() ;

  public String addItem() {
	  long itemId = this.catalogBean.getItems().size() + 1 ;
	  
	  this.catalogBean.addItem(new CatalogItem(itemId, this.item.getName(),this.item.getManufacturer(),
			  							this.item.getDescription(),this.item.getAvailableDate()));
	  
	  /*this.catalogBean.getItems().stream().forEach(item ->{
		  System.out.println(item.toString());*/
	  
	  this.inventoryService.createItem(this.item.getItemId(), this.item.getName());
	  /*});*/
	  
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
	
	
}//CatalogItemForBean
