/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2jmobius.gameserver.geoengine.geodata;

import java.io.BufferedOutputStream;

/**
 * @author Hasha
 */
public class BlockNull extends ABlock
{
	private final byte _nswe;
	
	public BlockNull()
	{
		_nswe = (byte) 0xFF;
	}
	
	@Override
	public boolean hasGeoPos()
	{
		return false;
	}
	
	@Override
	public short getHeightNearest(int geoX, int geoY, int worldZ)
	{
		return (short) worldZ;
	}
	
	@Override
	public short getHeightNearestOriginal(int geoX, int geoY, int worldZ)
	{
		return (short) worldZ;
	}
	
	@Override
	public short getHeightAbove(int geoX, int geoY, int worldZ)
	{
		return (short) worldZ;
	}
	
	@Override
	public short getHeightBelow(int geoX, int geoY, int worldZ)
	{
		return (short) worldZ;
	}
	
	@Override
	public byte getNsweNearest(int geoX, int geoY, int worldZ)
	{
		return _nswe;
	}
	
	@Override
	public byte getNsweNearestOriginal(int geoX, int geoY, int worldZ)
	{
		return _nswe;
	}
	
	@Override
	public byte getNsweAbove(int geoX, int geoY, int worldZ)
	{
		return _nswe;
	}
	
	@Override
	public byte getNsweBelow(int geoX, int geoY, int worldZ)
	{
		return _nswe;
	}
	
	@Override
	public int getIndexNearest(int geoX, int geoY, int worldZ)
	{
		return 0;
	}
	
	@Override
	public int getIndexAbove(int geoX, int geoY, int worldZ)
	{
		return 0;
	}
	
	@Override
	public int getIndexAboveOriginal(int geoX, int geoY, int worldZ)
	{
		return 0;
	}
	
	@Override
	public int getIndexBelow(int geoX, int geoY, int worldZ)
	{
		return 0;
	}
	
	@Override
	public int getIndexBelowOriginal(int geoX, int geoY, int worldZ)
	{
		return 0;
	}
	
	@Override
	public short getHeight(int index)
	{
		return 0;
	}
	
	@Override
	public short getHeightOriginal(int index)
	{
		return 0;
	}
	
	@Override
	public byte getNswe(int index)
	{
		return _nswe;
	}
	
	@Override
	public byte getNsweOriginal(int index)
	{
		return _nswe;
	}
	
	@Override
	public void setNswe(int index, byte nswe)
	{
	}
	
	@Override
	public void saveBlock(BufferedOutputStream stream)
	{
	}
}