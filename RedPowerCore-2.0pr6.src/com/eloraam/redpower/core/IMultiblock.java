package com.eloraam.redpower.core;

import aoe;
import qx;

public abstract interface IMultiblock
{
  public abstract void onMultiRemoval(int paramInt);
  
  public abstract aoe getMultiBounds(int paramInt);
  
  public abstract float getMultiBlockStrength(int paramInt, qx paramqx);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IMultiblock
 * JD-Core Version:    0.7.0.1
 */