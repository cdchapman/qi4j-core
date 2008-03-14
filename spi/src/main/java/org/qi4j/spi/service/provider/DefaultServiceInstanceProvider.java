/*
 * Copyright (c) 2007, Rickard Öberg. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.qi4j.spi.service.provider;

import org.qi4j.composite.Composite;
import org.qi4j.composite.CompositeBuilderFactory;
import org.qi4j.composite.ObjectBuilderFactory;
import org.qi4j.composite.scope.Structure;
import org.qi4j.service.ServiceInstanceProviderException;
import org.qi4j.spi.service.ServiceInstance;
import org.qi4j.spi.service.ServiceInstanceProvider;
import org.qi4j.spi.structure.ServiceDescriptor;

/**
 * Default service instance provider that simply uses
 * the CBF or OBF of the Module that the Service exists
 * in to instantiate an instance.
 */
public final class DefaultServiceInstanceProvider
    implements ServiceInstanceProvider
{
    private @Structure CompositeBuilderFactory cbf;
    private @Structure ObjectBuilderFactory obf;

    public Object newInstance( ServiceDescriptor descriptor ) throws ServiceInstanceProviderException
    {
        if( Composite.class.isAssignableFrom( descriptor.getServiceType() ) )
        {
            return cbf.newComposite( descriptor.getServiceType() );
        }
        else
        {
            return obf.newObject( descriptor.getServiceType() );
        }
    }

    public void releaseInstance( ServiceInstance instance )
    {
    }
}