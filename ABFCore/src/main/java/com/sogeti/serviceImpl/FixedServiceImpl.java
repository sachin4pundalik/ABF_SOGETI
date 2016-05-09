package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.FixedDAO;
import com.sogeti.db.models.FixedContract;
import com.sogeti.service.FixedService;

@Service("fixedService")
public class FixedServiceImpl implements FixedService{

	@Autowired
	FixedDAO fixedDAO;

	public List<FixedContract> findAll() {
		return fixedDAO.findAll();
	}

	public void create(FixedContract fixedContract) {
		fixedDAO.create(fixedContract);
	}

	public void delete(Integer fixedContractId) {
		fixedDAO.delete(fixedContractId);
		
	}

	public FixedContract find(Integer fixedContractId) {
		return fixedDAO.find(fixedContractId);
	}

	public FixedContract update(FixedContract fixedContract) {
		return fixedDAO.update(fixedContract);
	}
	
	public List<FixedContract> getFixedContractsForAContract(Integer contractId){
		return fixedDAO.getFixedContractsForAContract(contractId);
	}




}
