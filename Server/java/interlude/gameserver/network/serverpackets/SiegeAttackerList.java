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

import java.util.List;

import javolution.util.FastList;
import interlude.gameserver.datatables.ClanTable;
import interlude.gameserver.instancemanager.clanhallsiege.DevastatedCastleManager;
import interlude.gameserver.instancemanager.clanhallsiege.FortressofTheDeadManager;
import interlude.gameserver.model.L2Clan;
import interlude.gameserver.model.L2SiegeClan;
import interlude.gameserver.model.entity.Castle;
import interlude.gameserver.model.entity.ClanHall;

/**
 * Populates the Siege Attacker List in the SiegeInfo Window<BR>
 * <BR>
 * packet type id 0xca<BR>
 * format: cddddddd + dSSdddSSd<BR>
 * <BR>
 * c = ca<BR>
 * d = CastleID<BR>
 * d = unknow (0x00)<BR>
 * d = unknow (0x01)<BR>
 * d = unknow (0x00)<BR>
 * d = Number of Attackers Clans?<BR>
 * d = Number of Attackers Clans<BR>
 * { //repeats<BR>
 * d = ClanID<BR>
 * S = ClanName<BR>
 * S = ClanLeaderName<BR>
 * d = ClanCrestID<BR>
 * d = signed time (seconds)<BR>
 * d = AllyID<BR>
 * S = AllyName<BR>
 * S = AllyLeaderName<BR>
 * d = AllyCrestID<BR>
 *
 * @author KenM
 */
public class SiegeAttackerList extends L2GameServerPacket
{
	private static final String _S__CA_SiegeAttackerList = "[S] ca SiegeAttackerList";
	private Castle _castle;
	private ClanHall _clanHall;

	public SiegeAttackerList(Castle castle,ClanHall clanHall)
	{
		_castle = castle;
		_clanHall = clanHall;
	}

	@Override
	protected final void writeImpl()
	{
			List<L2SiegeClan> clans = new FastList<L2SiegeClan>();
			if (_castle==null)
			{
				if (_clanHall.getId() == 34)
					clans = DevastatedCastleManager.getInstance().getRegisteredClans();
				if (_clanHall.getId() == 64)
					clans = FortressofTheDeadManager.getInstance().getRegisteredClans();
			}
			else
				clans = _castle.getSiege().getAttackerClans();
			writeC(0xca);
			if (_castle==null)
				writeD(_clanHall.getId());
			else
				writeD(_castle.getCastleId());
			writeD(0x00); //0
			writeD(0x01); //1
			writeD(0x00); //0
			int size = clans.size();
			if (size > 0)
			{
				L2Clan clan;
				writeD(size);
				writeD(size);
				for (L2SiegeClan siegeclan : clans)
				{
					clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
					if (clan == null) continue;

					writeD(clan.getClanId());
					writeS(clan.getName());
					writeS(clan.getLeaderName());
					writeD(clan.getCrestId());
					writeD(0x00); //signed time (seconds) (not storated by L2J)
					writeD(clan.getAllyId());
					writeS(clan.getAllyName());
					writeS(""); //AllyLeaderName
					writeD(clan.getAllyCrestId());
				}
			}
			else
			{
				writeD(0x00);
				writeD(0x00);
			}
		}

	/*
	 * (non-Javadoc)
	 * @see interlude.gameserver.network.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__CA_SiegeAttackerList;
	}
}
