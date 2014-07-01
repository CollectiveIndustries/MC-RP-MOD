package buildcraft.api.transport;

import bq;

public abstract interface IPassiveItemContribution
{
  public abstract void readFromNBT(bq parambq);
  
  public abstract void writeToNBT(bq parambq);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.IPassiveItemContribution
 * JD-Core Version:    0.7.0.1
 */