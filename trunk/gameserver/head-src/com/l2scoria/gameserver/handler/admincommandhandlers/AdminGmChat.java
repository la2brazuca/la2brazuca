/*
 * This program is free software; you can redistribute it and/or modify
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
package com.l2scoria.gameserver.handler.admincommandhandlers;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.l2scoria.Config;
import com.l2scoria.gameserver.datatables.GmListTable;
import com.l2scoria.gameserver.datatables.sql.AdminCommandAccessRights;
import com.l2scoria.gameserver.handler.IAdminCommandHandler;
import com.l2scoria.gameserver.model.L2Object;
import com.l2scoria.gameserver.model.actor.instance.L2PcInstance;
import com.l2scoria.gameserver.network.SystemMessageId;
import com.l2scoria.gameserver.network.serverpackets.CreatureSay;
import com.l2scoria.gameserver.network.serverpackets.SystemMessage;

/**
 * This class handles following admin commands: - gmchat text = sends text to all online GM's - gmchat_menu text = same
 * as gmchat, displays the admin panel after chat
 * 
 * @version $Revision: 1.2.4.3 $ $Date: 2005/04/11 10:06:06 $
 */
public class AdminGmChat implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
			"admin_gmchat", "admin_snoop", "admin_gmchat_menu"
	};

	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		AdminCommandAccessRights.getInstance().hasAccess(command, activeChar.getAccessLevel());

		if(Config.GMAUDIT)
		{
			Logger _logAudit = Logger.getLogger("gmaudit");
			LogRecord record = new LogRecord(Level.INFO, command);
			record.setParameters(new Object[]
			{
					"GM: " + activeChar.getName(), " to target [" + activeChar.getTarget() + "] "
			});
			_logAudit.log(record);
		}

		if(command.startsWith("admin_gmchat"))
		{
			handleGmChat(command, activeChar);
		}
		else if(command.startsWith("admin_snoop"))
		{
			snoop(command, activeChar);
		}

		if(command.startsWith("admin_gmchat_menu"))
		{
			AdminHelpPage.showHelpPage(activeChar, "main_menu.htm");
		}

		return true;
	}

	/**
	 * @param command
	 * @param activeChar
	 */
	private void snoop(String command, L2PcInstance activeChar)
	{
		L2Object target = activeChar.getTarget();

		if(target == null)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_MUST_SELECT_A_TARGET));
			return;
		}

		if(!(target instanceof L2PcInstance))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.INCORRECT_TARGET));
			return;
		}

		L2PcInstance player = (L2PcInstance) target;
		player.addSnooper(activeChar);
		activeChar.addSnooped(player);

		target = null;
		player = null;
	}

	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}

	/**
	 * @param command
	 * @param activeChar
	 */
	private void handleGmChat(String command, L2PcInstance activeChar)
	{
		try
		{
			int offset = 0;

			String text;

			if(command.contains("menu"))
			{
				offset = 17;
			}
			else
			{
				offset = 13;
			}

			text = command.substring(offset);
			CreatureSay cs = new CreatureSay(0, 9, activeChar.getName(), text);
			GmListTable.broadcastToGMs(cs);

			text = null;
			cs = null;
		}
		catch(StringIndexOutOfBoundsException e)
		{
			// empty message.. ignore
		}
	}
}
