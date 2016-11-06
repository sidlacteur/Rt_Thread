
package Main;

import org.lttng.ust.agent.LTTngAgent;
//import Scenario.Scenario_ParamError;

import scenario.Scenario_ParamError;

public class Main {

	public static void main(String[] args) {
		LTTngAgent lttngAgent = LTTngAgent.getLTTngAgent();
		// new Scenario();
		// new ScenarioWithDiffPrio();
		// new scenario.ScenarioThreadPrio99();
		new Scenario_ParamError();
		lttngAgent.dispose();
		// new Scenario_ParamError();
	}
}
