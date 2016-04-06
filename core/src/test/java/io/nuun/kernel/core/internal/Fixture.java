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
package io.nuun.kernel.core.internal;

import com.google.common.collect.Lists;
import io.nuun.kernel.api.Kernel;
import io.nuun.kernel.api.config.KernelConfiguration;
import io.nuun.kernel.api.config.KernelOptions;
import io.nuun.kernel.core.NuunCore;

public class Fixture
{
    public static KernelConfiguration config()
    {
        return NuunCore.newKernelConfiguration()
                .option(KernelOptions.ROOT_PACKAGES, Lists.newArrayList("io.nuun.kernel"))
                .option(KernelOptions.SCAN_PLUGIN, false);
    }

    public static KernelConfiguration configWithScan()
    {
        return NuunCore.newKernelConfiguration()
                .option(KernelOptions.ROOT_PACKAGES, Lists.newArrayList("io.nuun.kernel"));
    }

    public static Kernel startKernel(KernelConfiguration kernelConfiguration)
    {
        Kernel kernel = NuunCore.createKernel(kernelConfiguration);
        kernel.init();
        kernel.start();
        return kernel;
    }

    public static Kernel initKernel(KernelConfiguration kernelConfiguration)
    {
        Kernel kernel = NuunCore.createKernel(kernelConfiguration);
        kernel.init();
        return kernel;
    }
}