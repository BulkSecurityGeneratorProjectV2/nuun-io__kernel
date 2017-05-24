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
package io.nuun.kernel.core;

import static io.nuun.kernel.core.NuunCore.createKernel;
import static io.nuun.kernel.core.NuunCore.newKernelConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import io.nuun.kernel.api.Kernel;
import io.nuun.kernel.core.internal.topology.TopologyPlugin;

public class KernelSuite10Test
{

    private Kernel underTest;

    @Before
    public void initkernel()
    {
        underTest = createKernel(

        newKernelConfiguration().rootPackages("io.nuun.kernel.core.test_topo_error1") //
                .withoutSpiPluginsLoader().plugins(new TopologyPlugin()));

        underTest.init();
        underTest.start();
    }

    @Test
    public void kernel_should_raise_error_on_bad_topology_01()
    {
        Injector injector = underTest.objectGraph().as(Injector.class);
   
    }

    @After
    public void stopKernel()
    {
        underTest.stop();
    }

}
