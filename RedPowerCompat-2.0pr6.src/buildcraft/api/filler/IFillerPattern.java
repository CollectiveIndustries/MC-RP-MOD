package buildcraft.api.filler;

import any;
import buildcraft.api.core.IBox;
import ur;

public abstract interface IFillerPattern
{
  public abstract int getId();
  
  public abstract void setId(int paramInt);
  
  public abstract boolean iteratePattern(any paramany, IBox paramIBox, ur paramur);
  
  public abstract String getTextureFile();
  
  public abstract int getTextureIndex();
  
  public abstract String getName();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.filler.IFillerPattern
 * JD-Core Version:    0.7.0.1
 */