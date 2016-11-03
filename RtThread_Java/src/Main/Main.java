
package Main;

import org.lttng.ust.agent.LTTngAgent;
import Scenario.Scenario;

@SuppressWarnings("deprecation")
public class Main {

	public static void main(String[] args) {

		LTTngAgent lttngAgent = LTTngAgent.getLTTngAgent();
		new Scenario();

		lttngAgent.dispose();
		// new Scenario_ParamError();
	}
}
