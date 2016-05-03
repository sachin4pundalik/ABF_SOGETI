/**
 * 
 */
package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.FixedCostDAO;
import com.sogeti.db.models.FixedCost;
import com.sogeti.service.FixedCostService;

/**
 * @author vkalyana
 *
 */

@Service("fixedCostService")
public class FixedCostServiceImpl implements FixedCostService {

	@Autowired
	FixedCostDAO fixedCostDAO;
	
	public List<FixedCost> findAll() {
		return fixedCostDAO.findAll();
	}

	public void create(FixedCost t) {
		fixedCostDAO.create(t);
	}

	public void delete(Integer id) {
		fixedCostDAO.delete(id);
	}

	public FixedCost find(Integer id) {
		
		return fixedCostDAO.find(id);
	}

	public FixedCost update(FixedCost t) {
		return fixedCostDAO.update(t);
	}

}
