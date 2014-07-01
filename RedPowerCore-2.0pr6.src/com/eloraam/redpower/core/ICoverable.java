package com.eloraam.redpower.core;

public abstract interface ICoverable
{
  public abstract boolean canAddCover(int paramInt1, int paramInt2);
  
  public abstract boolean tryAddCover(int paramInt1, int paramInt2);
  
  public abstract int tryRemoveCover(int paramInt);
  
  public abstract int getCover(int paramInt);
  
  public abstract int getCoverMask();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ICoverable
 * JD-Core Version:    0.7.0.1
 */