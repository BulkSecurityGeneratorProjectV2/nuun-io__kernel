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
package io.nuun.kernel.tests.internal.dsl.holder;

import io.nuun.kernel.tests.ut.assertor.dsl.Wildcard;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;

/**
 *
 * 
 * @author epo.jemba@kametic.com
 * @author pierre.thirouin@gmail.com
 *
 */
public interface InjecteeHolder
{
    public void setInjecteeClass(Class<?> injecteeClass);
    
    public void setInjecteeKey(Key<?> injecteeKey);
    
    public void setInjecteeTypeLiteral(TypeLiteral<?> injecteeTypeLiteral);

    public void setInjecteeWildcard(Wildcard injecteeWildcard);
    
    public void setInjecteeTimes(Integer injecteeTimes);
    
}
