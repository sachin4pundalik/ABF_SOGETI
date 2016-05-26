package com.sogeti.controller;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.KtContract;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.BandDT;
import com.sogeti.model.BusinessLineDT;
import com.sogeti.model.GradeDT;
import com.sogeti.model.KTContractResourceBean;
import com.sogeti.model.ResourceTypeDT;
import com.sogeti.model.RoleDT;
import com.sogeti.model.SkillDT;
import com.sogeti.model.StayTypeDT;
import com.sogeti.service.ContractManager;
import com.sogeti.service.KtContractService;
import com.sogeti.service.OffshorePriceService;
import com.sogeti.service.OnshorePriceService;
import com.sogeti.xmlbeans.Resource;

@RestController
@RequestMapping(value = "/kthours")
public class KTContractController {	

	
	private static final Logger logger = Logger.getLogger(KTContractController.class);

	@Autowired(required = true)
	KtContractService ktContractService;
	
	@Autowired
	ContractManager contractManager;
	
	@Autowired
	OffshorePriceService offshorePriceService;
	
	@Autowired
	OnshorePriceService onshorePriceService;
	
	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse createKtContract(@RequestBody List<KTContractResourceBean> contractResources) {
		
		ABFResponse response = new ABFResponse();
		KtContract ktContract = null;
		try{
			for(KTContractResourceBean ktResource : contractResources){
				if(ktResource.getKtContractResourceId() != 0){
					ktContract = ktContractService.getKtContractById(ktResource.getKtContractResourceId());
				}else{
					ktContract = new KtContract();
				}				
				Contract contract = contractManager.getContract(ktResource.getContractId());
				OnshorePrice onshorePrice = onshorePriceService.find(ktResource.getOnShorePrice());
				OffshorePrice offShorePrice = offshorePriceService.find(ktResource.getOffShorePrice());	
				String resourceXml = getXmlString(ktResource, ktContractService.getMaxKtContractId()); 
				ktContract.setContract(contract);
				ktContract.setOffshorePrice(offShorePrice);
				ktContract.setOnshorePrice(onshorePrice);
				ktContract.setDetailsXml(resourceXml);			
				ktContractService.saveKtContract(ktContract);
				
				response.setStatus(ABFConstants.STATUS_SUCCESS);
				response.setSuccessResponse(ABFConstants.STATUS_SUCCESS);
			}
		}catch(TechnicalException e){
			logger.error(e);
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}		
		return response;
	}
	
	@RequestMapping( value = "/fetchcontractkthours/{contractId}", method = RequestMethod.GET)
	public ABFResponse getKtContractForContract(@PathVariable("contractId") String contractId){
		ABFResponse response = new ABFResponse();
			
		try {
			List<KTContractResourceBean> resourceList = new ArrayList<KTContractResourceBean>();			
			List<KtContract> KtContracts = ktContractService.getKtContractsByContractId(Integer.parseInt(contractId));			
			for(KtContract resource : KtContracts){			
				resourceList.add(fillResourceData(resource));				
			}			
			response.setStatus(ABFConstants.STATUS_SUCCESS);
			response.setSuccessResponse(resourceList);
		} catch (TechnicalException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());			
		} catch (NumberFormatException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());			
		}		
		return response;		
	}
	
	@RequestMapping( value = "/removektresource/{ktContractId}", method = RequestMethod.GET)
	public ABFResponse deleteAmContractResource(@PathVariable("ktContractId") String ktContractId){
		ABFResponse response = new ABFResponse();
		try{
			KtContract ktContract = ktContractService.getKtContractById(Integer.parseInt(ktContractId));
			ktContractService.deleteKtContract(ktContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
			response.setSuccessResponse(ktContract);
		}catch(TechnicalException e){
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}catch (NumberFormatException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());			
		}
		return response;
	}
	
	private KTContractResourceBean fillResourceData(KtContract resource){
		KTContractResourceBean resourceBean = new KTContractResourceBean();
		resourceBean.setKtContractResourceId(resource.getKtContractId());
		
		if(resource.getOnshorePrice() != null && resource.getOnshorePrice().getOnshorepriceId() != 0){		
			ResourceTypeDT resourceType = new ResourceTypeDT();
			resourceType.setResourcetypeId(resource.getOnshorePrice().getBusinessLine().getResourceType().getResourcetypeId());
			resourceType.setResourceType(resource.getOnshorePrice().getBusinessLine().getResourceType().getResourceType());
			
			BusinessLineDT businessLine = new BusinessLineDT();
			businessLine.setBusinesslineId(resource.getOnshorePrice().getBusinessLine().getBusinesslineId());
			businessLine.setBusinesslineName(resource.getOnshorePrice().getBusinessLine().getBusinesslineName());
			
			RoleDT role = new RoleDT();
			role.setRoleId(resource.getOnshorePrice().getRole().getRoleId());
			role.setRoleType(resource.getOnshorePrice().getRole().getRoleType());
			
			GradeDT grade = new GradeDT();
			grade.setGradeId(resource.getOnshorePrice().getGrade().getGradeId());
			grade.setGradeType(resource.getOnshorePrice().getGrade().getGradeType());
			
			resourceBean.setResourceType(resourceType);
			resourceBean.setBusinessLine(businessLine);
			resourceBean.setRole(role);
			resourceBean.setGrade(grade);
			
		}else{
			ResourceTypeDT resourceType = new ResourceTypeDT();
			resourceType.setResourcetypeId(resource.getOffshorePrice().getBusinessLine().getResourceType().getResourcetypeId());
			resourceType.setResourceType(resource.getOffshorePrice().getBusinessLine().getResourceType().getResourceType());
			
			BusinessLineDT businessLine = new BusinessLineDT();
			businessLine.setBusinesslineId(resource.getOffshorePrice().getBusinessLine().getBusinesslineId());
			businessLine.setBusinesslineName(resource.getOffshorePrice().getBusinessLine().getBusinesslineName());
			
			SkillDT skill = new SkillDT();
			skill.setSkillId(resource.getOffshorePrice().getBusinessLine().getSkill().getSkillId());
			skill.setSkillName(resource.getOffshorePrice().getBusinessLine().getSkill().getSkillName());
			
			BandDT band = new BandDT();
			band.setBandId(resource.getOffshorePrice().getBand().getBandId());
			band.setBandName(resource.getOffshorePrice().getBand().getBandName());
			
			StayTypeDT stayType = new StayTypeDT();
			stayType.setStayTypeId(resource.getOffshorePrice().getStayType().getStayTypeId());
			stayType.setStayType(resource.getOffshorePrice().getStayType().getStayType());
			
			resourceBean.setResourceType(resourceType);
			resourceBean.setBusinessLine(businessLine);
			resourceBean.setSkill(skill);
			resourceBean.setBand(band);
			resourceBean.setStayType(stayType);
		}
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Resource.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();	
			StringReader reader = new StringReader(resource.getDetailsXml());
			Resource resourceObj = (Resource) unmarshaller.unmarshal(reader);
			
			resourceBean.setMonths(resourceObj.getMonths());			
		}catch(JAXBException e){
			e.printStackTrace();
		}		
		return resourceBean;
	}
	
	private String getXmlString(KTContractResourceBean resourceBean, int maxKtContractId) throws TechnicalException{		
		StringWriter sw = new StringWriter();
		try {
			Resource resource = new Resource();
			resource.setId(maxKtContractId + 1);
			resource.setMonths(resourceBean.getMonths());
		    JAXBContext context = JAXBContext.newInstance(Resource.class);
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.marshal(resource, sw );
		    System.out.println(sw.toString());
		} catch (JAXBException e) {
		    throw new TechnicalException("XML Conversion error", e);
		}
		return sw.toString();		
	}


}
