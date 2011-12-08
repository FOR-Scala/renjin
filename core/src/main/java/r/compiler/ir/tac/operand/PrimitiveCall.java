package r.compiler.ir.tac.operand;

import java.util.List;

import com.google.common.base.Joiner;

import r.base.Primitives;
import r.lang.Context;
import r.lang.FunctionCall;
import r.lang.PairList;
import r.lang.PrimitiveFunction;
import r.lang.SEXP;
import r.lang.StrictPrimitiveFunction;
import r.lang.Symbol;

/**
 * 
 */
public class PrimitiveCall implements Operand {

  private Symbol name;
  private final List<Operand> arguments;
  private PrimitiveFunction function;
  private SEXP[] argumentValues;
  
  public PrimitiveCall(Symbol name, List<Operand> arguments) {
    super();
    this.name = name;
    this.arguments = arguments;
    this.function = Primitives.getBuiltin(name);
    this.argumentValues = new SEXP[arguments.size()];
  }

  @Override
  public Object retrieveValue(Context context, Object[] temps) {
    // build argument list 
    if(function instanceof StrictPrimitiveFunction) {
      for(int i=0;i!=argumentValues.length;++i) {
        argumentValues[i] = (SEXP)arguments.get(i).retrieveValue(context, temps);
      }
      return ((StrictPrimitiveFunction) function).applyStrict(context, context.getEnvironment(), argumentValues);
    } else {
      PairList.Builder argList = new PairList.Builder();
      for(Operand operand : arguments) {
        argList.add((SEXP)operand.retrieveValue(context, temps));
      }
   
      PairList args = argList.build();
      FunctionCall call = new FunctionCall(name, args);
      return function.apply(context, context.getEnvironment() , call, args);
    }
  }
  
  @Override
  public String toString() {
    String statement;
    if(name.getPrintName().equals(">") || name.getPrintName().equals("<")) {
      statement = "primitive< " + name + " >";
    } else {
      statement = "primitive<" + name + ">";
    }
    return statement + "(" + Joiner.on(", ").join(arguments) + ")";
  }  
}