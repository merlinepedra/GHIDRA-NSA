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
package ghidra.app.plugin.core.symtable;

import ghidra.program.model.listing.Program;
import ghidra.program.model.symbol.Symbol;

/**
 * <code>DeletedSymbolRowObject</code> provides a lightweight {@link Symbol}
 * table row object which may be used for a deleted symbol when attempting 
 * to update table model.
 */
class DeletedSymbolRowObject extends SymbolRowObject {

	DeletedSymbolRowObject(Program program, long symbolId) {
		super(program, symbolId);
	}

	@Override
	public Symbol getSymbol() {
		return null; // symbol no longer exists
	}

}
