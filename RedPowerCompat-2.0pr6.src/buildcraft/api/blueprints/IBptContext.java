package buildcraft.api.blueprints;

import buildcraft.api.core.IBox;
import buildcraft.api.core.Position;
import ur;
import yc;

public abstract interface IBptContext
{
  public abstract ur mapItemStack(ur paramur);
  
  public abstract int mapWorldId(int paramInt);
  
  public abstract void storeId(int paramInt);
  
  public abstract Position rotatePositionLeft(Position paramPosition);
  
  public abstract IBox surroundingBox();
  
  public abstract yc world();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.IBptContext
 * JD-Core Version:    0.7.0.1
 */