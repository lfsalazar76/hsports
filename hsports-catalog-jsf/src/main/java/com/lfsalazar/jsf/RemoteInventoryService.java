package com.lfsalazar.jsf;

import java.util.Random;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ApplicationScoped
@RemoteService
public class RemoteInventoryService implements InventoryService {

	
	private String apiUrl = "http://localhost:8080/hsports-catalog-jax/hsports/api/";
	
	@Override
	public void createItem(Long catalogItemId, String name) {
	    Client client = ClientBuilder.newClient();
	    Response response = client.target(apiUrl)
	    	.path("inventoryitems")
	    	.request()
	    	.post(Entity.json(new InventoryItem(null,catalogItemId, name, (long) new Random().nextInt(10))));
	    
	    System.out.println(response.getStatus());
	    System.out.println(response.getLocation().getPath());
	}


	@Override
	public Long getQuantity(Long catalogItemId) {
		Client client = ClientBuilder.newClient() ;
		
		InventoryItem inventoryItem = client.target(apiUrl)
				.path("inventoryitems")
				.path("catalog").path("{catalogItemId}")
				.resolveTemplate("catalogItemId", catalogItemId.toString())
		.request().get(InventoryItem.class);  //what class
		return inventoryItem.getQuantity() ;
	}


	@Override
	public Future<InventoryItem> asyncGetQuantity(Long catalogItemId) {
		Client client = ClientBuilder.newClient() ;
		
		return client.target(apiUrl)
				.path("inventoryitems")
				.path("catalog").path("{catalogItemId}")
				.resolveTemplate("catalogItemId", catalogItemId.toString())
		.request().async().get(InventoryItem.class);  //what class
		
	}


	@Override
	public CompletionStage<InventoryItem> reactiveGetQuantity(Long catalogItemId) {
		Client client = ClientBuilder.newClient() ;
		
		return client.target(apiUrl)
				.path("inventoryitems")
				.path("catalog").path("{catalogItemId}")
				.resolveTemplate("catalogItemId", catalogItemId.toString())
		.request().rx().get(InventoryItem.class);  //what class
		
	}

}