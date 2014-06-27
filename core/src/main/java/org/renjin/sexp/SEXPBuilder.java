/*
 * R : A Computer Language for Statistical Data Analysis
 * Copyright (C) 1995, 1996  Robert Gentleman and Ross Ihaka
 * Copyright (C) 1997-2008  The R Development Core Team
 * Copyright (C) 2003, 2004  The R Foundation
 * Copyright (C) 2010 bedatadriven
 * Copyright (C) 2014 Ruslan Shevchenko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.renjin.sexp;

import org.renjin.eval.Context;

/**
 * Base builder for SEXP.  
 */
public interface SEXPBuilder {

  /**
   * build SEXP
   */
  SEXP build();

  /**
   * return length of SEXP which is already build.
   */
  int length();

  /**
   * set attribute.
   */
  SEXPBuilder setAttribute(String attributeName, SEXP value);

  /**
   * set attibutr
   */
  SEXPBuilder setAttribute(Symbol attributeName, SEXP value);
  
}
