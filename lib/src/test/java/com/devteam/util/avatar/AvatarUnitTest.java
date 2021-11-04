package com.devteam.util.avatar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

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
