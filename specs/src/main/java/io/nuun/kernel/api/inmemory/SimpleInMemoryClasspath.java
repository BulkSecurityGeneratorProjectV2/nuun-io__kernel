/**
 * Copyright (C) 2014 Kametic <epo.jemba@kametic.com>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nuun.kernel.api.inmemory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 
 * @author epo.jemba@kametic.com
 *
 */
public enum SimpleInMemoryClasspath implements InMemoryClasspath {

	INSTANCE;
    
    
    List<InMemoryClasspathEntry> entries;
	
	
	
	
	private SimpleInMemoryClasspath()
	{
		entries = new ArrayList<InMemoryClasspathEntry>();
	}
	
	
	public SimpleInMemoryClasspath add(InMemoryClasspathEntry entry)
	{
		entries.add(entry);
		return this;
	}
	
	public void reset()
	{
		entries.clear();
	}
	
	
	
	@Override
	public List<InMemoryClasspathEntry> entries()
	{
		return Collections.unmodifiableList(entries);
	}

}
