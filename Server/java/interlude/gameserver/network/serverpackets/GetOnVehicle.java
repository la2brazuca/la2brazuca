/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package interlude.gameserver.network.serverpackets;

import interlude.gameserver.model.actor.instance.L2BoatInstance;
import interlude.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author Maktakien
 */
public class GetOnVehicle extends L2GameServerPacket
{
	private int _x;
	private int _y;
	private int _z;
	private L2PcInstance _activeChar;
	private L2BoatInstance _boat;

	/**
	 * @param activeChar
	 * @param boat
	 * @param x
	 * @param y
	 * @param z
	 */
	public GetOnVehicle(L2PcInstance activeChar, L2BoatInstance boat, int x, int y, int z)
	{
		_activeChar = activeChar;
		_boat = boat;
		_x = x;
		_y = y;
		_z = z;
		_activeChar.setInBoat(true);
		_activeChar.setBoat(_boat);
	}

	/*
	 * (non-Javadoc)
	 * @see interlude.gameserver.network.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0x5c);
		writeD(_activeChar.getObjectId());
		writeD(_boat.getObjectId());
		writeD(_x);
		writeD(_y);
		writeD(_z);
	}

	/*
	 * (non-Javadoc)
	 * @see interlude.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return "[S] 5C GetOnVehicle";
	}
}
