package buildcraft.api.inventory;

import net.minecraftforge.common.ForgeDirection;
import ur;

public abstract interface ISelectiveInventory
  extends ISpecialInventory
{
  public abstract ur[] extractItem(Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, ForgeDirection paramForgeDirection, int paramInt);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.inventory.ISelectiveInventory
 * JD-Core Version:    0.7.0.1
 */