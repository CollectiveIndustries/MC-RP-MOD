package buildcraft.api.filler;

import la;

public abstract interface IFillerRegistry
{
  public abstract void addRecipe(IFillerPattern paramIFillerPattern, Object[] paramArrayOfObject);
  
  public abstract IFillerPattern findMatchingRecipe(la paramla);
  
  public abstract int getPatternNumber(IFillerPattern paramIFillerPattern);
  
  public abstract IFillerPattern getPattern(int paramInt);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.filler.IFillerRegistry
 * JD-Core Version:    0.7.0.1
 */