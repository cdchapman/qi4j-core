/*  Copyright 2007 Niclas Hedhman.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.qi4j.test.model1;

import org.qi4j.api.Composite;
import org.qi4j.api.annotation.ModifiedBy;
import org.qi4j.test.model1.Modifier5;

/** This will be to test some error case.
 *
 */
@ModifiedBy( Modifier5.class )
public interface Composition3
    extends Composite
{
}