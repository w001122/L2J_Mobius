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
package org.l2jmobius.gameserver.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminAdmin;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminAio;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminAnnouncements;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminBan;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminBuffs;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminCTFEngine;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminCache;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminChangeAccessLevel;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminChristmas;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminCreateItem;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminCursedWeapons;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminDMEngine;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminDelete;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminDonator;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminDoorControl;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminEditChar;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminEditNpc;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminEffects;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminEnchant;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminEventEngine;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminExpSp;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminFence;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminFightCalculator;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminFortSiege;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminGeodata;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminGm;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminGmChat;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminGmSpeed;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminHeal;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminHelpPage;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminHide;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminInvul;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminKick;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminKill;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminLevel;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminLogin;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMammon;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminManor;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMassControl;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMassRecall;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMenu;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMobGroup;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminMonsterRace;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminNoble;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminPForge;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminPetition;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminPledge;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminPolymorph;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminQuest;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminReload;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminRepairChar;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminRes;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminRideWyvern;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminScript;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminShop;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminShutdown;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminSiege;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminSkill;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminSpawn;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminSuperHaste;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminTarget;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminTeleport;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminTest;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminTownWar;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminTvTEngine;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminUnblockIp;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminVIPEngine;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminWho;
import org.l2jmobius.gameserver.handler.admincommandhandlers.AdminZone;

/**
 * @version $Revision: 1.1.4.5 $ $Date: 2005/03/27 15:30:09 $
 */
public class AdminCommandHandler
{
	protected static final Logger LOGGER = Logger.getLogger(AdminCommandHandler.class.getName());
	
	private static AdminCommandHandler _instance;
	
	private final Map<String, IAdminCommandHandler> _datatable;
	
	public static AdminCommandHandler getInstance()
	{
		if (_instance == null)
		{
			_instance = new AdminCommandHandler();
		}
		return _instance;
	}
	
	private AdminCommandHandler()
	{
		_datatable = new HashMap<>();
		registerAdminCommandHandler(new AdminAdmin());
		registerAdminCommandHandler(new AdminInvul());
		registerAdminCommandHandler(new AdminDelete());
		registerAdminCommandHandler(new AdminKill());
		registerAdminCommandHandler(new AdminTarget());
		registerAdminCommandHandler(new AdminShop());
		registerAdminCommandHandler(new AdminCTFEngine());
		registerAdminCommandHandler(new AdminVIPEngine());
		registerAdminCommandHandler(new AdminDMEngine());
		registerAdminCommandHandler(new AdminAnnouncements());
		registerAdminCommandHandler(new AdminCreateItem());
		registerAdminCommandHandler(new AdminHeal());
		registerAdminCommandHandler(new AdminHelpPage());
		registerAdminCommandHandler(new AdminShutdown());
		registerAdminCommandHandler(new AdminSpawn());
		registerAdminCommandHandler(new AdminSkill());
		registerAdminCommandHandler(new AdminScript());
		registerAdminCommandHandler(new AdminExpSp());
		registerAdminCommandHandler(new AdminEventEngine());
		registerAdminCommandHandler(new AdminFence());
		registerAdminCommandHandler(new AdminGmChat());
		registerAdminCommandHandler(new AdminGmSpeed());
		registerAdminCommandHandler(new AdminHide());
		registerAdminCommandHandler(new AdminSuperHaste());
		registerAdminCommandHandler(new AdminEditChar());
		registerAdminCommandHandler(new AdminGm());
		registerAdminCommandHandler(new AdminTeleport());
		registerAdminCommandHandler(new AdminRepairChar());
		registerAdminCommandHandler(new AdminChangeAccessLevel());
		registerAdminCommandHandler(new AdminChristmas());
		registerAdminCommandHandler(new AdminBan());
		registerAdminCommandHandler(new AdminPolymorph());
		registerAdminCommandHandler(new AdminReload());
		registerAdminCommandHandler(new AdminKick());
		registerAdminCommandHandler(new AdminMonsterRace());
		registerAdminCommandHandler(new AdminEditNpc());
		registerAdminCommandHandler(new AdminFightCalculator());
		registerAdminCommandHandler(new AdminMenu());
		registerAdminCommandHandler(new AdminSiege());
		registerAdminCommandHandler(new AdminFortSiege());
		registerAdminCommandHandler(new AdminPetition());
		registerAdminCommandHandler(new AdminPForge());
		registerAdminCommandHandler(new AdminEffects());
		registerAdminCommandHandler(new AdminDoorControl());
		registerAdminCommandHandler(new AdminTest());
		registerAdminCommandHandler(new AdminEnchant());
		registerAdminCommandHandler(new AdminMassRecall());
		registerAdminCommandHandler(new AdminMassControl());
		registerAdminCommandHandler(new AdminMobGroup());
		registerAdminCommandHandler(new AdminRes());
		registerAdminCommandHandler(new AdminMammon());
		registerAdminCommandHandler(new AdminUnblockIp());
		registerAdminCommandHandler(new AdminPledge());
		registerAdminCommandHandler(new AdminRideWyvern());
		registerAdminCommandHandler(new AdminLogin());
		registerAdminCommandHandler(new AdminCache());
		registerAdminCommandHandler(new AdminLevel());
		registerAdminCommandHandler(new AdminQuest());
		registerAdminCommandHandler(new AdminZone());
		registerAdminCommandHandler(new AdminCursedWeapons());
		registerAdminCommandHandler(new AdminGeodata());
		registerAdminCommandHandler(new AdminManor());
		registerAdminCommandHandler(new AdminTownWar());
		registerAdminCommandHandler(new AdminTvTEngine());
		registerAdminCommandHandler(new AdminDonator());
		registerAdminCommandHandler(new AdminNoble());
		registerAdminCommandHandler(new AdminBuffs());
		registerAdminCommandHandler(new AdminAio());
		registerAdminCommandHandler(new AdminWho());
		
		LOGGER.info("AdminCommandHandler: Loaded " + _datatable.size() + " handlers.");
	}
	
	public void registerAdminCommandHandler(IAdminCommandHandler handler)
	{
		String[] ids = handler.getAdminCommandList();
		for (String element : ids)
		{
			if (_datatable.keySet().contains(new String(element)))
			{
				LOGGER.warning("Duplicated command \"" + element + "\" definition in " + handler.getClass().getName() + ".");
			}
			else
			{
				_datatable.put(element, handler);
			}
		}
	}
	
	public IAdminCommandHandler getAdminCommandHandler(String adminCommand)
	{
		String command = adminCommand;
		
		if (adminCommand.indexOf(" ") != -1)
		{
			command = adminCommand.substring(0, adminCommand.indexOf(" "));
		}
		
		return _datatable.get(command);
	}
}