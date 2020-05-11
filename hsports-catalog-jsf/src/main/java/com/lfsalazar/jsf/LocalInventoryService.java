package com.lfsalazar.jsf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
//@Alternative  //use it only if explicitly called
public class LocalInventoryService implements InventoryService {

	private Map<Long, InventoryItem> items = new HashMap<>() ;
	
	@Override
	@Logging
	public void createItem(Long catalogItemId, String name) {
		long inventoryItemId = items.size() + 1 ;
		this.items.put(inventoryItemId, new InventoryItem(inventoryItemId, catalogItemId, name, 0L));
		this.printInventory();
		
	}
	
	private void printInventory() {
		System.out.println("Local inventory contains: "); 
		for(Entry<Long, InventoryItem> entry: this.items.entrySet() ){
			System.out.println(entry.getValue().getName());
		}
	}

	@Override
	public Long getQuantity(Long catalogItemId) {
		return 0L;
	}

	@Override
	public Future<InventoryItem> asyncGetQuantity(Long catalogItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletionStage<InventoryItem> reactiveGetQuantity(Long catalogItemId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
