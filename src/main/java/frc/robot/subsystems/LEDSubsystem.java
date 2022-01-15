package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.LEDConstants;

public class LEDSubsystem extends SubsystemBase {
  private final AddressableLED m_led = new AddressableLED(LEDConstants.kLEDPWMPort);
  private final AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(LEDConstants.kBufferSize);
  private int m_rainbowFirstPixelHue;

  private boolean redBall = false;
  private boolean blueBall = false;

  public LEDSubsystem() {
    m_led.setLength(m_ledBuffer.getLength());
    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  public void redBall(boolean exists) {
    redBall = exists;
  }

  public void blueBall(boolean exists) {
    blueBall = exists;
  }

  public void rainbow() {
    // For every pixel
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Calculate the hue - hue is easier for rainbows because the color
      // shape is a circle so only one value needs to precess
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
      // Set the value
      m_ledBuffer.setHSV(i, hue, 255, 128);
    }
    // Increase by to make the rainbow "move"
    m_rainbowFirstPixelHue += 3;
    // Check bounds
    m_rainbowFirstPixelHue %= 180;
    m_led.setData(m_ledBuffer);
  }

  private void setBallLEDs() {
    if (redBall && blueBall) {
      setFrontHalf(() -> m_ledBuffer.setRGB(index, r, g, b));
      setBackHalf(() -> m_ledBuffer.setRGB(index, r, g, b));
    }
    else {
      if (redBall) {
        setAll(() -> m_ledBuffer.setRGB(index, r, g, b));
      }
      if (blueBall) {
        setAll(() -> m_ledBuffer.setRGB(index, r, g, b));
      }
    }
  }

  private void setAll(Runnable ledSet) {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      ledSet.run();
    }
  }

  private void setFrontHalf(Runnable ledSet) {
    for (var i = 0; i < m_ledBuffer.getLength() / 2; i++) {
      ledSet.run();
    }
  }

  private void setBackHalf(Runnable ledSet) {
    for (var i = Math.ceil(m_ledBuffer.getLength() / 2.0); i < m_ledBuffer.getLength() / 2; i++) {
      ledSet.run();
    }
  }
}