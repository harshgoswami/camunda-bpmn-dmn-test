package com.dmn.test;

import java.util.HashMap;
import java.util.Map;

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
	
	@RequestMapping(value = "/dmn/test", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse dmTest(@RequestBody DmnRequest request) {
		BaseResponse response = new BaseResponse();
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String, Object> name = new HashMap<String, Object>();
		name.put("value", "E");
		name.put("type", "string");
		variables.put("name", name);
		// DmnEngine dmnEngine =
		// DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
		// InputStream inputStream =
		// EntityController.class.getResourceAsStream("decisionTable.dmn");
		// DmnDecision decision = dmnEngine.parseDecision("decideOnEntity",
		// inputStream);

		runtimeService.startProcessInstanceByKey("bpmn_dmn", variables);

		return response;
	}
}
