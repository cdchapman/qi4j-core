/*
 * Copyright (c) 2007, Rickard Öberg. All Rights Reserved.
 * Copyright (c) 2007, Niclas Hedhman. All Rights Reserved.
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
package org.qi4j.runtime;

import org.qi4j.api.ObjectFactory;
import org.qi4j.api.FragmentFactory;
import org.qi4j.api.Composite;
import org.qi4j.spi.object.ObjectContext;
import org.qi4j.spi.object.InvocationInstance;
import org.qi4j.spi.object.InvocationInstancePool;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.List;
import java.util.IdentityHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 */
public final class ObjectContextImpl
    implements ObjectContext
{
    private Composite composite;
    private ObjectFactory objectFactory;
    private FragmentFactory fragmentFactory;
    private InvocationInstancePool pool;
    private ConcurrentHashMap<Method, List<InvocationInstance>> methodToInvocationInstanceMap;

    public ObjectContextImpl( Composite aComposite, ObjectFactory aObjectFactory, FragmentFactory aFragmentFactory, InvocationInstancePool instancePool)
    {
        composite = aComposite;
        objectFactory = aObjectFactory;
        fragmentFactory = aFragmentFactory;
        pool = instancePool;
        methodToInvocationInstanceMap = instancePool.getPool( aComposite.getCompositeClass());
    }

    public Composite getComposite()
    {
        return composite;
    }

    public ObjectFactory getObjectFactory()
    {
        return objectFactory;
    }

    public FragmentFactory getMixinFactory()
    {
        return fragmentFactory;
    }

    public InvocationInstancePool getPool()
    {
        return pool;
    }

    public InvocationInstance newInvocationInstance( Method method)
    {
        List<InvocationInstance> instances = methodToInvocationInstanceMap.get( method );
        return pool.newInstance( method, composite , instances );
    }

    public ConcurrentHashMap<Method, List<InvocationInstance>> getMethodToInvocationInstanceMap()
    {
        return methodToInvocationInstanceMap;
    }
}
