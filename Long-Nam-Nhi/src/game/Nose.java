package game;

import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class Nose extends GameObject {

    public Nose() {
        this.position = new Vector2D(500, 100);
        int width = 200;
        int height = 265;
        renderer = new ImageRenderer("C:\\Users\\Long\\IdeaProjects\\N-L-N (3)\\Long-Nam-Nhi\\resources\\nose2.jpg", width, height);
    }

}
