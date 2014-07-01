package buildcraft.api.gates;

import amq;
import any;
import java.util.LinkedList;

public abstract interface IActionProvider
{
  public abstract LinkedList getNeighborActions(amq paramamq, any paramany);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.IActionProvider
 * JD-Core Version:    0.7.0.1
 */