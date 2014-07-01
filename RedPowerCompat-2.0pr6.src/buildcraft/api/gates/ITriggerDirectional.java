package buildcraft.api.gates;

import any;
import net.minecraftforge.common.ForgeDirection;

public abstract interface ITriggerDirectional
  extends ITrigger
{
  public abstract boolean isTriggerActive(ForgeDirection paramForgeDirection, any paramany, ITriggerParameter paramITriggerParameter);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.ITriggerDirectional
 * JD-Core Version:    0.7.0.1
 */