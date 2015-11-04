/**
 * Copyright (C) 2013-2014 Kametic <epo.jemba@kametic.com>
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
package io.nuun.kernel.core.pluginsit.dummy6;

import com.google.common.collect.Lists;
import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.api.plugin.request.BindingRequest;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequest;
import io.nuun.kernel.core.AbstractPlugin;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
/**
 * 
 * A 2 rounds plugin
 * 
 * @author ejemba
 *
 */
public class DummyPlugin6_B extends AbstractPlugin
{

    public DummyPlugin6_B()
    {
    }
    
    @Override
    public String name()
    {
        return "dummy-plugin-6-B";
    }

    @Override
    public Collection<Class<?>> dependentPlugins()
    {
        if (round != null && round.isFirst())
        {
            return Lists.<Class<?>>newArrayList(DummyPlugin6_D.class, DummyPlugin6_C.class);
        }
        else
        {
            return super.dependentPlugins();
        }
    }
    
    @Override
    public Collection<BindingRequest> bindingRequests()
    {
        if (round.number() == 3)
        {
            return bindingRequestsBuilder().annotationType(Marker66.class).build();
        }
        return super.bindingRequests();
    }
    
    @Override
    public Collection<ClasspathScanRequest> classpathScanRequests()
    {
        if (round.number() == 4)
        {
            return classpathScanRequestBuilder().annotationType(Marker6.class).build();
        }
        
        return super.classpathScanRequests();
    }

    @Override
    public InitState init(InitContext initContext)
    {
        if (round.number() != 5)
        {
            Collection<?> dependentPlugins = initContext.dependentPlugins();
            assertThat(dependentPlugins).isNotNull();
            if (round.isFirst())
            {
                assertThat(dependentPlugins).hasSize(2);
            }
            else
            {
                assertThat(dependentPlugins).hasSize(0);
            }

            if (round.number() == 4)
            {
                Collection<Class<?>> collection = initContext.scannedClassesByAnnotationClass().get(Marker6.class);
                assertThat(collection).hasSize(2);
            }
            else
            {
                Collection<Class<?>> collection = initContext.scannedClassesByAnnotationClass().get(Marker6.class);
                assertThat(collection).isNullOrEmpty();
            }

            for (Object plugin : dependentPlugins)
            {
                if (DummyPlugin6_D.class.isAssignableFrom(plugin.getClass())) 
                {
                    DummyPlugin6_D.class.cast(plugin).setInternal(true);
                }
                if (DummyPlugin6_C.class.isAssignableFrom(plugin.getClass())) 
                {
                    DummyPlugin6_C.class.cast(plugin).setInternal(true);
                }
            }
        
            return InitState.NON_INITIALIZED;
        } 
        else 
        {
            return InitState.INITIALIZED;
        }
    }

    @Override
    public String toString()
    {
        return "B";
    }
    
}
