package buildcraft.api.gates;

import amq;
import any;
import buildcraft.api.transport.IPipe;
import java.util.LinkedList;

public abstract interface ITriggerProvider
{
  public abstract LinkedList getPipeTriggers(IPipe paramIPipe);
  
  public abstract LinkedList getNeighborTriggers(amq paramamq, any paramany);
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.ITriggerProvider
 * JD-Core Version:    0.7.0.1
 */