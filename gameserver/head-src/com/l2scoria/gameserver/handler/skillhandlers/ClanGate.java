package com.l2scoria.gameserver.handler.skillhandlers;

import com.l2scoria.gameserver.handler.ISkillHandler;
import com.l2scoria.gameserver.managers.CastleManager;
import com.l2scoria.gameserver.managers.GrandBossManager;
import com.l2scoria.gameserver.model.L2Character;
import com.l2scoria.gameserver.model.L2Clan;
import com.l2scoria.gameserver.model.L2Effect;
import com.l2scoria.gameserver.model.L2Object;
import com.l2scoria.gameserver.model.L2Skill;
import com.l2scoria.gameserver.model.L2Skill.SkillType;
import com.l2scoria.gameserver.model.actor.instance.L2PcInstance;
import com.l2scoria.gameserver.model.entity.siege.Castle;
import com.l2scoria.gameserver.network.SystemMessageId;
import com.l2scoria.gameserver.network.serverpackets.SystemMessage;
import com.l2scoria.gameserver.thread.ThreadPoolManager;

public class ClanGate implements ISkillHandler
{
	private static final SkillType[] SKILL_IDS = { SkillType.CLAN_GATE };

	/* (non-Javadoc)
	 * @see com.l2jfree.gameserver.handler.ISkillHandler#getSkillIds()
	 */

	@Override
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{
		L2PcInstance player = null;
		if(activeChar instanceof L2PcInstance)
			player = (L2PcInstance) activeChar;
		else
			return;
		//need more checking...
		if(player.isInFunEvent() || player.isInsideZone(L2Character.ZONE_NOLANDING)
			|| player.isInOlympiadMode() || player.isInsideZone(L2Character.ZONE_PVP)
			|| GrandBossManager.getInstance().getZone(player) != null)
		{
			player.sendMessage("Cannot open the portal here.");
			return;
		}

		L2Clan clan = player.getClan();
		if(clan != null)
		{
			if(CastleManager.getInstance().getCastleByOwner(clan) != null)
			{
				Castle castle = CastleManager.getInstance().getCastleByOwner(clan);
				if(player.isCastleLord(castle.getCastleId()))
				{
					//please note clan gate expires in two minutes WHATEVER happens to the clan leader.
					ThreadPoolManager.getInstance().scheduleGeneral(new RemoveClanGate(castle.getCastleId(), player), skill.getTotalLifeTime());
					castle.createClanGate(player.getX(), player.getY(), player.getZ() + 20);
					player.getClan().broadcastToOnlineMembers(new SystemMessage(SystemMessageId.THE_PORTAL_HAS_BEEN_CREATED));
					player.setIsParalyzed(true);
				}
				castle = null;
			}
		}

		L2Effect effect = player.getFirstEffect(skill.getId());
		if(effect != null && effect.isSelfEffect())
			effect.exit();
		skill.getEffectsSelf(player);

		player = null;
		clan = null;
	}

	private class RemoveClanGate implements Runnable
	{
		private final int castle;
		private final L2PcInstance player;

		private RemoveClanGate(int castle, L2PcInstance player)
		{
			this.castle = castle;
			this.player = player;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			if(player != null)
			{
				player.setIsParalyzed(false);
			}
			CastleManager.getInstance().getCastleById(castle).destroyClanGate();
		}
	}

	@Override
	public SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}
