package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.AmContractDao;
import com.sogeti.db.models.AMContractResource;
import com.sogeti.db.models.Contract;
import com.sogeti.service.AmContractService;

@Service("amContractService")
public class AmContractServiceImpl implements AmContractService {
	
	@Autowired
	AmContractDao amContractDao;

	public List<AMContractResource> getAmContractResourcesByContractId(int contractId) throws TechnicalException {
		Contract contract = amContractDao.getContractById(contractId);
		List<AMContractResource> amContractResources= amContractDao.getAmContractResourcesByContractId(contract);
		for(AMContractResource amContractResource : amContractResources){
			//setDataFields(amContractResource);
			setDataFieldsId(amContractResource);
		}
		
		return amContractResources;
	}

	public AMContractResource getAmContractById(int amId) throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveAmContractResource(AMContractResource amContractResource) throws TechnicalException {
		return amContractDao.saveAmContractResource(amContractResource);
	}

	public boolean saveAmContractResourceBatch(List<AMContractResource> amContractResources) throws TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void setDataFields(AMContractResource amContractResource){		
		amContractResource.setId(Integer.toString(amContractResource.getAmId()));		
		if(amContractResource.getOnShorePriceId() != null && amContractResource.getOnShorePriceId().getOnshorepriceId() != 0){
			/*System.out.println("AM ID===>"+amContractResource.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+amContractResource.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+amContractResource.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+amContractResource.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+amContractResource.getOnShorePriceId().getGradeId().getGradeId());	*/		
			amContractResource.setType(amContractResource.getOnShorePriceId().getBusinesslineId().getResourceType().getResourceType());
			amContractResource.setBline(amContractResource.getOnShorePriceId().getBusinesslineId().getBusinesslineName());
			amContractResource.setRole(amContractResource.getOnShorePriceId().getRoleId().getRoleType());
			amContractResource.setGrade(amContractResource.getOnShorePriceId().getGradeId().getGradeType());
			amContractResource.setSkill(amContractResource.getOnShorePriceId().getBusinesslineId().getSkill().getSkillName());
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+amContractResource.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+amContractResource.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+amContractResource.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+amContractResource.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
			amContractResource.setType(amContractResource.getOffShorePriceId().getBusinesslineId().getResourceType().getResourceType());			
			amContractResource.setBline(amContractResource.getOffShorePriceId().getBusinesslineId().getBusinesslineName());
			amContractResource.setSkill(amContractResource.getOffShorePriceId().getBusinesslineId().getSkill().getSkillName());
			amContractResource.setBand(amContractResource.getOffShorePriceId().getBandId().getBandName());
			amContractResource.setStay(amContractResource.getOffShorePriceId().getStayTypeId().getStayType());			
		}		
	}
	
	private void setDataFieldsId(AMContractResource amContractResource){		
		amContractResource.setId(Integer.toString(amContractResource.getAmId()));		
		if(amContractResource.getOnShorePriceId() != null && amContractResource.getOnShorePriceId().getOnshorepriceId() != 0){
			/*System.out.println("AM ID===>"+amContractResource.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+amContractResource.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+amContractResource.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+amContractResource.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+amContractResource.getOnShorePriceId().getGradeId().getGradeId());	*/		
			amContractResource.setType(Integer.toString(amContractResource.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId()));
			amContractResource.setBline(Integer.toString(amContractResource.getOnShorePriceId().getBusinesslineId().getBusinesslineId()));
			amContractResource.setRole(Integer.toString(amContractResource.getOnShorePriceId().getRoleId().getRoleId()));
			amContractResource.setGrade(Integer.toString(amContractResource.getOnShorePriceId().getGradeId().getGradeId()));
			amContractResource.setSkill(Integer.toString(amContractResource.getOnShorePriceId().getBusinesslineId().getSkill().getSkillId()));
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+amContractResource.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+amContractResource.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+amContractResource.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+amContractResource.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
			amContractResource.setType(Integer.toString(amContractResource.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId()));			
			amContractResource.setBline(Integer.toString(amContractResource.getOffShorePriceId().getBusinesslineId().getBusinesslineId()));
			amContractResource.setSkill(Integer.toString(amContractResource.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId()));
			amContractResource.setBand(Integer.toString(amContractResource.getOffShorePriceId().getBandId().getBandId()));
			amContractResource.setStay(Integer.toString(amContractResource.getOffShorePriceId().getStayTypeId().getStayTypeId()));			
		}		
	}

}
