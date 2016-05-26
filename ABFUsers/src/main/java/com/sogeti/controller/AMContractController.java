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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.AMContractResourceBean;
import com.sogeti.model.BandDT;
import com.sogeti.model.BusinessLineDT;
import com.sogeti.model.GradeDT;
import com.sogeti.model.ResourceTypeDT;
import com.sogeti.model.RoleDT;
import com.sogeti.model.SkillDT;
import com.sogeti.model.StayTypeDT;
import com.sogeti.service.AmContractService;
import com.sogeti.service.ContractManager;
import com.sogeti.service.OffshorePriceService;
import com.sogeti.service.OnshorePriceService;
import com.sogeti.xmlbeans.Resource;

@RestController
@RequestMapping(value = "/amhours")
public class AMContractController {
	
	private static final Logger logger = Logger.getLogger(AMContractController.class);

	@Autowired(required = true)
	AmContractService amContractService;
	
	@Autowired
	ContractManager contractManager;
	
	@Autowired
	OffshorePriceService offshorePriceService;
	
	@Autowired
	OnshorePriceService onshorePriceService;
	
	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse createAmContract(@RequestBody List<AMContractResourceBean> contractResources) {
		ABFResponse response = new ABFResponse();
		AmContract amContract = null;
		try{
			for(AMContractResourceBean amResource : contractResources){
				if(amResource.getAmContractResourceId() != 0){
					amContract = amContractService.getAmContractById(amResource.getAmContractResourceId());
				}else{
					amContract = new AmContract();
				}				
				Contract contract = contractManager.getContract(amResource.getContractId());
				OnshorePrice onshorePrice = onshorePriceService.find(amResource.getOnShorePrice());
				OffshorePrice offShorePrice = offshorePriceService.find(amResource.getOffShorePrice());	
				String resourceXml = getXmlString(amResource, amContractService.getMaxAmContractId()); 
				amContract.setContract(contract);
				amContract.setOffshorePrice(offShorePrice);
				amContract.setOnshorePrice(onshorePrice);
				amContract.setDetailsXml(resourceXml);			
				amContract = amContractService.saveAmContract(amContract);
				
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
	
	@RequestMapping( value = "/fetchcontractamhours/{contractId}", method = RequestMethod.GET)
	public ABFResponse getAmContractForContract(@PathVariable("contractId") String contractId){
		ABFResponse response = new ABFResponse();
			
		try {
			List<AMContractResourceBean> resourceList = new ArrayList<AMContractResourceBean>();			
			List<AmContract> AmContracts = amContractService.getAmContractsByContractId(Integer.parseInt(contractId));			
			for(AmContract resource : AmContracts){			
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
	
	@RequestMapping( value = "/removeamresource/{amContractId}", method = RequestMethod.DELETE)
	public ABFResponse deleteAmContractResource(@PathVariable("amContractId") String amContractId){
		ABFResponse response = new ABFResponse();
		try{
			AmContract amContract = amContractService.getAmContractById(Integer.parseInt(amContractId));
			amContractService.deleteAmContract(amContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
			response.setSuccessResponse(amContract);
		}catch(TechnicalException e){
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}catch (NumberFormatException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());			
		}
		return response;
	}
	
	private AMContractResourceBean fillResourceData(AmContract resource){
		AMContractResourceBean resourceBean = new AMContractResourceBean();
		resourceBean.setAmContractResourceId(resource.getAmContractId());
		
		resourceBean.setContractId(resource.getContract().getContractId());
		
		
		if(resource.getOnshorePrice() != null && resource.getOnshorePrice().getOnshorepriceId() != 0){	
			resourceBean.setPrice(resource.getOnshorePrice().getPrice().floatValue());
			resourceBean.setOnShorePrice(resource.getOnshorePrice().getOnshorepriceId());
			
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
			resourceBean.setPrice(resource.getOffshorePrice().getPrice().floatValue());
			resourceBean.setOnShorePrice(resource.getOffshorePrice().getOffshorepriceId());
			
			ResourceTypeDT resourceType = new ResourceTypeDT();
			resourceType.setResourcetypeId(resource.getOffshorePrice().getBusinessLine().getResourceType().getResourcetypeId());
			resourceType.setResourceType(resource.getOffshorePrice().getBusinessLine().getResourceType().getResourceType());
			
			BusinessLineDT businessLine = new BusinessLineDT();
			businessLine.setBusinesslineId(resource.getOffshorePrice().getBusinessLine().getBusinesslineId());
			businessLine.setBusinesslineName(resource.getOffshorePrice().getBusinessLine().getBusinesslineName());
			
			
			BandDT band = new BandDT();
			band.setBandId(resource.getOffshorePrice().getBand().getBandId());
			band.setBandName(resource.getOffshorePrice().getBand().getBandName());
			
			StayTypeDT stayType = new StayTypeDT();
			stayType.setStayTypeId(resource.getOffshorePrice().getStayType().getStayTypeId());
			stayType.setStayType(resource.getOffshorePrice().getStayType().getStayType());
			
			SkillDT skill = new SkillDT();
			skill.setSkillId(resource.getOffshorePrice().getBusinessLine().getSkill().getSkillId());
			skill.setSkillName(resource.getOffshorePrice().getBusinessLine().getSkill().getSkillName());
			
			resourceBean.setResourceType(resourceType);
			resourceBean.setBusinessLine(businessLine);
			resourceBean.setBand(band);
			resourceBean.setStayType(stayType);
			resourceBean.setSkill(skill);
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
	
	
	private String getXmlString(AMContractResourceBean resourceBean, int maxAmContractId) throws TechnicalException{		
		StringWriter sw = new StringWriter();
		try {
			Resource resource = new Resource();
			resource.setId(maxAmContractId + 1);
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
