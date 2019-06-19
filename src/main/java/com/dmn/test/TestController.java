package com.dmn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private ProcessEngine processEngine;
	
	@RequestMapping(value = "/dmn/test", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse dmTest(@RequestBody DmnRequest request) throws FileNotFoundException {
		BaseResponse response = new BaseResponse();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", request.getItype());
		
//		DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
//		File initialFile = new File("D:\\IDD\\Projects\\camunda-bpmn-dmn-test\\src\\main\\resources\\decisionTable.dmn");
//	    InputStream inputStream = new FileInputStream(initialFile);
//		DmnDecision decision = dmnEngine.parseDecision("decideOnEntity", inputStream);
		
		//Parse DMN
//		 DecisionService decisionService = processEngine.getDecisionService();
//		DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("decideOnEntity", variables);
//	    [{typeOfEntity=Value 'INDIVIDUAL' of type 'PrimitiveValueType[string]'}]
//	    String desiredDish = dishDecisionResult.getSingleEntry();

		// Deploy BPMN and DMN
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("diagram_2.bpmn").deploy();
		
		repositoryService.createDeployment().addClasspathResource("decisionTable.dmn").name("decideOnEntity").deploy();

		runtimeService.startProcessInstanceByKey("bpmn_dmn", variables);

		return response;
	}
	
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse saveWorkFlows() {
		BaseResponse response = new BaseResponse();
		try {
			RepositoryService repositoryService = processEngine.getRepositoryService();
			repositoryService.createDeployment().addClasspathResource("Test1.bpmn").deploy();
			//Map<String, Object> variables = new HashMap<String, Object>();
			//ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Test1", variables);
			
			repositoryService
			    .createDeployment()
			    .addClasspathResource("decisionTable.dmn")
			    .name("DeploymentName")
			    .deploy();
			 
		} catch (Exception ex) {
		
		}
		return response;
	}
	
}
