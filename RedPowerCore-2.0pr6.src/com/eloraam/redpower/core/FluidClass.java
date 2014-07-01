package com.eloraam.redpower.core;

import net.minecraftforge.liquids.LiquidStack;
import yc;

public abstract class FluidClass
{
  public abstract int getFluidId();
  
  public abstract int getFluidId(yc paramyc, WorldCoord paramWorldCoord);
  
  public abstract int getFluidLevel(yc paramyc, WorldCoord paramWorldCoord);
  
  public abstract boolean setFluidLevel(yc paramyc, WorldCoord paramWorldCoord, int paramInt);
  
  public abstract int getFluidQuanta();
  
  public abstract String getTextureFile();
  
  public abstract int getTexture();
  
  public abstract LiquidStack getLiquidStack(int paramInt);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FluidClass
 * JD-Core Version:    0.7.0.1
 */