package com.eloraam.redpower.core;

import java.util.List;
import qx;
import tj;
import ur;
import yc;

public abstract interface IMicroPlacement
{
  public abstract boolean onPlaceMicro(ur paramur, qx paramqx, yc paramyc, WorldCoord paramWorldCoord, int paramInt);
  
  public abstract String getMicroName(int paramInt1, int paramInt2);
  
  public abstract void addCreativeItems(int paramInt, tj paramtj, List paramList);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IMicroPlacement
 * JD-Core Version:    0.7.0.1
 */