package com.eloraam.redpower.core;

public abstract interface IRedPowerWiring
  extends IRedPowerConnectable, IWiring
{
  public abstract int scanPoweringStrength(int paramInt1, int paramInt2);
  
  public abstract int getCurrentStrength(int paramInt1, int paramInt2);
  
  public abstract void updateCurrentStrength();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IRedPowerWiring
 * JD-Core Version:    0.7.0.1
 */