package de.hs-fulda.ai.softcomputing.fuzbahn;

import double;

public class Train extends TrackElement {

  /** 
   *  Current speed of the train, is influenced by acceleration and braking power. Values in meters per second (positive only). Defaults to 0.0.
   */
  private double speed;

  /** 
   *  Current power of train engine. Valid values are in percent ranging from -1.0 to 1.0 and representing values between acclerationMin and accelerationMax.
   */
  private double power;

  private double brakeForce;

  /** 
   *  Maximal acceleration supported by train engine. Value should be up to 1 m/s².
   */
  protected double accelerationMax;
  /* {see=acceleration}*/

  /** 
   *  Minimal acceleration supported by train engine. Values should usually be <0.0 as a result of dynamic braking.
   */
  protected double accelerationMin;
  /* {see=acceleration}*/

  protected double brakeAccelerationMax;

    public Track myTrack;

  /** 
   *  Computes current acceleration by adding getEngineAcceleration() and getBrakeAcceleration()
   */
  public double getAcceleration() {
  return 0.0;
  }

  /** 
   *  Returns current acceleration provided by the trains main engine depending on current engine power. Values range from acclerationMin to accelerationMax
   */
  public double getEngineAcceleration() {
  return 0.0;
  }

  public double getSpeed() {
  return 0.0;
  }

  /** 
   *  Returns current acceleration (actually it's deceleration) provided by the actual brake force. Values range from 0 to brakeAccelerationMax
   */
  public double getBrakeAcceleration() {
  return 0.0;
  }

  public void setPower(double p) {
  }

  public void setBrakeForce(double f) {
  }

  /** 
   *  Updates current speed according to current acceleration during one time unit.
   */
  public void update() {
  }

}