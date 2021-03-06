/*
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2018 BeDataDriven Groep B.V. and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, a copy is available at
 * https://www.gnu.org/licenses/gpl-2.0.txt
 */
package org.renjin.primitives.summary;

import org.junit.Test;
import org.renjin.EvalTestCase;

import static org.junit.Assert.assertThat;

public class DeferredSummaryTest extends EvalTestCase {

  @Test
  public void summation() {
    // for large vectors, sum() returns a DeferredSummary
    eval(" x <- sum(as.double(1:1e5)) ");

    // for evaluation with I/O
    eval(" print(x) ");

    // ensure that the result is cached and correct
    assertThat(eval("x"), elementsIdenticalTo(c(5000050000d)));
  }
  
  @Test
  public void onePlaced() {
    eval(" x <- sum(as.double(1:1e5)) ");
    eval(" y <- c(1.5, 2.5) ");
    eval(" dim(y) <- c(2, 1) ");
    
    eval(" print(x) ");
    
    eval("y[1,1] <- x");
    
  }
}
