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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import org.qi4j.spi.object.InvocationInstance;
import org.qi4j.spi.object.ProxyReferenceInvocationHandler;

public final class InvocationInstanceImpl
    implements InvocationInstance
{
    private Object firstModifier;
    private Object lastModifier;
    private FragmentInvocationHandler mixinInvocationHandler;
    private ProxyReferenceInvocationHandler proxyHandler;
    private List<InvocationInstance> pool;

    public InvocationInstanceImpl( Object aFirstModifier, Object aLastModifier, FragmentInvocationHandler aMixinInvocationHandler, ProxyReferenceInvocationHandler aProxyHandler, List<InvocationInstance> aPool )
    {
        firstModifier = aFirstModifier;
        lastModifier = aLastModifier;
        proxyHandler = aProxyHandler;
        mixinInvocationHandler = aMixinInvocationHandler;
        pool = aPool;
    }

    public Object invoke( Object proxy, Method method, Object[] args, Object mixin ) throws Throwable
    {
        try
        {
            if (firstModifier == null)
            {
                if (mixin instanceof InvocationHandler)
                    return ((InvocationHandler)mixin).invoke( proxy, method, args);
                else
                    return method.invoke( mixin, args);
            } else
            {
                proxyHandler.setContext( proxy, mixin);
                if (mixinInvocationHandler != null)
                    mixinInvocationHandler.setFragment( mixin );
                if (firstModifier instanceof InvocationHandler)
                    return ((InvocationHandler)firstModifier).invoke( proxy, method, args);
                else
                    return method.invoke( firstModifier, args);
            }
        }
        finally
        {
            pool.add( this );
        }
    }
}
