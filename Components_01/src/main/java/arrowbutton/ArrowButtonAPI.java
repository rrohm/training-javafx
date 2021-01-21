/*
 *  ----- AEONIUM SOFTWARE SYSTEMS UG (HAFTUNGSB.) SOURCE CODE LICENSE -----
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright 2013 AEONIUM SOFTWARE SYSTEMS UG (HAFTUNGSB.) - ALL RIGHTS RESERVED.
 *
 *  The contents of this file are intellectual property of
 *  aeonium software systems UG (haftungsb.), Robert Rohm. All rights reserved.
 *  You must NOT, especially:
 *  - redistribute this file in source form,
 *  - redistribute this file in binary form,
 *  - modify this file,
 *  - remove or modify this copyright information,
 *  - use this file for your own work
 *  WITHOUT WRITTEN PERMISSION.
 *
 *  Anyway, we appreciate any interest in our work and knowledge.
 *  So, if you wish to use this file for your own purposes,
 *  please contact us:
 *  mailto:info@aeonium-systems.de
 *
 *
 *  © 2013 aeonium software systems UG (haftungsb.), Robert Rohm.
 */
package arrowbutton;

/**
 *
 * @author robert rohm
 */
public interface ArrowButtonAPI {
  
  public enum Direction{
    LEFT, RIGHT
  }

  public void setText(String text);

  public void setDirection(Direction direction);
}
