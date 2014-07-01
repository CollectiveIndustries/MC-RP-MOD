package buildcraft.api.transport;

import net.minecraftforge.common.ForgeDirection;
import ur;

public abstract interface IPipeEntry
{
  public abstract void entityEntering(ur paramur, ForgeDirection paramForgeDirection);
  
  public abstract void entityEntering(IPipedItem paramIPipedItem, ForgeDirection paramForgeDirection);
  
  public abstract boolean acceptItems();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.IPipeEntry
 * JD-Core Version:    0.7.0.1
 */