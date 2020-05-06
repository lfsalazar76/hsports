package com.lfsalazar.jsf;

import com.lfsalazar.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
//@RequestScoped
@ConversationScoped
public class CatalogItemDeleteBean implements Serializable {
	
	private long itemId;
	private CatalogItem item;
	@Inject
	private CatalogItemFormBean catalogItemFormBean; 
	@Inject 
	private CatalogLocal catalogBean;
	@Inject
	private Conversation conversation;

	public void fetchItem() {
		/*
		 * List<CatalogItem> items =
		 * this.catalogItemFormBean.getItems().stream().filter(i -> { return
		 * i.getItemId() == itemId; }).collect(Collectors.toList());
		 * 
		 * if (items.isEmpty()) { this.item = null; } else { this.item = items.get(0); }
		 */
		
		conversation.begin();
		this.item=catalogBean.findItem(this.itemId);
	}
	
	public String removeItem() {
		/*
		 * this.catalogItemFormBean.getItems().removeIf(item ->{ return
		 * item.getItemId().equals(this.itemId); }); 
		 */
		this.catalogBean.deleteItem(this.item);
		conversation.end();
		return "list?faces-redirect=true";
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public CatalogItem getItem() {
		return item;
	}

	public void setItem(CatalogItem item) {
		this.item = item;
	}

	public CatalogItemFormBean getCatalogItemFormBean() {
		return catalogItemFormBean;
	}

	public void setCatalogItemFormBean(CatalogItemFormBean catalogItemFormBean) {
		this.catalogItemFormBean = catalogItemFormBean;
	}


}
