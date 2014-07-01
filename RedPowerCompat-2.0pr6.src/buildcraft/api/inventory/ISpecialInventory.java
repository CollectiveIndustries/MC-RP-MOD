package buildcraft.api.inventory;

import la;
import net.minecraftforge.common.ForgeDirection;
import ur;

public abstract interface ISpecialInventory
  extends la
{
  public abstract int addItem(ur paramur, boolean paramBoolean, ForgeDirection paramForgeDirection);
  
  public abstract ur[] extractItem(boolean paramBoolean, ForgeDirection paramForgeDirection, int paramInt);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.inventory.ISpecialInventory
 * JD-Core Version:    0.7.0.1
 */