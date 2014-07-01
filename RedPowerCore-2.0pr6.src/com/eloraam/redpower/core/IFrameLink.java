package com.eloraam.redpower.core;

public abstract interface IFrameLink
{
  public abstract boolean isFrameMoving();
  
  public abstract boolean canFrameConnectIn(int paramInt);
  
  public abstract boolean canFrameConnectOut(int paramInt);
  
  public abstract WorldCoord getFrameLinkset();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IFrameLink
 * JD-Core Version:    0.7.0.1
 */