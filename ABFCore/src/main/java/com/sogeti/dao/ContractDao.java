package com.sogeti.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import com.sogeti.db.models.Contract;

/**
 * ContractDAO class will call the addContract method.
 * <P>
 * <B> Visibility decisions: </B>
 * <P>
 * Unless otherwise noted, attributes are private, and a public getter and
 * setter is provided for each.
 * <P>
 * <B> Design/implementation notes: </B>
 * <P>
 * Document any decisions, assumptions, issues, or other notes regarding the
 * implementation of this class.
 * <P>
 * <P>
 * <B> Revision History: </B>
 * 
 * <PRE>
 * 
 * =============================================================================
 * Prior Date            By              Version  Project/CSR  Description 
 * ---------- --------------------------   ---------- ------------ ------------ 
 * 06/04/2016         kalyan             N/A          ABF        Created.
 * 
 * =============================================================================
 * 
 * </PRE>
 */

public interface ContractDao {

	public void createContract(Contract contractEntity)
			throws PersistenceException;

	public List<Contract> allContracts();
	public List<Contract> allContractsByMe(int loginID);
	public List<Contract> allContractsApprovalByMe(int loginID);
	
	public Contract getContract(int contractID);

	public void updateContract(Contract contract);

	public void deleteContract(Contract contractData);
}
