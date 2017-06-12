package br.com.geekfox.apps.DataMinions.converters;

import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class UserMinionConverter {

    public static UserMinion jsonToDomain(DataMinionData data) {
        UserMinion minion = new UserMinion();
        minion.setId(data.getId());
        minion.setDataminionId(data.getMinionId());
        if (data.getNickname() == null || data.getNickname().isEmpty()) {
            minion.setNickname(data.getName());
        }
        else {
            minion.setNickname(data.getNickname());
        }
        minion.setStatus(data.getStatus());
        minion.setLevel(data.getLevel());
        //minion.setStatus(data.getStatus());
        //minion.setHealth(data.getHealth());
        minion.setXp(data.getXp());
        minion.setAtk1Id(data.getSlot1AtkId());
        minion.setAtk2Id(data.getSlot2AtkId());
        minion.setAtk3Id(data.getSlot3AtkId());
        minion.setAtk4Id(data.getSlot4AtkId());
        return minion;
    }

    public static DataMinionData domainToJson(UserMinion minion) {
        DataMinionData data = new DataMinionData();
        data.setId(minion.getId());
        data.setMinionId(minion.getDataminionId());
        //data.setHealth(minion.getHealth());
        //data.setStatus(minion.getStatus());
        data.setLevel(minion.getLevel());
        data.setXp(minion.getXp());
        data.setSlot1AtkId(minion.getAtk1Id());
        data.setSlot2AtkId(minion.getAtk2Id());
        data.setSlot3AtkId(minion.getAtk3Id());
        data.setSlot4AtkId(minion.getAtk4Id());
        return data;
    }
}
