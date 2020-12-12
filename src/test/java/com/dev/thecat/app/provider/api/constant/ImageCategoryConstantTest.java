package com.dev.thecat.app.provider.api.constant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageCategoryConstantTest {
  @SuppressWarnings({"AccessStaticViaInstance", "InstantiationOfUtilityClass"})
  @Test
  public void instanceTest() {
    ImageCategoryConstant imageCategoryConstant = new ImageCategoryConstant();
    assertNull(imageCategoryConstant.NONE);
    assertNotNull(imageCategoryConstant.HAT);
    assertNotNull(imageCategoryConstant.SUNGLASSES);
  }
}
