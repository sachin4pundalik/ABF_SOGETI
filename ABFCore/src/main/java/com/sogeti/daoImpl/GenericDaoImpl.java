package com.sogeti.daoImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.sogeti.dao.GenericDao;


@Transactional
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    EntityManager em;

    private Class<T> type;
    
    private String entity;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
       // queryString.append(this.getQueryClauses(params, null));

        final Query query = this.em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }
    
    
    public List<T> findAll() 
    {
        Query query = 
             em.createQuery("select x from " + 
                                                      getEntityName() + " x");
        
        return (List<T>) query.getResultList();
    }

    public T create(final T t) throws PersistenceException {
    	try {
        em.persist(t);
    	} catch (PersistenceException e) {
			throw e;
		}
        return t;
    }

    public void delete(final Object id) {
        em.remove(this.em.getReference(type, id));
    }

    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    public T update(final T t) {
        return em.merge(t);    
    }
    
    public String getEntityName() 
    {
        if (entity == null) {
            Entity entityAnn = (Entity) type.getAnnotation(Entity.class);
            
            if (entityAnn != null && !entityAnn.name().equals("")) 
            {
                entity = entityAnn.name();
            } else 
            {
                entity = type.getSimpleName();
            }
        }
        
        return entity;
    }
}