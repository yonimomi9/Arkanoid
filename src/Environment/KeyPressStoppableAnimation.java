/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * KeyPressStoppableAnimation.java
 */
package Environment;

import biuoop.DrawSurface;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private biuoop.KeyboardSensor sensor;
    private String key;
    private String secondKey;
    private String lastKey;
    private Animation animation;

    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(biuoop.KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.secondKey = null;
        this.animation = animation;
        this.lastKey = null;
        this.isAlreadyPressed = true;
    }

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key1      the key 1
     * @param key2      the key 2
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(biuoop.KeyboardSensor sensor, String key1, String key2,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key1;
        this.secondKey = key2;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        if (this.secondKey != null) {
            return this.twoKeysShouldStop();
        }
        if (this.sensor.isPressed(this.key)) {
            return !this.isAlreadyPressed;
        } else {
            this.isAlreadyPressed = false;
            return false;
        }
    }

    private boolean twoKeysShouldStop() {
        if (this.sensor.isPressed(this.key) || this.sensor.isPressed(this.secondKey)) {
            this.lastKey = this.whatIsPressed();
            return !this.isAlreadyPressed;
        } else {
            return false;
        }
    }

    private String whatIsPressed() {
        if (this.sensor.isPressed(this.key)) {
            return this.key;
        }
        return this.secondKey;
    }
}
