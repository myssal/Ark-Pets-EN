/** Copyright (c) 2022-2023, Harry Huang
 * At GPL-3.0 License
 */
package cn.harryh.arkpets.behaviors;

import cn.harryh.arkpets.ArkConfig;
import cn.harryh.arkpets.utils.AnimData;
import com.badlogic.gdx.utils.Array;


public class BehaviorOperBuild extends Behavior {

    public BehaviorOperBuild(ArkConfig $config, String[] $animList) {
        super($config, $animList);
        action_list = new AnimData.AnimAutoData[] {
            new AnimData.AnimAutoData(new AnimData("Relax", true, true, 0, 0),
                    4f, (int) (256 / Math.sqrt(config.behavior_ai_activation))),
            new AnimData.AnimAutoData(new AnimData("Move", true, true, 0, 1),
                    2f, 32*(config.behavior_allow_walk?1:0)),
            new AnimData.AnimAutoData(new AnimData("Move", true, true, 0, -1),
                    2f, 32*(config.behavior_allow_walk?1:0)),
            new AnimData.AnimAutoData(new AnimData("Sit", true, true, 50, 0),
                    6f, 64*(config.behavior_allow_sit?1:0))
        };
    }

    public static boolean match(String[] animList) {
        Array<String> arr = new Array<>(animList);
        if (!arr.contains("Interact", false))
            return false;
        if (!arr.contains("Relax", false))
            return false;
        if (!arr.contains("Move", false))
            return false;
        if (!arr.contains("Sit", false))
            return false;
        if (!arr.contains("Sleep", false))
            return false;
        return true;
    }

    public AnimData defaultAnim() {
        return new AnimData("Relax", true, true);
    }

    public AnimData clickEnd() {
        return config.behavior_allow_interact?new AnimData("Interact", false, false,
                new AnimData("Relax", true, true)) : null;
    }

    public AnimData dragStart() {
        return action_list[0].ANIM;
    }

    public AnimData drop() {
        return config.behavior_allow_interact?new AnimData("Interact", false, false,
                new AnimData("Relax", true, true)) : null;
    }
}