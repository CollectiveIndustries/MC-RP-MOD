package buildcraft.api.gates;

public abstract interface IAction
{
  public abstract int getId();
  
  public abstract String getTexture();
  
  public abstract int getIndexInTexture();
  
  public abstract boolean hasParameter();
  
  public abstract String getDescription();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.IAction
 * JD-Core Version:    0.7.0.1
 */