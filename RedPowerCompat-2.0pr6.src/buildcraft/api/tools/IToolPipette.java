package buildcraft.api.tools;

import net.minecraftforge.liquids.LiquidStack;
import ur;

public abstract interface IToolPipette
{
  public abstract int getCapacity(ur paramur);
  
  public abstract boolean canPipette(ur paramur);
  
  public abstract int fill(ur paramur, LiquidStack paramLiquidStack, boolean paramBoolean);
  
  public abstract LiquidStack drain(ur paramur, int paramInt, boolean paramBoolean);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.tools.IToolPipette
 * JD-Core Version:    0.7.0.1
 */