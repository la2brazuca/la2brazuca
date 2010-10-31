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
package com.l2jfrozen.gameserver.handler.itemhandlers;

import javolution.text.TextBuilder;

import com.l2jfrozen.gameserver.handler.IItemHandler;
import com.l2jfrozen.gameserver.model.actor.instance.L2ItemInstance;
import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfrozen.gameserver.model.actor.instance.L2PlayableInstance;
import com.l2jfrozen.gameserver.network.serverpackets.NpcHtmlMessage;

public class MapForestOfTheDead implements IItemHandler
{
	public MapForestOfTheDead()
	{}

	private static int _itemIds[] =
	{
		7063
	};

	public void useItem(L2PlayableInstance playable, L2ItemInstance item)
	{
		if(!(playable instanceof L2PcInstance))
			return;
		int itemId = item.getItemId();
		if(itemId == 7063)
		{
			NpcHtmlMessage html = new NpcHtmlMessage(5);
			TextBuilder map = new TextBuilder("<html><title>Map - Forest of the Dead</title>");
			map.append("<body>");
			map.append("<br>");
			map.append("Map :");
			map.append("<br>");
			map.append("<table>");
			map.append("<tr><td>");
			map.append("<img src=\"icon.Quest_deadperson_forest_t00\" width=255 height=255>");
			map.append("</td></tr>");
			map.append("</table>");
			map.append("</body></html>");
			html.setHtml(map.toString());
			playable.sendPacket(html);
		}
	}

	public int[] getItemIds()
	{
		return _itemIds;
	}
}