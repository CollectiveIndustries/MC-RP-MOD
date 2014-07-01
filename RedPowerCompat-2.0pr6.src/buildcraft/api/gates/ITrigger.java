package buildcraft.api.gates;

import any;

public abstract interface ITrigger
{
  public abstract int getId();
  
  public abstract String getTextureFile();
  
  public abstract int getIndexInTexture();
  
  public abstract boolean hasParameter();
  
  public abstract String getDescription();
  
  public abstract boolean isTriggerActive(any paramany, ITriggerParameter paramITriggerParameter);
  
  public abstract ITriggerParameter createParameter();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.ITrigger
 * JD-Core Version:    0.7.0.1
 */