/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.matchers;

import org.jetbrains.annotations.NotNull;
import org.parboiled.MatcherContext;
import org.parboiled.common.Preconditions;
import org.parboiled.support.Characters;
import org.parboiled.support.Chars;

public class CharRangeMatcher<V> extends AbstractMatcher<V> {

    public final char cLow;
    public final char cHigh;

    public CharRangeMatcher(char cLow, char cHigh) {
        Preconditions.checkArgument(cLow < cHigh && !Chars.isSpecial(cLow) && !Chars.isSpecial(cHigh));
        this.cLow = cLow;
        this.cHigh = cHigh;
    }

    @Override
    public String getLabel() {
        return hasLabel() ? super.getLabel() : cLow + ".." + cHigh;
    }

    public boolean match(@NotNull MatcherContext<V> context) {
        char c = context.getCurrentLocation().currentChar;
        if (c < cLow || c > cHigh) return false;

        context.advanceInputLocation();
        context.createNode();
        return true;
    }

    public Characters getStarterChars() {
        Characters chars = Characters.NONE;
        for (char c = cLow; c <= cHigh; c++) {
            chars = chars.add(c);
        }
        return chars;
    }

}