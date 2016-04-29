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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveAmContract(AmContract AmContract) throws TechnicalException {
		return amContractDao.saveAmContract(AmContract);
	}

	public boolean saveAmContractBatch(List<AmContract> AmContracts) throws TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void setDataFields(AmContract AmContract){		
		AmContract.setId(Integer.toString(AmContract.getAmContractId()));		
		if(AmContract.getOnshorePrice() != null && AmContract.getOnshorePrice().getOnshorepriceId() != 0){
			/*System.out.println("AM ID===>"+AmContract.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+AmContract.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+AmContract.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+AmContract.getOnShorePriceId().getGradeId().getGradeId());	*/		
			AmContract.setType(AmContract.getOnshorePrice().getBusinessLine().getResourceType().getResourceType());
			AmContract.setBline(AmContract.getOnshorePrice().getBusinessLine().getBusinesslineName());
			AmContract.setRole(AmContract.getOnshorePrice().getRole().getRoleType());
			AmContract.setGrade(AmContract.getOnshorePrice().getGrade().getGradeType());
			//AmContract.setSkill(AmContract.getOnshorePrice().getBusinessLine().getSkill().getSkillName());
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+AmContract.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+AmContract.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+AmContract.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
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
			/*System.out.println("AM ID===>"+AmContract.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+AmContract.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+AmContract.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+AmContract.getOnShorePriceId().getGradeId().getGradeId());	*/		
			AmContract.setType(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));
			AmContract.setBline(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getBusinesslineId()));
			AmContract.setRole(Integer.toString(AmContract.getOnshorePrice().getRole().getRoleId()));
			AmContract.setGrade(Integer.toString(AmContract.getOnshorePrice().getGrade().getGradeId()));
			//AmContract.setSkill(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getSkill().getSkillId()));
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+AmContract.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+AmContract.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+AmContract.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
			AmContract.setType(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));			
			AmContract.setBline(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getBusinesslineId()));
			AmContract.setSkill(Integer.toString(AmContract.getOffshorePrice().getBusinessLine().getSkill().getSkillId()));
			AmContract.setBand(Integer.toString(AmContract.getOffshorePrice().getBand().getBandId()));
			AmContract.setStay(Integer.toString(AmContract.getOffshorePrice().getStayType().getStayTypeId()));			
		}		
	}

}
