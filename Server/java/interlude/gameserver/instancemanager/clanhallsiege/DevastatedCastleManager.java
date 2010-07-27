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
package interlude.gameserver.instancemanager.clanhallsiege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javolution.util.FastList;
import interlude.L2DatabaseFactory;
import interlude.gameserver.Announcements;
import interlude.gameserver.datatables.ClanTable;
import interlude.gameserver.datatables.DoorTable;
import interlude.gameserver.datatables.NpcTable;
import interlude.gameserver.idfactory.IdFactory;
import interlude.gameserver.instancemanager.ClanHallManager;
import interlude.gameserver.model.L2Character;
import interlude.gameserver.model.L2Clan;
import interlude.gameserver.model.L2SiegeClan;
import interlude.gameserver.model.L2World;
import interlude.gameserver.model.L2SiegeClan.SiegeClanType;
import interlude.gameserver.model.actor.instance.L2DoorInstance;
import interlude.gameserver.model.actor.instance.L2MonsterInstance;
import interlude.gameserver.model.actor.instance.L2PcInstance;
import interlude.gameserver.model.actor.instance.L2SiegeBossInstance;
import interlude.gameserver.model.entity.ClanHall;
import interlude.gameserver.model.entity.ClanHallSiege;
import interlude.gameserver.model.zone.type.L2ClanHallSiegeZone;
import interlude.gameserver.network.SystemMessageId;
import interlude.gameserver.network.serverpackets.SiegeInfo;
import interlude.gameserver.network.serverpackets.SystemMessage;
import interlude.gameserver.network.serverpackets.UserInfo;
import interlude.gameserver.taskmanager.ExclusiveTask;
import interlude.gameserver.templates.L2NpcTemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DevastatedCastleManager extends ClanHallSiege
{
	protected static Log					_log				= LogFactory.getLog(DevastatedCastleManager.class.getName());
	public ClanHall 						_clanhall			= ClanHallManager.getInstance().getClanHallById(34);
	private List<L2SiegeClan>				_registeredClans	= new FastList<L2SiegeClan>();	// L2SiegeClan
	private List<L2DoorInstance>			_doors				= new FastList<L2DoorInstance>();
	private List<String>					_doorDefault		= new FastList<String>();
	private L2ClanHallSiegeZone				_zone				= null;
	private L2MonsterInstance				_questMob			= null;
	protected boolean						_isRegistrationOver	= false;
	private static DevastatedCastleManager	_instance;

	public static final DevastatedCastleManager getInstance()
	{
		if (_instance == null)
			_instance = new DevastatedCastleManager();
		return _instance;
	}

	private DevastatedCastleManager()
	{
		_log.info("ClanHallSiege: Devastated Castle");
		long siegeDate = restoreSiegeDate(34);
		Calendar tmpDate = Calendar.getInstance();
		tmpDate.setTimeInMillis(siegeDate);
		setSiegeDate(tmpDate);
		setNewSiegeDate(siegeDate,34,22);
		loadSiegeClan();
		loadDoor();
		// Schedule siege auto start
		_startSiegeTask.schedule(1000);
		_isRegistrationOver = false;
		loadDCSiegeGuards();
	}

	public void startSiege()
	{
		if (!getIsInProgress())
		{
			if (getRegisteredClans().size() <= 0)
			{
				SystemMessage sm;
				sm = new SystemMessage(SystemMessageId.S1_SIEGE_WAS_CANCELED_BECAUSE_NO_CLANS_PARTICIPATED);
				sm.addString(_clanhall.getName());
				Announcements.getInstance().announceToAll(sm);
				setNewSiegeDate(getSiegeDate().getTimeInMillis(),34,22);
				_startSiegeTask.schedule(1000);
				_isRegistrationOver = false;
				return;
			}
			setIsInProgress(true);
			_clanhall.setUnderSiege(true);
			_zone.updateSiegeStatus();
			announceToPlayer("The siege of the clan hall: " + _clanhall.getName() + " started.");
			_isRegistrationOver = true;
			updatePlayerSiegeStateFlags(false);
			spawnDoor();
			spawnDCSiegeGuards();
			L2NpcTemplate template = NpcTable.getInstance().getTemplate(35410);
			_questMob = new L2SiegeBossInstance(IdFactory.getInstance().getNextId(), template);
			_questMob.getStatus().setCurrentHpMp(_questMob.getMaxHp(), _questMob.getMaxMp());
			_questMob.spawnMe(178282,-17623,-2195);
			_siegeEndDate = Calendar.getInstance();
			_siegeEndDate.add(Calendar.MINUTE, 60);
			_endSiegeTask.schedule(1000);
		}
	}

	public void endSiege(L2Character par)
	{
		if (getIsInProgress())
		{
			setIsInProgress(false);
			if (par!=null)
			{
				if (par instanceof L2PcInstance)
				{
					L2PcInstance killer = ((L2PcInstance)par);
					if ((killer.getClan()!=null)&& (checkIsRegistered(killer.getClan())))
					{
						ClanHallManager.getInstance().setOwner(_clanhall.getId(), killer.getClan());
						announceToPlayer("The Siege Clan Hall: " + _clanhall.getName () + " finished.");
						announceToPlayer("The owner of the clan hall became " + killer.getClan().getName());
					}
					else
					{
						announceToPlayer("The siege of the clan hall: " + _clanhall.getName() + " finished.");
						announceToPlayer("The owner of the clan hall remains the same");
					}
				}
			}
			else
			{
				announceToPlayer("The siege of the clan hall: " + _clanhall.getName() + " finished.");
				announceToPlayer("The owner of the clan hall remains the same");
				_questMob.doDie(_questMob);
			}
			deleteDCSiegeGuards();
			_questMob.deleteMe();
			spawnDoor();
			_clanhall.setUnderSiege(false);
			_zone.updateSiegeStatus();
			updatePlayerSiegeStateFlags(true);
			clearSiegeClan(); // Clear siege clan from db
			if (_clanhall.getOwnerClan() != null)
				saveSiegeClan(_clanhall.getOwnerClan());
			setNewSiegeDate(getSiegeDate().getTimeInMillis(),34,22);
			_startSiegeTask.schedule(1000);
			_isRegistrationOver = false;
		}
	}

	public void updatePlayerSiegeStateFlags(boolean clear)
	{
		L2Clan clan;
		for (L2SiegeClan siegeClan : getRegisteredClans())
		{
			if (siegeClan == null)
				continue;

			clan = ClanTable.getInstance().getClan(siegeClan.getClanId());
			for (L2PcInstance member : clan.getOnlineMembers(""))
			{
				if (clear)
					member.setSiegeState((byte) 0);
				else
					member.setSiegeState((byte) 1);
				member.sendPacket(new UserInfo(member));
				member.revalidateZone(true);
			}
		}
	}

	public void spawnDoor()
	{
		spawnDoor(false);
	}

	public void spawnDoor(boolean isDoorWeak)
	{
		for (int i = 0; i < getDoors().size(); i++)
		{
			L2DoorInstance door = getDoors().get(i);
			if (door.getCurrentHp() <= 0)
			{
				door.decayMe(); // Kill current if not killed already
				door = DoorTable.parseList(_doorDefault.get(i));
				DoorTable.getInstance().putDoor(door); // Readd the new door to the DoorTable By Erb
				if (isDoorWeak)
					door.setCurrentHp(door.getMaxHp() / 2);
				door.spawnMe(door.getX(), door.getY(), door.getZ());
				getDoors().set(i, door);
			}
			else if (door.getOpen() == 0)
				door.closeMe();
		}
	}

	public final List<L2DoorInstance> getDoors()
	{
		return _doors;
	}

	public void registerSiegeZone(L2ClanHallSiegeZone zone)
	{
		_zone = zone;
	}

	private void loadDoor()
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("Select * from castle_door where castleId = ?");
			statement.setInt(1, 34);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				// Create list of the door default for use when respawning dead doors
				_doorDefault.add(rs.getString("name") 
						+ ";" + rs.getInt("id") 
						+ ";" + rs.getInt("x") 
						+ ";" + rs.getInt("y") 
						+ ";" + rs.getInt("z") 
						+ ";" + rs.getInt("range_xmin") 
						+ ";" + rs.getInt("range_ymin") 
						+ ";" + rs.getInt("range_zmin") 
						+ ";" + rs.getInt("range_xmax") 
						+ ";" + rs.getInt("range_ymax") 
						+ ";" + rs.getInt("range_zmax") 
						+ ";" + rs.getInt("hp") 
						+ ";" + rs.getInt("pDef") 
						+ ";" + rs.getInt("mDef"));
				L2DoorInstance door = DoorTable.parseList(_doorDefault.get(_doorDefault.size() - 1));
				door.setCHDoor(true);
				door.spawnMe(door.getX(), door.getY(), door.getZ());
				_doors.add(door);
				DoorTable.getInstance().putDoor(door);
				door.closeMe();
			}
			statement.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception: loadCastleDoor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	private final ExclusiveTask _endSiegeTask = new ExclusiveTask()
	{
		@Override
		protected void onElapsed()
		{
			if (!getIsInProgress())
			{
				cancel();
				return;
			}
			final long timeRemaining = _siegeEndDate.getTimeInMillis() - System.currentTimeMillis();
			if (timeRemaining <= 0)
			{
				endSiege(null);
				cancel();
				return;
			}
			if (3600000 > timeRemaining)
			{
				if (timeRemaining > 120000)
					announceToPlayer(Math.round(timeRemaining / 60000.0) + " min (a) before the end of the siege of the clan hall " + _clanhall.getName() + ".");
				else
					announceToPlayer("The siege of the clan hall " + _clanhall.getName() + " expire " + Math.round(timeRemaining / 1000.0) + " seconds (s)!");
			}
			int divider;
			if (timeRemaining > 3600000)
				divider = 3600000; // 1 hour
			else if (timeRemaining > 600000)
				divider = 600000; // 10 min
			else if (timeRemaining > 60000)
				divider = 60000; // 1 min
			else if (timeRemaining > 10000)
				divider = 10000; // 10 sec
			else
				divider = 1000; // 1 sec
			schedule(timeRemaining-((timeRemaining-500) / divider * divider));
		}
	};

	private final ExclusiveTask _startSiegeTask = new ExclusiveTask()
	{
		@Override
		protected void onElapsed()
		{
			if (getIsInProgress())
			{
				cancel();
				return;
			}
			if (!getIsRegistrationOver())
			{
				long regTimeRemaining = (getSiegeDate().getTimeInMillis()-(2*3600000)) - System.currentTimeMillis();
				
				if (regTimeRemaining > 0)
				{
					schedule(regTimeRemaining);
					return;
				}
			}
			final long timeRemaining = getSiegeDate().getTimeInMillis() - System.currentTimeMillis();
			if (timeRemaining <= 0)
			{
				startSiege();
				cancel();
				return;
			}
			if (86400000 > timeRemaining)
			{
				if (!getIsRegistrationOver())
				{
					_isRegistrationOver = true;
					announceToPlayer("The registration period at the siege of the clan hall " + _clanhall.getName() + " over.");
				}
				if (timeRemaining > 7200000)
					announceToPlayer(Math.round(timeRemaining / 3600000.0) + " hours before the siege of the clan hall: " + _clanhall.getName() + ".");
				
				else if (timeRemaining > 120000)
					announceToPlayer(Math.round(timeRemaining / 60000.0) + " minutes before the siege of the clan hall: " + _clanhall.getName() + ".");
				
				else
					announceToPlayer("The siege of the clan hall: " + _clanhall.getName() + " start in " + Math.round(timeRemaining / 1000.0) + " seconds!");
			}
			int divider;
			if (timeRemaining > 86400000)
				divider = 86400000; // 1 day
			else if (timeRemaining > 3600000)
				divider = 3600000; // 1 hour
			else if (timeRemaining > 600000)
				divider = 600000; // 10 min
			else if (timeRemaining > 60000)
				divider = 60000; // 1 min
			else if (timeRemaining > 10000)
				divider = 10000; // 10 sec
			else
				divider = 1000; // 1 sec
			schedule(timeRemaining-((timeRemaining-500) / divider * divider));
		}
	};

	public List<L2SiegeClan> getRegisteredClans()
	{
		return _registeredClans;
	}

	public void registerClan(L2PcInstance player)
	{
		if ((player.getClan() != null) && checkIfCanRegister(player))
			saveSiegeClan(player.getClan()); // Save to database
	}

	public void removeSiegeClan(L2PcInstance player)
	{
		L2Clan clan = player.getClan();
		if (clan == null || clan == _clanhall.getOwnerClan() || !checkIsRegistered(clan))
			return;
		removeSiegeClan(clan.getClanId());
	}

	private boolean checkIfCanRegister(L2PcInstance player)
	{
		L2Clan clan = player.getClan();
		if (clan == null || clan.getLevel() < 4)
		{
			player.sendMessage("Only clans reached the 4-th level and above can take part in the siege...");
			return false;
		}
		else if (getIsRegistrationOver())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.DEADLINE_FOR_SIEGE_S1_PASSED);
			sm.addString(_clanhall.getName());
			player.sendPacket(sm);
			return false;
		}
		else if (getIsInProgress())
		{
			player.sendPacket(SystemMessageId.NOT_SIEGE_REGISTRATION_TIME2);
			return false;
		}
		else if (clan.getClanId() == _clanhall.getOwnerId())
		{
			player.sendMessage("Clan owns the clan hall will automatically register on the siege.");
			return false;
		}
		else
		{
			if (checkIsRegistered(player.getClan()))
			{
				player.sendPacket(SystemMessageId.ALREADY_REQUESTED_SIEGE_BATTLE);
				return false;
			}
			if (FortressofTheDeadManager.getInstance().checkIsRegistered(player.getClan()))
			{
				player.sendPacket(SystemMessageId.ALREADY_REQUESTED_SIEGE_BATTLE);
				return false;
			}
		}
		if (getRegisteredClans().size() >= 5)
		{
			player.sendPacket(SystemMessageId.ATTACKER_SIDE_FULL);
			return false;
		}		
		return true;
	}

	public final boolean checkIsRegistered(L2Clan clan)
	{
		if (clan == null)
			return false;

		Connection con = null;
		boolean register = false;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT clan_id FROM siege_clans WHERE clan_id=? AND castle_id=?");
			statement.setInt(1, clan.getClanId());
			statement.setInt(2, 34);
			ResultSet rs = statement.executeQuery();

			if (rs.next())
				register = true;

			rs.close();
			statement.close();
		}
		catch (Exception e)
		{
			_log.error("Exception: checkIsRegistered(): " + e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return register;
	}

	public synchronized void saveSiegeClan(L2Clan clan)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement;
			statement = con.prepareStatement("INSERT INTO siege_clans (clan_id,castle_id,type,castle_owner) VALUES (?,?,?,0)");
			statement.setInt(1, clan.getClanId());
			statement.setInt(2, _clanhall.getId());
			statement.setInt(3, 1);
			statement.execute();
			statement.close();
			addAttacker(clan.getClanId());
			announceToPlayer(clan.getName() + " registered to attack the clan hall: " + _clanhall.getName());
		}
		catch (Exception e)
		{
			_log.error("Exception: saveSiegeClan(L2Clan clan, int typeId, boolean isUpdateRegistration): " + e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}		
	}

	private void loadSiegeClan()
	{
		Connection con = null;
		try
		{
			getRegisteredClans().clear();
			if (_clanhall.getOwnerId() > 0)
				addAttacker(_clanhall.getOwnerId());
			PreparedStatement statement = null;
			ResultSet rs = null;

			con = L2DatabaseFactory.getInstance().getConnection();

			statement = con.prepareStatement("SELECT clan_id,type FROM siege_clans where castle_id=?");
			statement.setInt(1, _clanhall.getId());
			rs = statement.executeQuery();

			int typeId;
			int clanId;
			while (rs.next())
			{
				typeId = rs.getInt("type");
				clanId =rs.getInt("clan_id");
				if (typeId == 1)
					addAttacker(clanId);
			}

			statement.close();
		}
		catch (Exception e)
		{
			_log.error("Exception: loadSiegeClan(): " + e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}		
	}

	public void removeSiegeClan(int clanId)
	{
		if (clanId <= 0)
			return;

		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM siege_clans WHERE castle_id=? and clan_id=?");
			statement.setInt(1, _clanhall.getId());
			statement.setInt(2, clanId);
			statement.execute();
			statement.close();

			loadSiegeClan();
		}
		catch (Exception e)
		{
			_log.error(e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void clearSiegeClan()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM siege_clans WHERE castle_id=?");
			statement.setInt(1, _clanhall.getId());
			statement.execute();
			statement.close();

			this.getRegisteredClans().clear();
		}
		catch (Exception e)
		{
			_log.error("Exception: clearSiegeClan(): " + e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void addAttacker(int clanId)
	{
		getRegisteredClans().add(new L2SiegeClan(clanId, SiegeClanType.ATTACKER));
	}

	public void announceToPlayer(String message)
	{
		// Get all players
		for (L2PcInstance player : L2World.getInstance().getAllPlayers())
		{
			player.sendMessage(message);
		}
	}

	public final boolean getIsRegistrationOver()
	{
		return _isRegistrationOver;
	}

	public void listRegisterClan(L2PcInstance player)
	{
		player.sendPacket(new SiegeInfo(null,_clanhall,getSiegeDate()));
	}
}