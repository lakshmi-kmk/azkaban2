package azkaban.test.trigger;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import azkaban.actions.ExecuteFlowAction;
import azkaban.executor.ExecutionOptions;
import azkaban.trigger.ActionTypeLoader;
import azkaban.trigger.TriggerAction;
import azkaban.trigger.TriggerException;
import azkaban.utils.Props;


public class ExecuteFlowActionTest {
	
	@Test
	public void jsonConversionTest() throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, TriggerException {
		ActionTypeLoader loader = new ActionTypeLoader();
		loader.init(new Props());
		
		ExecutionOptions options = new ExecutionOptions();
		List<String> disabledJobs = new ArrayList<String>();
		options.setDisabledJobs(disabledJobs);
		
		ExecuteFlowAction executeFlowAction = new ExecuteFlowAction(1, "testproject", "testflow", "azkaban", options);
		
		Object obj = executeFlowAction.toJson();
		
		ExecuteFlowAction action = (ExecuteFlowAction) loader.createActionFromJson(ExecuteFlowAction.type, obj);
		assertTrue(executeFlowAction.getProjectId() == action.getProjectId());
		assertTrue(executeFlowAction.getFlowName().equals(action.getFlowName()));
		assertTrue(executeFlowAction.getSubmitUser().equals(action.getSubmitUser()));
	}

	
	
}
