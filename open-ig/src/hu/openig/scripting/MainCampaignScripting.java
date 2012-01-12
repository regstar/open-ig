/*
 * Copyright 2008-2012, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */

package hu.openig.scripting;

import hu.openig.model.BattleInfo;
import hu.openig.model.Building;
import hu.openig.model.CampaignScripting;
import hu.openig.model.Fleet;
import hu.openig.model.FleetMode;
import hu.openig.model.InventoryItem;
import hu.openig.model.Planet;
import hu.openig.model.Player;
import hu.openig.model.ResearchType;
import hu.openig.model.VideoMessage;
import hu.openig.model.World;
import hu.openig.utils.U;
import hu.openig.utils.XElement;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The scripting for the original game's campaign.
 * @author akarnokd, 2012.01.12.
 */
public class MainCampaignScripting implements CampaignScripting {
	/** The current world. */
	protected World world;
	/** The current player. */
	protected Player player;
	@Override
	public List<VideoMessage> getSendMessages() {
		List<VideoMessage> result = U.newArrayList();
		
		for (VideoMessage msg : world.bridge.sendMessages.values()) {
//			if (msg.visible) {
				result.add(msg);
//			}
		}
		Collections.sort(result, new Comparator<VideoMessage>() {
			@Override
			public int compare(VideoMessage o1, VideoMessage o2) {
				return o1.id.compareTo(o2.id);
			}
		});
		return result;
	}

	@Override
	public List<VideoMessage> getReceiveMessages() {
		List<VideoMessage> result = U.newArrayList();

		for (VideoMessage msg : world.bridge.receiveMessages.values()) {
//			if (msg.visible) {
				result.add(msg);
//			}
		}
		
		Collections.sort(result, new Comparator<VideoMessage>() {
			@Override
			public int compare(VideoMessage o1, VideoMessage o2) {
				return o1.id.compareTo(o2.id);
			}
		});
		return result;
	}
	/**
	 * Get a receive-message.
	 * @param id the message id
	 * @return the video message or null if not available
	 */
	VideoMessage receive(String id) {
		return world.bridge.receiveMessages.get(id);
	}
	/**
	 * Get a send-message.
	 * @param id the message id
	 * @return the video message or null if not available
	 */
	VideoMessage send(String id) {
		return world.bridge.sendMessages.get(id);
	}
	@Override
	public void init(World world, XElement in) {
		this.world = world;
		player = world.player;
	}

	@Override
	public void load(XElement in) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(XElement out) {
		// TODO Auto-generated method stub

	}
	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResearched(Player player, ResearchType rt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProduced(Player player, ResearchType rt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroyed(Fleet winner, Fleet loser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onColonized(Planet planet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConquered(Planet planet, Player previousOwner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerBeaten(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDiscovered(Player player, Planet planet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDiscovered(Player player, Player other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDiscovered(Player player, Fleet fleet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLostSight(Player player, Fleet fleet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFleetAt(Fleet fleet, double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFleetAt(Fleet fleet, Fleet other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFleetAt(Fleet fleet, Planet planet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStance(Player first, Player second) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAllyAgainst(Player first, Player second, Player commonEnemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleComplete(Player player, BattleInfo battle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTime() {
		// TODO Auto-generated method stub
		for (Planet p : player.planets.keySet()) {
			if (p.owner != player) {
				hideMessages(p.id);
			} else {
				if (isUnderAttack(p)) {
					VideoMessage msg = send(p.id + "-Is-Under-Attack");
					if (msg != null) {
						msg.visible = true;
					}
				} else {
					VideoMessage msg = send(p.id + "-Not-Under-Attack");
					if (msg != null) {
						msg.visible = false;
					}
				}
			}
		}
	}
	/**
	 * Hide messages that start with the given id.
	 * @param id the message id
	 */
	void hideMessages(String id) {
		for (VideoMessage msg : world.bridge.sendMessages.values()) {
			if (msg.id.startsWith(id)) {
				msg.visible = false;
			}
		}
		for (VideoMessage msg : world.bridge.receiveMessages.values()) {
			if (msg.id.startsWith(id)) {
				msg.visible = false;
			}
		}
	}
	/**
	 * Check if the planet is under attack by a fleet.
	 * @param p the planet
	 * @return the fleet
	 */
	public boolean isUnderAttack(Planet p) {
		for (Fleet f : player.fleets.keySet()) {
			if (f.owner != player && f.targetPlanet() == p && f.mode == FleetMode.ATTACK) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBuildingComplete(Planet planet, Building building) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRepairComplete(Planet planet, Building building) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrading(Planet planet, Building building, int newLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInventoryAdd(Planet planet, InventoryItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInventoryRemove(Planet planet, InventoryItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLost(Planet planet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLost(Fleet fleet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVideoComplete(String video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSoundComplete(String audio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlanetInfected(Planet planet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlanetCured(Planet planet) {
		// TODO Auto-generated method stub
		
	}
	
}
