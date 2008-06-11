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

package org.qi4j.bootstrap;

import java.io.Serializable;
import java.util.List;
import org.qi4j.entity.EntityComposite;
import org.qi4j.runtime.entity.EntityModel;
import org.qi4j.runtime.structure.qi.ModuleModel;
import org.qi4j.structure.Visibility;
import org.qi4j.util.MetaInfo;

/**
 * Declaration of a Composite. Created by {@link ModuleAssembly#addComposites(Class[])}.
 */
public final class EntityDeclaration
{
    private Class<? extends EntityComposite>[] compositeTypes;
    private MetaInfo metaInfo = new MetaInfo();
    private Visibility visibility = Visibility.module;

    public EntityDeclaration( Class<? extends EntityComposite>... compositeTypes )
    {
        this.compositeTypes = compositeTypes;
    }

    public <T extends Serializable> EntityDeclaration setMetaInfo( Serializable info )
    {
        metaInfo.set( info );
        return this;
    }

    public EntityDeclaration visibleIn( Visibility visibility )
    {
        this.visibility = visibility;
        return this;
    }

    void addEntities( ModuleModel moduleModel, List<EntityModel> entities )
    {
        for( Class<? extends EntityComposite> compositeType : compositeTypes )
        {
            EntityModel compositeModel = EntityModel.newModel( compositeType,
                                                               visibility,
                                                               new MetaInfo( metaInfo ),
                                                               moduleModel );
            entities.add( compositeModel );
        }
    }
}