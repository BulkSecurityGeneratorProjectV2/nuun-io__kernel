/**
 * Copyright (C) 2014 Kametic <epo.jemba@kametic.com>
 * <p/>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nuun.kernel.core.internal.scanner.inmemory;

import io.nuun.kernel.api.inmemory.ClasspathClass;
import io.nuun.kernel.api.inmemory.ClasspathDirectory;
import io.nuun.kernel.api.inmemory.ClasspathJar;
import io.nuun.kernel.core.internal.scanner.AbstractClasspathScanner;
import io.nuun.kernel.core.internal.scanner.ClasspathScannerTestBase;
import io.nuun.kernel.core.internal.scanner.sample.*;
import io.nuun.kernel.core.pluginsit.dummy7.Module7;

import static io.nuun.kernel.api.inmemory.ClasspathResource.res;

/**
 * @author epo.jemba{@literal @}kametic.com
 */
public class ClasspathScannerInMemoryTest extends ClasspathScannerTestBase
{

    private InMemoryMultiThreadClasspath classpath = InMemoryMultiThreadClasspath.INSTANCE;

    @SuppressWarnings("unchecked")
    @Override
    protected AbstractClasspathScanner createUnderTest()
    {
        classpath.reset();
        classpath
                .add(ClasspathDirectory.create("default")
                        .add(res("META-INF/properties", "tst-one.properties"))
                        .add(res("META-INF/properties", "tst-two.properties"))
                )
                .add(ClasspathJar.create("app.jar")
                        .add(new ClasspathClass(Bean1.class))
                        .add(new ClasspathClass(Bean2.class))
                        .add(new ClasspathClass(Bean3.class))
                        .add(new ClasspathClass(Bean6.class))
                )
                .add(ClasspathJar.create("modules.jar")
                        .add(new ClasspathClass(MyModule1.class))
                        .add(new ClasspathClass(MyModule4.class))
                );

        return new ClasspathScannerInMemory(classpath);
    }
}
