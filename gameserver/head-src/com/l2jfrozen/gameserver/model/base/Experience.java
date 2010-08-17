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
package com.l2jfrozen.gameserver.model.base;

import javolution.util.FastMap;

/**
 *
 *
 */
public class Experience
{
	private static FastMap<Integer, Long> experience = new FastMap<Integer, Long>();

	public static void setExp(int level, long expr)
	{
		experience.put(level, expr);
		MAX_LEVEL = experience.size() - 1;
	}

	public static long getExp(int level)
	{
		return experience.get(level);
	}

	/**
	 * This is the first UNREACHABLE level.<BR>
	 * ex: If you want a max at 80 & 99.99%, you have to put 81.<BR>
	 * <BR>
	 */
	public static int MAX_LEVEL;
	public final static byte MAX_SUBCLASS_LEVEL = 81;
	//////////////////////////////////////////////////////
	public final static byte MIN_NEWBIE_LEVEL = 6;
	public final static byte MAX_NEWBIE_LEVEL = 39;
}
