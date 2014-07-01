package buildcraft.api.inventory;

import net.minecraftforge.common.ForgeDirection;

public abstract interface ISecuredInventory
{
  public abstract boolean canAccess(String paramString);
  
  public abstract void prepareTransaction(ForgeDirection paramForgeDirection, String paramString);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.inventory.ISecuredInventory
 * JD-Core Version:    0.7.0.1
 */