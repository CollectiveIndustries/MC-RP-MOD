package buildcraft.api.transport;

import yc;

public abstract interface IExtractionHandler
{
  public abstract boolean canExtractItems(IPipe paramIPipe, yc paramyc, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean canExtractLiquids(IPipe paramIPipe, yc paramyc, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.IExtractionHandler
 * JD-Core Version:    0.7.0.1
 */