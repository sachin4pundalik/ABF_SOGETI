package com.sogeti.service;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.KtContract;

public interface KtContractService {
	
	public List<KtContract> getKtContractsByContractId(int contractId) throws TechnicalException;
	
	public KtContract getKtContractById(int amId) throws TechnicalException;
	
	public boolean saveKtContract(KtContract ktContract) throws TechnicalException;
	
	public boolean saveKtContractBatch(List<KtContract> ktContracts) throws TechnicalException;
	
	public boolean deleteKtContract(KtContract ktContract) throws TechnicalException;

}
