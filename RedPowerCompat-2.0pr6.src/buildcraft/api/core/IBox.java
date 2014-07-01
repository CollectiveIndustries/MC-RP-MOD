package buildcraft.api.core;

import yc;

public abstract interface IBox
{
  public abstract void expand(int paramInt);
  
  public abstract void contract(int paramInt);
  
  public abstract boolean contains(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract Position pMin();
  
  public abstract Position pMax();
  
  public abstract void createLasers(yc paramyc, LaserKind paramLaserKind);
  
  public abstract void deleteLasers();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.core.IBox
 * JD-Core Version:    0.7.0.1
 */