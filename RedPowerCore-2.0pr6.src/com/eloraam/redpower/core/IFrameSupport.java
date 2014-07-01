package com.eloraam.redpower.core;

import java.io.IOException;
import ym;

public abstract interface IFrameSupport
{
  public abstract byte[] getFramePacket();
  
  public abstract void handleFramePacket(byte[] paramArrayOfByte)
    throws IOException;
  
  public abstract void onFrameRefresh(ym paramym);
  
  public abstract void onFramePickup(ym paramym);
  
  public abstract void onFrameDrop();
}


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IFrameSupport
 * JD-Core Version:    0.7.0.1
 */