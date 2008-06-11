/*
 * Copyright (c) 2008, Rickard Öberg. All Rights Reserved.
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

package org.qi4j.runtime.injection;

import org.qi4j.runtime.composite.qi.DependencyModel;
import org.qi4j.runtime.composite.qi.InjectionProvider;
import org.qi4j.runtime.composite.qi.InjectionProviderFactory;
import org.qi4j.runtime.composite.qi.Resolution;

/**
 * TODO
 */
public class CachingInjectionProviderFactoryDecorator
    implements InjectionProviderFactory
{
    private InjectionProviderFactory decoratedFactory;

    public CachingInjectionProviderFactoryDecorator( InjectionProviderFactory decoratedFactory )
    {
        this.decoratedFactory = decoratedFactory;
    }

    public InjectionProvider newInjectionProvider( Resolution resolution, DependencyModel dependencyModel ) throws InvalidInjectionException
    {
        return new CachingInjectionProviderDecorator( decoratedFactory.newInjectionProvider( resolution, dependencyModel ) );
    }
}
