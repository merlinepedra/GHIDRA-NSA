/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.plugin.core.functiongraph;

import java.util.ArrayList;
import java.util.List;

import ghidra.app.plugin.core.functiongraph.graph.layout.FGLayout;
import ghidra.app.plugin.core.functiongraph.graph.layout.FGLayoutProvider;
import ghidra.app.plugin.core.functiongraph.graph.layout.jungrapht.JgtLayoutFactory;
import ghidra.app.plugin.core.functiongraph.graph.layout.jungrapht.JgtNamedLayoutProvider;
import ghidra.util.classfinder.ClassSearcher;

/**
 * Finds {@link FGLayout}s via the classpath
 */
public class DiscoverableFGLayoutFinder implements FGLayoutFinder {

	@Override
	public List<FGLayoutProvider> findLayouts() {

		List<FGLayoutProvider> list = new ArrayList<>();

		// add discovered layouts
		List<FGLayoutProvider> instances =
			ClassSearcher.getInstances(FGLayoutProvider.class);
		list.addAll(instances);

		// add hand-picked, generated layout providers
		List<String> jgtLayoutNames = JgtLayoutFactory.getSupportedLayoutNames();
		for (String name : jgtLayoutNames) {
			list.add(new JgtNamedLayoutProvider(name));
		}
		return list;
	}

}
