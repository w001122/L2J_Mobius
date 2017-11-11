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
package quests.Q10796_TheEyeThatDefiedTheGods;

import java.util.HashSet;
import java.util.Set;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.NpcLogListHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;

/**
 * The Eye that Defied the Gods (10796)
 * @URL https://l2wiki.com/The_Eye_that_Defied_the_Gods
 * @author Gigi
 */
public class Q10796_TheEyeThatDefiedTheGods extends Quest
{
	// NPCs
	private static final int HERMIT = 31616;
	private static final int EYE_OF_ARGOS = 31683;
	// Monsters
	private static final int[] MONSTERS =
	{
		21294, // Canyon Antelope
		21296, // Canyon Bandersnatch
		23311, // Valley Buffalo
		23312, // Valley Grendel
		21295, // Canyon Antelope Slave
		21297, // Canyon Bandersnatch Slave
		21299, // Valley Buffalo Slave
		21304 // Valley Grendel Slave
	};
	// Items
	private static final int EAA = 730;
	// Misc
	private static final int MIN_LEVEL = 70;
	private static final int MAX_LEVEL = 75;
	private static final String KILL_COUNT_VAR = "KillCount";
	
	public Q10796_TheEyeThatDefiedTheGods()
	{
		super(10796);
		addStartNpc(HERMIT);
		addTalkId(HERMIT, EYE_OF_ARGOS);
		addKillId(MONSTERS);
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "no_level.html");
		addCondRace(Race.ERTHEIA, "noErtheya.html");
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		String htmltext = event;
		switch (event)
		{
			case "31616-02.htm":
			case "31616-03.htm":
			{
				break;
			}
			case "31616-04.htm":
			{
				qs.startQuest();
				break;
			}
			case "31683-02.html":
			{
				if (qs.isCond(2) && (qs.getInt(KILL_COUNT_VAR) >= 200))
				{
					
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExpAndSp(player, 1088640, 261);
						giveStoryQuestReward(player, 2);
						giveItems(player, EAA, 2);
						qs.exitQuest(false, true);
						htmltext = event;
					}
					else
					{
						htmltext = getNoQuestLevelRewardMsg(player);
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (npc.getId())
		{
			case HERMIT:
			{
				if (qs.isCreated())
				{
					htmltext = "31616-01.htm";
				}
				else if (qs.isCond(1))
				{
					htmltext = "31616-05.html";
				}
				if (qs.isCompleted())
				{
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
			case EYE_OF_ARGOS:
			{
				if (qs.isCond(2))
				{
					htmltext = "31683-01.html";
				}
				else if (qs.isCompleted())
				{
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isCond(1))
		{
			int count = qs.getInt(KILL_COUNT_VAR);
			qs.set(KILL_COUNT_VAR, ++count);
			if (count >= 200)
			{
				qs.setCond(2, true);
			}
			else
			{
				sendNpcLogList(killer);
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public Set<NpcLogListHolder> getNpcLogList(L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(1))
		{
			final int killCount = qs.getInt(KILL_COUNT_VAR);
			if (killCount > 0)
			{
				final Set<NpcLogListHolder> holder = new HashSet<>();
				holder.add(new NpcLogListHolder(NpcStringId.DEFEAT_THE_BEASTS_OF_THE_VALLEY, killCount));
				return holder;
			}
		}
		return super.getNpcLogList(player);
	}
}