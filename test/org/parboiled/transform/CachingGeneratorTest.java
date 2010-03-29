/*
 * Copyright (C) 2009-2010 Mathias Doenitz
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

package org.parboiled.transform;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.List;

import static org.parboiled.transform.AsmTestUtils.assertTraceDumpEquality;

public class CachingGeneratorTest extends TransformationTest {

    @SuppressWarnings({"unchecked"})
    @Test
    public void test() throws Exception {
        List<RuleMethodProcessor> processors = ImmutableList.<RuleMethodProcessor>of(
                new SuperCallRewriter(),
                new LabellingGenerator(),
                new LeafingGenerator(),
                new CachingGenerator()
        );

        assertTraceDumpEquality(processMethod("RuleWithoutAction", processors), "" +
                "    ALOAD 0\n" +
                "    GETFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW org/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL org/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Lorg/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL org/parboiled/transform/TestParser.RuleWithoutAction ()Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"RuleWithoutAction\"\n" +
                "    INVOKEINTERFACE org/parboiled/Rule.label (Ljava/lang/String;)Lorg/parboiled/Rule;\n" +
                "   L1\n" +
                "    DUP\n" +
                "    INSTANCEOF org/parboiled/matchers/AbstractMatcher\n" +
                "    IFEQ L2\n" +
                "    CHECKCAST org/parboiled/matchers/AbstractMatcher\n" +
                "    DUP\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/AbstractMatcher.lock ()V\n" +
                "   L2\n" +
                "    DUP_X1\n" +
                "    CHECKCAST org/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/ProxyMatcher.arm (Lorg/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Lorg/parboiled/Rule;\n" +
                "    ARETURN\n");

        assertTraceDumpEquality(processMethod("RuleWithNamedLabel", processors), "" +
                "  @Lorg/parboiled/support/Label;(value=\"harry\")\n" +
                "    ALOAD 0\n" +
                "    GETFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW org/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL org/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Lorg/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL org/parboiled/transform/TestParser.RuleWithNamedLabel ()Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"harry\"\n" +
                "    INVOKEINTERFACE org/parboiled/Rule.label (Ljava/lang/String;)Lorg/parboiled/Rule;\n" +
                "   L1\n" +
                "    DUP\n" +
                "    INSTANCEOF org/parboiled/matchers/AbstractMatcher\n" +
                "    IFEQ L2\n" +
                "    CHECKCAST org/parboiled/matchers/AbstractMatcher\n" +
                "    DUP\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/AbstractMatcher.lock ()V\n" +
                "   L2\n" +
                "    DUP_X1\n" +
                "    CHECKCAST org/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/ProxyMatcher.arm (Lorg/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Lorg/parboiled/Rule;\n" +
                "    ARETURN\n");

        assertTraceDumpEquality(processMethod("RuleWithLeaf", processors), "" +
                "    ALOAD 0\n" +
                "    GETFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW org/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL org/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Lorg/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL org/parboiled/transform/TestParser.RuleWithLeaf ()Lorg/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"RuleWithLeaf\"\n" +
                "    INVOKEINTERFACE org/parboiled/Rule.label (Ljava/lang/String;)Lorg/parboiled/Rule;\n" +
                "   L1\n" +
                "    DUP\n" +
                "    IFNULL L2\n" +
                "    INVOKEINTERFACE org/parboiled/Rule.asLeaf ()Lorg/parboiled/Rule;\n" +
                "   L2\n" +
                "    DUP\n" +
                "    INSTANCEOF org/parboiled/matchers/AbstractMatcher\n" +
                "    IFEQ L3\n" +
                "    CHECKCAST org/parboiled/matchers/AbstractMatcher\n" +
                "    DUP\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/AbstractMatcher.lock ()V\n" +
                "   L3\n" +
                "    DUP_X1\n" +
                "    CHECKCAST org/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL org/parboiled/matchers/ProxyMatcher.arm (Lorg/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD org/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Lorg/parboiled/Rule;\n" +
                "    ARETURN\n");
    }

}