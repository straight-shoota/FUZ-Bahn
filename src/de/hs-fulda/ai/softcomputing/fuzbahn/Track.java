package de.hs-fulda.ai.softcomputing.fuzbahn;

import double;

public class Track {

  /** 
   *  real length of track (in meters)
   */
  public double length;

  public elements;

    public Train myTrain;
    public TrackElement nutzt;
    public TrackElementComparator myTrackElementComparator;

  public void getPrecedingElements(TrackElement e) {
  }

  public void addElement(TrackElement e) {
  }

  public TrackElement removeElement(TrackElement e) {
  return null;
  }

  /** 
   *  This method must be called when the position a movable TrackElement (i.e. Train) changes. It recalculates the order of elements on track if necessary.
   */
  public void updateElement(TrackElement e) {
  }

}