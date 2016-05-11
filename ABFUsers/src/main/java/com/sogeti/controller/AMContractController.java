package com.sogeti.controller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
import com.sogeti.db.models.AmContract;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.AMContractResourceBean;
import com.sogeti.service.AmContractService;
import com.sogeti.xmlbeans.Month;
import com.sogeti.xmlbeans.Resource;

@RestController
@RequestMapping(value = "/amhours")
public class AMContractController {
	
	private static final Logger logger = Logger.getLogger(AMContractController.class);

	@Autowired(required = true)
	AmContractService amContractService;
	
	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse createAmContract(@RequestBody List<AmContract> contractResources) {
		
		return null;
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
	
	@RequestMapping( value = "/removeamresource/{amContractId}", method = RequestMethod.GET)
	public ABFResponse deleteAmContractResource(@PathVariable("amContractId") String amContractId){
		ABFResponse response = new ABFResponse();
		try{
			AmContract amContract = amContractService.getAmContractById(Integer.parseInt(amContractId));
			boolean deleteFlag = amContractService.deleteAmContract(amContract);
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
		resourceBean.setId(resource.getId());
		resourceBean.setType(resource.getType());
		resourceBean.setBline(resource.getBline());
		resourceBean.setSkill(resource.getSkill());
		resourceBean.setBand(resource.getBand());
		resourceBean.setRole(resource.getRole());
		resourceBean.setGrade(resource.getGrade());
		resourceBean.setStay(resource.getStay());		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Resource.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();	
			StringReader reader = new StringReader(resource.getDetailsXml());
			Resource resourceObj = (Resource) unmarshaller.unmarshal(reader);
			// Set Month Totals
			for(Month month : resourceObj.getMonths().getMonth()){
				month.calculateTotal();
			}			
			resourceBean.setMonths(resourceObj.getMonths());			
		}catch(JAXBException e){
			e.printStackTrace();
		}		
		return resourceBean;
	}
}
