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
package ghidra.file.formats.coff;

import java.util.Arrays;

import java.io.IOException;

import ghidra.app.util.bin.ByteProvider;
import ghidra.app.util.bin.format.coff.archive.CoffArchiveConstants;
import ghidra.formats.gfilesystem.*;
import ghidra.formats.gfilesystem.factory.GFileSystemFactoryByteProvider;
import ghidra.formats.gfilesystem.factory.GFileSystemProbeBytesOnly;
import ghidra.util.exception.CancelledException;
import ghidra.util.task.TaskMonitor;

public class CoffArchiveFileSystemFactory implements
		GFileSystemFactoryByteProvider<CoffArchiveFileSystem>, GFileSystemProbeBytesOnly {

	public static final int PROBE_BYTES_REQUIRED = CoffArchiveConstants.MAGIC_LEN;

	@Override
	public CoffArchiveFileSystem create(FSRLRoot targetFSRL, ByteProvider byteProvider,
			FileSystemService fsService, TaskMonitor monitor)
			throws IOException, CancelledException {

		CoffArchiveFileSystem fs = new CoffArchiveFileSystem(targetFSRL, byteProvider);
		fs.mount(monitor);
		return fs;
	}

	@Override
	public int getBytesRequired() {
		return PROBE_BYTES_REQUIRED;
	}

	@Override
	public boolean probeStartBytes(FSRL containerFSRL, byte[] startBytes) {
		return Arrays.equals(
			CoffArchiveConstants.MAGIC_BYTES, 0, CoffArchiveConstants.MAGIC_BYTES.length,
			startBytes, 0, CoffArchiveConstants.MAGIC_BYTES.length);
	}
}
