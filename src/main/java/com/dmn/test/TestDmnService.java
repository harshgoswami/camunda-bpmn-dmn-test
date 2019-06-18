package com.dmn.test;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class TestDmnService implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {

		Map<String, Object> variables = execution.getVariables();
		if (!variables.isEmpty()) {
			System.out.println("vars not empty");
		} else {
			System.out.println("vars empty");
		}
	}

}
