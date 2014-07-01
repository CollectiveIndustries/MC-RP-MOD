package buildcraft.api.gates;

import bq;
import ur;

public abstract interface ITriggerParameter
{
  public abstract ur getItemStack();
  
  public abstract void set(ur paramur);
  
  public abstract void writeToNBT(bq parambq);
  
  public abstract void readFromNBT(bq parambq);
  
  public abstract ur getItem();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.ITriggerParameter
 * JD-Core Version:    0.7.0.1
 */