package com.chrisrooney.gasmaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


import static org.junit.Assert.assertTrue;



@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

  @Test
  public void shouldFail() {
      assertTrue(false);
  }
  
  @Test
  public void shouldNotBeNull() {
    assertThat(activity).isNotNull();

    TextView textView = (TextView) activity.findViewById(R.id.textPrice);
    assertThat(textView).isNotNull();

    Button button = (Button) activity.findViewById(R.id.Save);
    assertThat(button).isNotNull();

    EditText editText = (EditText) activity.findViewById(R.id.Price);
    assertThat(editText).isNotNull();
  }
}