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

import interlude.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author Maktakien
 */
public class StopMoveInVehicle extends L2GameServerPacket
{
	private L2PcInstance _activeChar;
	private int _boatId;

	/**
	 * @param player
	 * @param boatid
	 */
	public StopMoveInVehicle(L2PcInstance player, int boatid)
	{
		_activeChar = player;
		_boatId = boatid;
	}

	/*
	 * (non-Javadoc)
	 * @see interlude.gameserver.network.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0x72);
		writeD(_activeChar.getObjectId());
		writeD(_boatId);
		writeD(_activeChar.getInBoatPosition().getX());
		writeD(_activeChar.getInBoatPosition().getY());
		writeD(_activeChar.getInBoatPosition().getZ());
		writeD(_activeChar.getPosition().getHeading());
	}

	/*
	 * (non-Javadoc)
	 * @see interlude.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return "[S] 72 StopMoveInVehicle";
	}
}
