package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.AmContractDao;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.Contract;
import com.sogeti.service.AmContractService;

@Service("amContractService")
public class AmContractServiceImpl implements AmContractService {
	
	@Autowired
	AmContractDao amContractDao;

	public List<AmContract> getAmContractsByContractId(int contractId) throws TechnicalException {
		Contract contract = amContractDao.getContractById(contractId);
		List<AmContract> AmContracts= amContractDao.getAmContractsByContractId(contract);
		for(AmContract AmContract : AmContracts){
			//setDataFields(AmContract);
			setDataFieldsId(AmContract);
		}
		
		return AmContracts;
	}

	public AmContract getAmContractById(int amId) throws TechnicalException {
		return amContractDao.getAmContractById(amId);
	}

	public boolean saveAmContract(AmContract AmContract) throws TechnicalException {
		return amContractDao.saveAmContract(AmContract);
	}

	public boolean saveAmContractBatch(List<AmContract> AmContracts) throws TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteAmContract(AmContract amContract) throws TechnicalException {
		return amContractDao.deleteAmContract(amContract);
	}
	
	public int getMaxAmContractId() throws TechnicalException{
		return amContractDao.getMaxAmContractId();
	}
	
	private void setDataFields(AmContract AmContract){		
		AmContract.setId(Integer.toString(AmContract.getAmContractId()));		
		if(AmContract.getOnshorePrice() != null && AmContract.getOnshorePrice().getOnshorepriceId() != 0){			
			AmContract.setType(AmContract.getOnshorePrice().getBusinessLine().getResourceType().getResourceType());
			AmContract.setBline(AmContract.getOnshorePrice().getBusinessLine().getBusinesslineName());
			AmContract.setRole(AmContract.getOnshorePrice().getRole().getRoleType());
			AmContract.setGrade(AmContract.getOnshorePrice().getGrade().getGradeType());			
		} else{			
			AmContract.setType(AmContract.getOffshorePrice().getBusinessLine().getResourceType().getResourceType());			
			AmContract.setBline(AmContract.getOffshorePrice().getBusinessLine().getBusinesslineName());
			AmContract.setSkill(AmContract.getOffshorePrice().getBusinessLine().getSkill().getSkillName());
			AmContract.setBand(AmContract.getOffshorePrice().getBand().getBandName());
			AmContract.setStay(AmContract.getOffshorePrice().getStayType().getStayType());			
		}		
	}
	
	private void setDataFieldsId(AmContract AmContract){		
		AmContract.setId(Integer.toString(AmContract.getAmContractId()));		
		if(AmContract.getOnshorePrice() != null && AmContract.getOnshorePrice().getOnshorepriceId() != 0){			
			AmContract.setType(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));
			AmContract.setBline(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getBusinesslineId()));
			AmContract.setRole(Integer.toString(AmContract.getOnshorePrice().getRole().getRoleId()));
			AmContract.setGrade(Integer.toString(AmContract.getOnshorePrice().getGrade().getGradeId()));			
		} else{			
			AmContract.setType(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));			
			AmContract.setBline(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getBusinesslineId()));
			AmContract.setSkill(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getSkill().getSkillId()));
			AmContract.setBand(Integer.toString(AmContract.getOffshorePrice().getBand().getBandId()));
			AmContract.setStay(Integer.toString(AmContract.getOffshorePrice().getStayType().getStayTypeId()));			
		}		
	}

}
