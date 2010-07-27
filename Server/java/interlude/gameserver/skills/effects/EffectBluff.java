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
package interlude.gameserver.skills.effects;

import interlude.gameserver.model.L2Effect;
import interlude.gameserver.model.actor.instance.L2FolkInstance;
import interlude.gameserver.model.actor.instance.L2NpcInstance;
import interlude.gameserver.model.actor.instance.L2SiegeSummonInstance;
import interlude.gameserver.network.serverpackets.BeginRotation;
import interlude.gameserver.network.serverpackets.StopRotation;
import interlude.gameserver.skills.Env;

/**
 * @author L2J Server Implementation of the Bluff Effect
 */
final class EffectBluff extends L2Effect
{
	public EffectBluff(Env env, EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public EffectType getEffectType()
	{
		return EffectType.BLUFF; // test for bluff effect
	}

	/** Notify started */
	@Override
	public void onStart()
	{
		if (getEffected() instanceof L2FolkInstance)
		{
			return;
		}
		// if(getEffected() instanceof L2SiegeGuardInstance) return;
		// Cannot be used on Headquarters Flag.
		// bluff now is a PVE PVP skill
		if (getEffected() instanceof L2NpcInstance && ((L2NpcInstance) getEffected()).getNpcId() == 35062 || getSkill().getId() != 358)
		{
			return;
		}
		if (getEffected() instanceof L2SiegeSummonInstance) {
			return;
		}
		getEffected().broadcastPacket(new BeginRotation(getEffected().getObjectId(), getEffected().getHeading(), 1, 65535));
		getEffected().broadcastPacket(new StopRotation(getEffected().getObjectId(), getEffector().getHeading(), 65535));
		getEffected().setHeading(getEffector().getHeading());
		onActionTime();
	}

	@Override
	public boolean onActionTime()
	{
		return false;
	}
}
