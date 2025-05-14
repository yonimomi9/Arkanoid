/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Environment.SpriteCollection.java
 */
package Environment;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Environment.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Environment.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    public SpriteCollection(List<Sprite> list){
        this.sprites = list;
    }
    /**
     * Add sprite into the list of sprites.
     *
     * @param s the Environment.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notify all the sprites to activate timePassed method each by its own demands.
     */
    public void notifyAllTimePassed() {
        int size = this.sprites.size() - 1;
        for (int i = 0; i < size - 1; i++) {
            this.getList().get(i).timePassed();
        }
    }

    /**
     * get the sprite list.
     *
     * @return the object's list.
     */
    public List<Sprite> getList() {
        return this.sprites;
    }

    /**
     * Notify all the sprites to activate drawOn method each by its own demands.
     *
     * @param d the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * Removes the sprite from the SpriteCollection.
     *
     * @param s the s
     */
    public void remove(Sprite s) {
        this.sprites.remove(s);
    }

}