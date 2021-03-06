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
package org.l2jmobius.gameserver.handler.skillhandlers;

import org.l2jmobius.gameserver.ai.CtrlEvent;
import org.l2jmobius.gameserver.handler.ISkillHandler;
import org.l2jmobius.gameserver.model.Skill;
import org.l2jmobius.gameserver.model.Skill.SkillType;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.instance.MonsterInstance;
import org.l2jmobius.gameserver.model.actor.instance.PlayerInstance;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.skills.Formulas;

/**
 * @author _drunk_ TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class Spoil implements ISkillHandler
{
	private static final SkillType[] SKILL_IDS =
	{
		SkillType.SPOIL
	};
	
	@Override
	public void useSkill(Creature creature, Skill skill, WorldObject[] targets)
	{
		if (!(creature instanceof PlayerInstance))
		{
			return;
		}
		
		if (targets == null)
		{
			return;
		}
		
		for (WorldObject target1 : targets)
		{
			if (!(target1 instanceof MonsterInstance))
			{
				continue;
			}
			
			MonsterInstance target = (MonsterInstance) target1;
			
			if (target.isSpoil())
			{
				creature.sendPacket(new SystemMessage(SystemMessageId.ALREDAY_SPOILED));
				continue;
			}
			
			boolean spoil = false;
			if (!target.isDead())
			{
				spoil = Formulas.calcMagicSuccess(creature, (Creature) target1, skill);
				
				if (spoil)
				{
					target.setSpoil(true);
					target.setIsSpoiledBy(creature.getObjectId());
					creature.sendPacket(new SystemMessage(SystemMessageId.SPOIL_SUCCESS));
				}
				else
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.S1_WAS_UNAFFECTED_BY_S2);
					sm.addString(target.getName());
					sm.addSkillName(skill.getDisplayId());
					creature.sendPacket(sm);
				}
				target.getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, creature);
			}
		}
	}
	
	@Override
	public SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}
