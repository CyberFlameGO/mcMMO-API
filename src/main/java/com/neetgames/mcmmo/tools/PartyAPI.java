//package com.neetgames.mcmmo.tools;
//
//import com.gmail.nossr50.config.Config;
//import com.gmail.nossr50.datatypes.interactions.NotificationType;
//import com.gmail.nossr50.datatypes.party.Party;
//import com.gmail.nossr50.mcMMO;
//import com.gmail.nossr50.util.player.NotificationManager;
//import org.bukkit.OfflinePlayer;
//import org.bukkit.entity.Player;
//
//import java.util.*;
//
//public final class PartyAPI {
//    private PartyAPI() {}
//
//    /**
//     * Get the name of the party a player is in.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check the party name of
//     * @return the name of the player's party, or null if not in a party
//     */
//    public static String getPartyName(Player player) {
//        if (!inParty(player)) {
//            return null;
//        }
//
//        return mcMMO.getUserManager().queryMcMMOPlayer(player).getParty().getName();
//    }
//
//    /**
//     * Checks if a player is in a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check
//     * @return true if the player is in a party, false otherwise
//     */
//    public static boolean inParty(Player player) {
//        if(mcMMO.getUserManager().queryMcMMOPlayer(player) == null)
//            return false;
//
//        return mcMMO.getUserManager().queryMcMMOPlayer(player).inParty();
//    }
//
//    /**
//     * Check if two players are in the same party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param playera The first player to check
//     * @param playerb The second player to check
//     * @return true if the two players are in the same party, false otherwise
//     */
//    public static boolean inSameParty(Player playera, Player playerb) {
//        return mcMMO.getPartyManager().inSameParty(playera, playerb);
//    }
//
//    /**
//     * Get a list of all current parties.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @return the list of parties.
//     */
//    public static List<Party> getParties() {
//        return mcMMO.getPartyManager().getParties();
//    }
//
//    /**
//     * Add a player to a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to add to the party
//     * @param partyName The party to add the player to
//     * @deprecated parties can have limits, use the other method
//     */
//    @Deprecated
//    public static void addToParty(Player player, String partyName) {
//        //Check if player profile is loaded
//        if(mcMMO.getUserManager().queryMcMMOPlayer(player) == null)
//            return;
//
//        Party party = mcMMO.getPartyManager().getParty(partyName);
//
//        if (party == null) {
//            party = new Party(new PartyLeader(player.getUniqueId(), player.getName()), partyName);
//        } else {
//            if(mcMMO.getPartyManager().isPartyFull(player, party))
//            {
//                NotificationManager.sendPlayerInformation(player, NotificationType.PARTY_MESSAGE, "Commands.Party.PartyFull", party.toString());
//                return;
//            }
//        }
//
//        mcMMO.getPartyManager().addToParty(mcMMO.getUserManager().queryMcMMOPlayer(player), party);
//    }
//
//    /**
//     * The max party size of the server
//     * 0 or less for no size limit
//     * @return the max party size on this server
//     */
//    public static int getMaxPartySize()
//    {
//        return Config.getInstance().getPartyMaxSize();
//    }
//
//    /**
//     * Add a player to a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to add to the party
//     * @param partyName The party to add the player to
//     * @param bypassLimit if true bypasses party size limits
//     */
//    //TODO: bypasslimit not used?
//    public static void addToParty(Player player, String partyName, boolean bypassLimit) {
//        //Check if player profile is loaded
//        if(mcMMO.getUserManager().queryMcMMOPlayer(player) == null)
//            return;
//
//        Party party = mcMMO.getPartyManager().getParty(partyName);
//
//        if (party == null) {
//            party = new Party(new PartyLeader(player.getUniqueId(), player.getName()), partyName);
//        }
//
//        mcMMO.getPartyManager().addToParty(mcMMO.getUserManager().queryMcMMOPlayer(player), party);
//    }
//
//    /**
//     * Remove a player from a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to remove
//     */
//    public static void removeFromParty(Player player) {
//        //Check if player profile is loaded
//        if(mcMMO.getUserManager().queryMcMMOPlayer(player) == null)
//            return;
//
//        mcMMO.getPartyManager().removeFromParty(mcMMO.getUserManager().queryMcMMOPlayer(player));
//    }
//
//    /**
//     * Get the leader of a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param partyName The party name
//     * @return the leader of the party
//     */
//    public static String getPartyLeader(String partyName) {
//        return mcMMO.getPartyManager().getPartyLeaderName(partyName);
//    }
//
//    /**
//     * Set the leader of a party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param partyName The name of the party to set the leader of
//     * @param playerName The playerName to set as leader
//     */
//    @Deprecated
//    public static void setPartyLeader(String partyName, String playerName) {
//        mcMMO.getPartyManager().setPartyLeader(mcMMO.p.getServer().getOfflinePlayer(playerName).getUniqueId(), mcMMO.getPartyManager().getParty(partyName));
//    }
//
//    /**
//     * Get a list of all players in this player's party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check
//     * @return all the players in the player's party
//     */
//    @Deprecated
//    public static List<OfflinePlayer> getOnlineAndOfflineMembers(Player player) {
//        List<OfflinePlayer> members = new ArrayList<>();
//
//        for (UUID memberUniqueId : mcMMO.getPartyManager().getAllMembers(player).keySet()) {
//            OfflinePlayer member = mcMMO.p.getServer().getOfflinePlayer(memberUniqueId);
//            members.add(member);
//        }
//        return members;
//    }
//
//    /**
//     * Get a list of all player names in this player's party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check
//     * @return all the player names in the player's party
//     */
//    @Deprecated
//    public static LinkedHashSet<String> getMembers(Player player) {
//        return (LinkedHashSet<String>) mcMMO.getPartyManager().getAllMembers(player).values();
//    }
//
//    /**
//     * Get a list of all player names and uuids in this player's party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check
//     * @return all the player names and uuids in the player's party
//     */
//    public static LinkedHashMap<UUID, String> getMembersMap(Player player) {
//        return mcMMO.getPartyManager().getAllMembers(player);
//    }
//
//    /**
//     * Get a list of all online players in this party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param partyName The party to check
//     * @return all online players in this party
//     */
//    public static List<Player> getOnlineMembers(String partyName) {
//        return mcMMO.getPartyManager().getOnlineMembers(partyName);
//    }
//
//    /**
//     * Get a list of all online players in this player's party.
//     * </br>
//     * This function is designed for API usage.
//     *
//     * @param player The player to check
//     * @return all online players in the player's party
//     */
//    public static List<Player> getOnlineMembers(Player player) {
//        return mcMMO.getPartyManager().getOnlineMembers(player);
//    }
//
//    public static boolean hasAlly(String partyName) {
//        return getAllyName(partyName) != null;
//    }
//
//    public static String getAllyName(String partyName) {
//        Party ally = mcMMO.getPartyManager().getParty(partyName).getAlly();
//        if (ally != null) {
//            return ally.getPartyName();
//        }
//
//        return null;
//    }
//}
