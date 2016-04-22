/**
 * This file is part of Nuun IO Kernel Core.
 *
 * Nuun IO Kernel Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Nuun IO Kernel Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Nuun IO Kernel Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.fixture.scan;

import com.google.common.collect.Sets;
import io.nuun.kernel.core.AbstractPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class AdditionalClasspathPlugin extends AbstractPlugin {
    public static final String NAME = "additional-classpath";
    public static final URL URL;

    static {
        try {
            URL = new URL("file:///additional/url.jar");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Set<URL> computeAdditionalClasspathScan() {
        return Sets.newHashSet(URL);
    }
}