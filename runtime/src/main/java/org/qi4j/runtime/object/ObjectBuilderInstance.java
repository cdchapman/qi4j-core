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

package org.qi4j.runtime.object;

import java.util.Iterator;
import org.qi4j.composite.InstantiationException;
import org.qi4j.object.ObjectBuilder;
import org.qi4j.runtime.composite.qi.UsesInstance;
import org.qi4j.runtime.structure.qi.ModuleInstance;

/**
 * TODO
 */
public class ObjectBuilderInstance<T>
    implements ObjectBuilder<T>
{

    protected ModuleInstance moduleInstance;
    protected ObjectModel objectModel;
    private UsesInstance uses;
    private Class<T> objectType;

    public ObjectBuilderInstance( ModuleInstance moduleInstance, ObjectModel objectModel )
    {
        this.moduleInstance = moduleInstance;

        this.objectModel = objectModel;
        objectType = (Class<T>) objectModel.type();
    }

    public Class<T> objectType()
    {
        return objectType;
    }

    public ObjectBuilder<T> use( Object... usedObjects )
    {
        getUses().use( usedObjects );

        return this;
    }

    public T newInstance() throws InstantiationException
    {
        Object instance = objectModel.newInstance( moduleInstance, uses == null ? UsesInstance.NO_USES : uses );
        return objectType.cast( instance );
    }

    public void injectTo( T instance ) throws InstantiationException
    {
        objectModel.inject( moduleInstance, uses == null ? UsesInstance.NO_USES : uses, instance );
    }

    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            public boolean hasNext()
            {
                return true;
            }

            public T next()
            {
                return newInstance();
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    protected UsesInstance getUses()
    {
        if( uses == null )
        {
            uses = new UsesInstance();
        }
        return uses;
    }
}