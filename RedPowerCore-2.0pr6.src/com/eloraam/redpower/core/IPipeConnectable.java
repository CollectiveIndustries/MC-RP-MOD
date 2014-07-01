package com.eloraam.redpower.core;

public abstract interface IPipeConnectable
{
  public abstract int getPipeConnectableSides();
  
  public abstract int getPipeFlangeSides();
  
  public abstract int getPipePressure(int paramInt);
  
  public abstract FluidBuffer getPipeBuffer(int paramInt);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IPipeConnectable
 * JD-Core Version:    0.7.0.1
 */