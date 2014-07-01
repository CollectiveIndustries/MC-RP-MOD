package buildcraft.api.core;

public abstract interface IAreaProvider
{
  public abstract int xMin();
  
  public abstract int yMin();
  
  public abstract int zMin();
  
  public abstract int xMax();
  
  public abstract int yMax();
  
  public abstract int zMax();
  
  public abstract void removeFromWorld();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.core.IAreaProvider
 * JD-Core Version:    0.7.0.1
 */