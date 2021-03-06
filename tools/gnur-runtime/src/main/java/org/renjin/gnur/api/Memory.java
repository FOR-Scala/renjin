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
// Initial template generated from Memory.h from R 3.2.2
package org.renjin.gnur.api;

import org.renjin.gcc.annotations.Noop;

/**
 * GNU R API methods defined in the "R_ext/Memory.h" header file
 */
@SuppressWarnings("unused")
public final class Memory {

  private Memory() {
  }


  public static Object vmaxget() {
    // NOOP
    return null;
  }

  @Noop
  public static void vmaxset(Object p0) {
    // NOOP
  }

  /**
   * Intended to invoke the R garbage collector but implemented to have no effect in Renjin.
   */
  @Noop
  public static void R_gc() {
    // NOOP
  }

  /**
   * Intended to return TRUE if the garbage collector is running. Renjin's implementation always returns FALSE.
   */
  public static int R_gc_running() {
    return 0;
  }

}
