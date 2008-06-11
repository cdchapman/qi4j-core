package org.qi4j.runtime.injection.provider;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.qi4j.runtime.composite.qi.Resolution;
import org.qi4j.runtime.injection.DependencyModel;
import org.qi4j.runtime.injection.InjectionContext;
import org.qi4j.runtime.injection.InjectionProvider;
import org.qi4j.runtime.injection.InjectionProviderFactory;

/**
 * TODO
 */
public final class ThisInjectionProviderFactory
    implements InjectionProviderFactory
{
    public InjectionProvider newInjectionProvider( Resolution bindingContext, DependencyModel dependencyModel ) throws InvalidInjectionException
    {
        if( bindingContext.composite() != null )
        {
            // If Composite type then return real type, otherwise use the specified one
            Class thisType = dependencyModel.rawInjectionType();

            if( thisType.isAssignableFrom( bindingContext.composite().type() ) )
            {
                thisType = bindingContext.composite().type();
            }

            return new ThisInjectionProvider( thisType );

/* TODO Needs to be fixed to support internal mixins
            // Check if the composite implements the desired type
            if( dependencyModel.getRawType().isAssignableFrom( fragmentKey.getCompositeType() ) )
            {
                return new ThisInjectionProvider(dependencyModel.getRawType());
            }
            else
            {
                throw new InvalidInjectionException( "Composite " + fragmentKey.getCompositeType() + " does not implement @This type " + dependencyModel.getDependencyType() + " in fragment " + dependencyModel.getDependentType() );
            }
*/
        }
        else
        {
            throw new InvalidInjectionException( "Object " + dependencyModel.injectedClass() + " may not use @This" );
        }
    }

    private class ThisInjectionProvider implements InjectionProvider
    {
        Constructor proxyConstructor;

        public ThisInjectionProvider( Class type )
        {
            try
            {
                proxyConstructor = Proxy.getProxyClass( type.getClassLoader(), new Class[]{ type } ).getConstructor( InvocationHandler.class );
            }
            catch( Exception e )
            {
                // Ignore
                e.printStackTrace();
            }
        }

        public Object provideInjection( InjectionContext context )
        {
            try
            {
                InvocationHandler handler = context.compositeInstance();
                return proxyConstructor.newInstance( handler );
            }
            catch( Exception e )
            {
                throw new InjectionProviderException( "Could not instantiate @This proxy", e );
            }
        }
    }
}