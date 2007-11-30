/*
 * Copyright 2006 Niclas Hedhman.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.qi4j.query.operators;

import org.qi4j.query.BinaryOperator;
import org.qi4j.query.BooleanExpression;
import org.qi4j.query.Expression;

public class And
    implements BooleanExpression, BinaryOperator
{
    private BooleanExpression left;
    private BooleanExpression right;

    public And( BooleanExpression left, BooleanExpression right )
    {
        this.left = left;
        this.right = right;
    }

    public Expression getLeftArgument()
    {
        return left;
    }

    public Expression getRightArgument()
    {
        return right;
    }

    public String toString()
    {
        return "(" + left + " AND " + right + ")";
    }
}