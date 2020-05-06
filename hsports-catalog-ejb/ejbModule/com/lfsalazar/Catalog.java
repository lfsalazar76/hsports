package com.lfsalazar;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Catalog
 */
@Singleton
@LocalBean
public class Catalog implements CatalogLocal {

	
	@PersistenceContext
	private EntityManager entityManager ;
	
	private List<CatalogItem> items = new ArrayList<>();
    /**
     * Default constructor. 
     */
    public Catalog() {
       
    }

	@Override
	public List<CatalogItem> getItems() {
		return this.entityManager.createQuery("select c from CatalogItem c", CatalogItem.class).getResultList();  //this is JPQL not sql
	}

	@Override
	public void addItem(CatalogItem item) {
		this.entityManager.persist(item);	
	}	
	
	@Override 
	public CatalogItem findItem(Long itemId) {
		return this.entityManager.find(CatalogItem.class, itemId);
	}
	
	@Override 
	public void deleteItem(CatalogItem item) {
		this.entityManager.remove(this.entityManager.contains(item)? item:this.entityManager.merge(item));
	}
	
	@Override
	public List<CatalogItem> searchByName(String name){
		return this.entityManager.createQuery("select c from CatalogItem c"+  //remember this is JPQL
				" where c.name like :name", CatalogItem.class).setParameter("name","%"+name+"%").getResultList();
	}

	@Override
	public void saveItem(CatalogItem item) {
		this.entityManager.merge(item);
		
	}
	
	

}
