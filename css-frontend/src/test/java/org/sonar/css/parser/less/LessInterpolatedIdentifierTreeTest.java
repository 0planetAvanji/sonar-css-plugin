/*
 * SonarQube CSS / SCSS / Less Analyzer
 * Copyright (C) 2013-2017 David RACODON
 * mailto: david.racodon@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.css.parser.less;

import org.junit.Test;
import org.sonar.css.parser.LexicalGrammar;
import org.sonar.plugins.css.api.tree.css.IdentifierTree;

import static org.fest.assertions.Assertions.assertThat;

public class LessInterpolatedIdentifierTreeTest extends LessTreeTest {

  public LessInterpolatedIdentifierTreeTest() {
    super(LexicalGrammar.LESS_INTERPOLATED_IDENTIFIER);
  }

  @Test
  public void lessInterpolatedIdentifier() {
    checkParsed(" -moz-abc@{class}e@{class1}def", "-moz-abc@{class}e@{class1}def");
  }

  @Test
  public void notLessInterpolatedIdentifier() {
    checkNotParsed(" *");
    checkNotParsed(" abc");
    checkNotParsed(" .");
    checkNotParsed(" .class");
    checkNotParsed(" .class.class");
    checkNotParsed(" 123");
    checkNotParsed(" 123px");
  }

  private void checkParsed(String toParse, String expectedIdent) {
    IdentifierTree tree = (IdentifierTree) parser().parse(toParse);
    assertThat(tree.value()).isNotNull();
    assertThat(tree.text()).isEqualTo(expectedIdent);
    assertThat(tree.isLessInterpolated()).isTrue();
    assertThat(tree.isInterpolated()).isTrue();
  }

  private void checkParsed(String toParse) {
    checkParsed(toParse, toParse);
  }

}
