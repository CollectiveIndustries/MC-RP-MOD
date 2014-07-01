package com.eloraam.redpower.core;

public abstract interface ITubeConnectable
{
  public abstract int getTubeConnectableSides();
  
  public abstract int getTubeConClass();
  
  public abstract boolean canRouteItems();
  
  public abstract boolean tubeItemEnter(int paramInt1, int paramInt2, TubeItem paramTubeItem);
  
  public abstract boolean tubeItemCanEnter(int paramInt1, int paramInt2, TubeItem paramTubeItem);
  
  public abstract int tubeWeight(int paramInt1, int paramInt2);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ITubeConnectable
 * JD-Core Version:    0.7.0.1
 */