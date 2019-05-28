package cn.graydove.driftbottle.client.frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class WindowXY {
    public static Point getXY(int w,int h){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        int width=toolkit.getScreenSize().width;
        int height=toolkit.getScreenSize().height;
        return new Point((width-w)/2,(height-h)/2);
    }
    public static Point getXY(Dimension dimension) {
        return getXY(dimension.width, dimension.height);
    }
}
