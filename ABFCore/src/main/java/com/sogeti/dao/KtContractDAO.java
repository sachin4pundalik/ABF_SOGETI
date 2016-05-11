package com.sogeti.dao;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.KtContract;

public interface KtContractDAO {
	
public List<KtContract> getKtContractsByContractId(Contract contractId) throws TechnicalException;
	
	public KtContract getKtContractById(int amId) throws TechnicalException;
	
	public boolean saveKtContract(KtContract ktContract) throws TechnicalException;
	
	public boolean saveKtContractBatch(List<KtContract> ktContracts) throws TechnicalException;
	
	public Contract getContractById(int contractId);
	
	public boolean deleteKtContract(KtContract KtContract) throws TechnicalException;

}
