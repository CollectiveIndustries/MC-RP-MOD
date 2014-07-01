package buildcraft.api.power;

public abstract interface IPowerReceptor
{
  public abstract void setPowerProvider(IPowerProvider paramIPowerProvider);
  
  public abstract IPowerProvider getPowerProvider();
  
  public abstract void doWork();
  
  public abstract int powerRequest();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.power.IPowerReceptor
 * JD-Core Version:    0.7.0.1
 */