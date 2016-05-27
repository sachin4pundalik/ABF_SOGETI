package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.KtContractDAO;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.KtContract;
import com.sogeti.service.KtContractService;

@Service("ktContractService")
public class KtContractServiceImpl implements KtContractService {
	
	@Autowired
	KtContractDAO ktContractDao;

	public List<KtContract> getKtContractsByContractId(int contractId) throws TechnicalException {
		Contract contract = ktContractDao.getContractById(contractId);
		List<KtContract> KtContracts= ktContractDao.getKtContractsByContractId(contract);
		for(KtContract ktContract : KtContracts){
			//setDataFields(AmContract);
			setDataFieldsId(ktContract);
		}		
		return KtContracts;
	}

	public KtContract getKtContractById(int amId) throws TechnicalException {
		return ktContractDao.getKtContractById(amId);
	}

	public boolean saveKtContract(KtContract ktContract) throws TechnicalException {
		return ktContractDao.saveKtContract(ktContract);
	}

	public boolean saveKtContractBatch(List<KtContract> ktContracts) throws TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteKtContract(KtContract ktContract) throws TechnicalException {
		return ktContractDao.deleteKtContract(ktContract);
	}
	
	private void setDataFields(KtContract ktContract){		
		ktContract.setId(Integer.toString(ktContract.getKtContractId()));		
		if(ktContract.getOnshorePrice() != null && ktContract.getOnshorePrice().getOnshorepriceId() != 0){
			/*System.out.println("AM ID===>"+AmContract.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+AmContract.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+AmContract.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+AmContract.getOnShorePriceId().getGradeId().getGradeId());	*/		
			ktContract.setType(ktContract.getOnshorePrice().getBusinessLine().getResourceType().getResourceType());
			ktContract.setBline(ktContract.getOnshorePrice().getBusinessLine().getBusinesslineName());
			ktContract.setRole(ktContract.getOnshorePrice().getRole().getRoleType());
			ktContract.setGrade(ktContract.getOnshorePrice().getGrade().getGradeType());
			//AmContract.setSkill(AmContract.getOnshorePrice().getBusinessLine().getSkill().getSkillName());
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+AmContract.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+AmContract.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+AmContract.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
			ktContract.setType(ktContract.getOffshorePrice().getBusinessLine().getResourceType().getResourceType());			
			ktContract.setBline(ktContract.getOffshorePrice().getBusinessLine().getBusinesslineName());
			ktContract.setSkill(ktContract.getOffshorePrice().getBusinessLine().getSkill().getSkillName());
			ktContract.setBand(ktContract.getOffshorePrice().getBand().getBandName());
			ktContract.setStay(ktContract.getOffshorePrice().getStayType().getStayType());			
		}		
	}
	
	private void setDataFieldsId(KtContract ktContract){		
		ktContract.setId(Integer.toString(ktContract.getKtContractId()));		
		if(ktContract.getOnshorePrice() != null && ktContract.getOnshorePrice().getOnshorepriceId() != 0){
			/*System.out.println("AM ID===>"+AmContract.getAmId());
			System.out.println("BUSINESS LINE ID ONSHORE===>"+AmContract.getOnShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOnShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("ROLE ID====>"+AmContract.getOnShorePriceId().getRoleId().getRoleId());
			System.out.println("GRADE ID===>"+AmContract.getOnShorePriceId().getGradeId().getGradeId());	*/		
			ktContract.setType(Integer.toString(ktContract.getOnshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));
			ktContract.setBline(Integer.toString(ktContract.getOnshorePrice().getBusinessLine().getBusinesslineId()));
			ktContract.setRole(Integer.toString(ktContract.getOnshorePrice().getRole().getRoleId()));
			ktContract.setGrade(Integer.toString(ktContract.getOnshorePrice().getGrade().getGradeId()));
			//AmContract.setSkill(Integer.toString(AmContract.getOnshorePrice().getBusinessLine().getSkill().getSkillId()));
		} else{
			/*System.out.println("BUSINESS LINE ID OFFSHORE===>"+AmContract.getOffShorePriceId().getBusinesslineId().getBusinesslineId());
			System.out.println("TYPE ID====>"+AmContract.getOffShorePriceId().getBusinesslineId().getResourceType().getResourcetypeId());
			System.out.println("SKILL DETAILS===>"+AmContract.getOffShorePriceId().getBusinesslineId().getSkill().getSkillId());
			System.out.println("STAY TYPE DETAIL=>"+AmContract.getOffShorePriceId().getStayTypeId().getStayTypeId());*/
			ktContract.setType(Integer.toString(ktContract.getOffshorePrice().getBusinessLine().getResourceType().getResourcetypeId()));			
			ktContract.setBline(Integer.toString(ktContract.getOffshorePrice().getBusinessLine().getBusinesslineId()));
			ktContract.setSkill(Integer.toString(ktContract.getOffshorePrice().getBusinessLine().getSkill().getSkillId()));
			ktContract.setBand(Integer.toString(ktContract.getOffshorePrice().getBand().getBandId()));
			ktContract.setStay(Integer.toString(ktContract.getOffshorePrice().getStayType().getStayTypeId()));			
		}		
	}

	public int getMaxKtContractId() throws TechnicalException {
		return ktContractDao.getMaxKtContractId();
	}

}
