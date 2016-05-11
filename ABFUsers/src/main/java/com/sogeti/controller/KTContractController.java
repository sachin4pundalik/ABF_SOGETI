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
import com.sogeti.db.models.KtContract;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.KTContractResourceBean;
import com.sogeti.service.KtContractService;
import com.sogeti.xmlbeans.Month;
import com.sogeti.xmlbeans.Resource;

@RestController
@RequestMapping(value = "/kthours")
public class KTContractController {	

	
	private static final Logger logger = Logger.getLogger(KTContractController.class);

	@Autowired(required = true)
	KtContractService ktContractService;
	
	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse createKtContract(@RequestBody List<KtContract> contractResources) {
		
		return null;
	}
	
	@RequestMapping( value = "/fetchcontractkthours/{contractId}", method = RequestMethod.GET)
	public ABFResponse getAmContractForContract(@PathVariable("contractId") String contractId){
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
			boolean deleteFlag = ktContractService.deleteKtContract(ktContract);
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
