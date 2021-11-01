package com.devteam.lib.avatar;

import com.devteam.lib.util.avatar.AvatarUtil;
import com.devteam.lib.util.dataformat.DataSerializer;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AvatarUnitTest {
    @Test
    public void test() throws IOException {
        String[] names = {"Thien Dinh"};
        for(String name : names) {
            BufferedImage img = AvatarUtil.create(150, 150, name);
            ImageIO.write(img, "png", new File("build/" + name  + ".png"));
        }
    }
}
