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
package io.nuun.kernel.core.internal.scanner.inmemory;

import io.nuun.kernel.api.inmemory.Resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 *
 * 
 * @author epo.jemba{@literal @}kametic.com
 *
 */
public class InMemoryFactory {
	
	private static final String INMEMORY = "inmemory";
	private InMemoryHandler handler;

	public InMemoryFactory() {
		handler = new InMemoryHandler();
	}
	
	 
	
	public URL createInMemoryClass (Class<?> claSs) throws MalformedURLException
	{
		return url( claSs.getName().replace('.', '/') + ".class");
	}
	
	public URL createInMemoryResource (String resource) throws MalformedURLException
	{
		String content = resource.replace('\\', '/');
		return url(content);
	}
	
	private URL url (String content) throws MalformedURLException
	{
		assertElementName(content);
		return new URL( (URL) null ,  INMEMORY	+ "://localhost/" + content , handler);
	}

    protected void assertElementName (String name)
    {
    	if ( ! Pattern.matches(Resource.PATTERN, name))
    	{
    		throw new IllegalArgumentException("\"" + name +"\" must be a valid ressource name : " + Resource.PATTERN );
    	}
    	
    }
	
}