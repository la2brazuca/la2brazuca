/* This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jfrozen.gameserver.network.serverpackets;

/**
 * Format: (ch)ddd
 */
public class ExVariationCancelResult extends L2GameServerPacket
{
	private static final String _S__FE_57_EXVARIATIONCANCELRESULT = "[S] FE:57 ExVariationCancelResult";

	private int _closeWindow;
	private int _unk1;

	public ExVariationCancelResult(int result)
	{
		_closeWindow = 1;
		_unk1 = result;
	}

	/**
	 * @see com.l2jfrozen.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x57);
		writeD(_closeWindow);
		writeD(_unk1);
	}

	/**
	 * @see com.l2jfrozen.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_57_EXVARIATIONCANCELRESULT;
	}

}