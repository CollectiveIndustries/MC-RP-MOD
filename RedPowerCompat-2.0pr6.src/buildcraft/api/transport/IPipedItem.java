package buildcraft.api.transport;

import any;
import bq;
import buildcraft.api.core.Position;
import buildcraft.api.core.SafeTimeTracker;
import net.minecraftforge.common.ForgeDirection;
import px;
import ur;
import yc;

public abstract interface IPipedItem
{
  public abstract void remove();
  
  public abstract void setWorld(yc paramyc);
  
  public abstract Position getPosition();
  
  public abstract void setPosition(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract float getSpeed();
  
  public abstract void setSpeed(float paramFloat);
  
  public abstract ur getItemStack();
  
  public abstract void setItemStack(ur paramur);
  
  public abstract any getContainer();
  
  public abstract void setContainer(any paramany);
  
  @Deprecated
  public abstract SafeTimeTracker getSynchroTracker();
  
  @Deprecated
  public abstract void setSynchroTracker(SafeTimeTracker paramSafeTimeTracker);
  
  @Deprecated
  public abstract int getDeterministicRandomization();
  
  @Deprecated
  public abstract void setDeterministicRandomization(int paramInt);
  
  public abstract int getEntityId();
  
  public abstract void setEntityId(int paramInt);
  
  public abstract void readFromNBT(bq parambq);
  
  public abstract void writeToNBT(bq parambq);
  
  public abstract px toEntityItem(ForgeDirection paramForgeDirection);
  
  public abstract float getEntityBrightness(float paramFloat);
  
  public abstract boolean isCorrupted();
  
  public abstract void addContribution(String paramString, IPassiveItemContribution paramIPassiveItemContribution);
  
  public abstract IPassiveItemContribution getContribution(String paramString);
  
  public abstract boolean hasContributions();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.IPipedItem
 * JD-Core Version:    0.7.0.1
 */