package org.renjin.gcc.codegen.fatptr;

import org.renjin.gcc.codegen.MethodGenerator;
import org.renjin.gcc.codegen.expr.*;
import org.renjin.gcc.codegen.var.LocalVarAllocator;
import org.renjin.repackaged.asm.Type;

public class DereferencedFatPtr implements RefPtrExpr, FatPtr {

  private final ValueFunction valueFunction;
  private JExpr array;
  private JExpr offset;

  public DereferencedFatPtr(JExpr array, JExpr offset, ValueFunction valueFunction) {
    this.array = array;
    this.offset = offset;
    this.valueFunction = valueFunction;
  }

  public JExpr getOffset() {
    return offset;
  }

  public JExpr getArray() {
    return array;
  }

  private ArrayElement element() {
    return Expressions.elementAt(array, offset);
  }

  @Override
  public JExpr unwrap() {
    return element();
  }

  @Override
  public void store(MethodGenerator mv, GExpr rhs) {

    if(rhs instanceof FatPtr) {
      element().store(mv, ((FatPtr) rhs).wrap());

    } else {
      throw new UnsupportedOperationException("TODO: " + rhs.getClass().getName());
    }
  }

  @Override
  public GExpr addressOf() {
    return new FatPtrPair(array, offset);
  }

  @Override
  public Type getValueType() {
    return valueFunction.getValueType();
  }

  @Override
  public boolean isAddressable() {
    return false;
  }

  @Override
  public JExpr wrap() {
    return element();
  }

  @Override
  public FatPtrPair toPair(MethodGenerator mv) {
    Type wrapperType = Wrappers.wrapperType(valueFunction.getValueType());
    LocalVarAllocator.LocalVar wrapper = mv.getLocalVarAllocator().reserve(wrapperType);
    wrapper.store(mv, element());

    return toPair(wrapper);
  }
  
  @Override
  public FatPtrPair toPair() {
    return toPair(element());
  }

  private FatPtrPair toPair(JExpr wrapper) {
    JExpr array = Wrappers.arrayField(wrapper, valueFunction.getValueType());
    JExpr offset = Wrappers.offsetField(wrapper);

    return new FatPtrPair(array, offset);
  }
}
