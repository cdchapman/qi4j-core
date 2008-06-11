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

package org.qi4j.spi;

import org.qi4j.Qi4j;
import org.qi4j.composite.Composite;
import org.qi4j.spi.composite.CompositeDescriptor;
import org.qi4j.structure.Module;

/**
 * Encapsulation of the Qi4j SPI. The Qi4jSPI holds references
 * to all the SPI objects in a Qi4j runtime.
 */
public interface Qi4jSPI
    extends Qi4j
{
    CompositeDescriptor getCompositeDescriptor( Composite composite );

    CompositeDescriptor getCompositeDescriptor( Class<? extends Composite> compositeType, Module module );
}
