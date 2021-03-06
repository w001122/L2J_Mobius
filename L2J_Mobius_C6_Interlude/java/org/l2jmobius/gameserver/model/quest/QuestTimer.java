/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2jmobius.gameserver.model.quest;

import java.util.concurrent.ScheduledFuture;

import org.l2jmobius.commons.concurrent.ThreadPool;
import org.l2jmobius.gameserver.model.actor.instance.NpcInstance;
import org.l2jmobius.gameserver.model.actor.instance.PlayerInstance;

public class QuestTimer
{
	public class ScheduleTimerTask implements Runnable
	{
		@Override
		public void run()
		{
			if (!_isActive)
			{
				return;
			}
			
			try
			{
				if (!_isRepeating)
				{
					cancel();
				}
				_quest.notifyEvent(_name, _npc, _player);
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	boolean _isActive = true;
	final String _name;
	final Quest _quest;
	final NpcInstance _npc;
	final PlayerInstance _player;
	final boolean _isRepeating;
	private ScheduledFuture<?> _schedular;
	
	public QuestTimer(Quest quest, String name, long time, NpcInstance npc, PlayerInstance player, boolean repeating)
	{
		_name = name;
		_quest = quest;
		_player = player;
		_npc = npc;
		_isRepeating = repeating;
		if (repeating)
		{
			_schedular = ThreadPool.scheduleAtFixedRate(new ScheduleTimerTask(), time, time); // Prepare auto end task
		}
		else
		{
			_schedular = ThreadPool.schedule(new ScheduleTimerTask(), time); // Prepare auto end task
		}
	}
	
	public QuestTimer(Quest quest, String name, long time, NpcInstance npc, PlayerInstance player)
	{
		this(quest, name, time, npc, player, false);
	}
	
	public QuestTimer(QuestState qs, String name, long time)
	{
		this(qs.getQuest(), name, time, null, qs.getPlayer(), false);
	}
	
	public void cancel()
	{
		cancel(true);
	}
	
	public void cancel(boolean removeTimer)
	{
		_isActive = false;
		
		if (_schedular != null)
		{
			_schedular.cancel(false);
		}
		
		if (removeTimer)
		{
			_quest.removeQuestTimer(this);
		}
	}
	
	// public method to compare if this timer matches with the key attributes passed.
	// a quest and a name are required.
	// null npc or player act as wildcards for the match
	public boolean isMatch(Quest quest, String name, NpcInstance npc, PlayerInstance player)
	{
		if ((quest == null) || (name == null))
		{
			return false;
		}
		
		if ((quest != _quest) || (name.compareToIgnoreCase(_name) != 0))
		{
			return false;
		}
		
		return (npc == _npc) && (player == _player);
	}
	
	public boolean getIsActive()
	{
		return _isActive;
	}
	
	public boolean getIsRepeating()
	{
		return _isRepeating;
	}
	
	public Quest getQuest()
	{
		return _quest;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public NpcInstance getNpc()
	{
		return _npc;
	}
	
	public PlayerInstance getPlayer()
	{
		return _player;
	}
	
	@Override
	public String toString()
	{
		return _name;
	}
}
