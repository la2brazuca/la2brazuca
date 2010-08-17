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

package com.l2jfrozen.gameserver.network.clientpackets;

import com.l2jfrozen.gameserver.managers.CastleManager;
import com.l2jfrozen.gameserver.managers.FortManager;
import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfrozen.gameserver.model.entity.siege.Castle;
import com.l2jfrozen.gameserver.model.entity.siege.Fort;
import com.l2jfrozen.gameserver.network.SystemMessageId;
import com.l2jfrozen.gameserver.network.serverpackets.SystemMessage;

/**
 * @author ProGramMoS, scoria dev
 */
public final class RequestJoinSiege extends L2GameClientPacket
{
	private static final String _C__A4_RequestJoinSiege = "[C] a4 RequestJoinSiege";
	//private static Logger _log = Logger.getLogger(RequestJoinSiege.class.getName());

	private int _castleId;
	private int _isAttacker;
	private int _isJoining;

	@Override
	public String getType()
	{
		return _C__A4_RequestJoinSiege;
	}

	@Override
	protected void readImpl()
	{
		_castleId = readD();
		_isAttacker = readD();
		_isJoining = readD();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance player = getClient().getActiveChar();

		if(player == null)
			return;

		if(!player.isClanLeader())
			return;

		if(_castleId < 100)
		{
			final Castle castle = CastleManager.getInstance().getCastleById(_castleId);

			if(castle == null)
				return;

			if(_isJoining == 1)
			{
				if(System.currentTimeMillis() < player.getClan().getDissolvingExpiryTime())
				{
					player.sendPacket(new SystemMessage(SystemMessageId.CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS));
					return;
				}

				if(_isAttacker == 1)
				{
					castle.getSiege().registerAttacker(player);
				}
				else
				{
					castle.getSiege().registerDefender(player);
				}
			}
			else
			{
				castle.getSiege().removeSiegeClan(player);
			}

			castle.getSiege().listRegisterClan(player);
		}
		else
		{
			final Fort fort = FortManager.getInstance().getFortById(_castleId);

			if(fort == null)
				return;

			if(_isJoining == 1)
			{
				if(System.currentTimeMillis() < player.getClan().getDissolvingExpiryTime())
				{
					player.sendPacket(new SystemMessage(SystemMessageId.CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS));
					return;
				}

				if(_isAttacker == 1)
				{
					fort.getSiege().registerAttacker(player);
				}
				else
				{
					fort.getSiege().registerDefender(player);
				}
			}
			else
			{
				fort.getSiege().removeSiegeClan(player);
			}

			fort.getSiege().listRegisterClan(player);
		}

	}
}
