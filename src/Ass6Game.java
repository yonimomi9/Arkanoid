/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Ass6Game.java
 */

import Environment.DirectHit;
import Environment.GameFlow;
import Environment.Green3;
import Environment.LevelInformation;
import Environment.WideEasy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The type Ass5Game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<String> order = new ArrayList<>();
        for (String arg : args) {
            if (Objects.equals(arg, "1") || Objects.equals(arg, "2")
                    || Objects.equals(arg, "3")) {
                order.add(arg);
            }
        }
        List<LevelInformation> lvls = new LinkedList<>();
        if (order.size() > 0) {
            for (int i = 0; i < order.size(); i++) {
                if (Objects.equals(order.get(i), "1")) {
                    lvls.add(new DirectHit());
                } else if (Objects.equals(order.get(i), "2")) {
                    lvls.add(new WideEasy());
                } else {
                    lvls.add(new Green3());
                }
            }
        } else {
            lvls.add(new DirectHit());
            lvls.add(new WideEasy());
            lvls.add(new Green3());
        }
        GameFlow gf = new GameFlow();
        gf.runLevels(lvls);

    }
}

