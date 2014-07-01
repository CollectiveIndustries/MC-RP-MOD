package buildcraft.api.power;

import bq;
import buildcraft.api.core.SafeTimeTracker;
import net.minecraftforge.common.ForgeDirection;

public abstract interface IPowerProvider
{
  public abstract int getLatency();
  
  public abstract int getMinEnergyReceived();
  
  public abstract int getMaxEnergyReceived();
  
  public abstract int getMaxEnergyStored();
  
  public abstract int getActivationEnergy();
  
  public abstract float getEnergyStored();
  
  public abstract void configure(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  public abstract void configurePowerPerdition(int paramInt1, int paramInt2);
  
  public abstract boolean update(IPowerReceptor paramIPowerReceptor);
  
  public abstract boolean preConditions(IPowerReceptor paramIPowerReceptor);
  
  public abstract float useEnergy(float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public abstract void readFromNBT(bq parambq);
  
  public abstract void writeToNBT(bq parambq);
  
  public abstract void receiveEnergy(float paramFloat, ForgeDirection paramForgeDirection);
  
  public abstract boolean isPowerSource(ForgeDirection paramForgeDirection);
  
  public abstract SafeTimeTracker getTimeTracker();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.power.IPowerProvider
 * JD-Core Version:    0.7.0.1
 */