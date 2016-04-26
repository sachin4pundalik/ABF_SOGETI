/*------------------------------------------------------------------------------
 *     Ident: Centre of Excellence Java
 *    Author: pawarraj
 * Copyright: (c) 10 august 2015 Sogeti Nederland B.V. All Rights Reserved.
 *------------------------------------------------------------------------------
 * Sogeti Nederland B.V.    |  No part of this file may be reproduced or 
 * Centre of Excellence Java|  transmitted in any form or by any means,        
 * Lange Dreef 17           |  electronic or mechanical, for the purpose,      
 * 4131 NJ VIANEN           |  without the express written permission of the   
 * The Netherlands          |  copyright holder.
 *------------------------------------------------------------------------------
 */

package com.sogeti.GenericExceptions;

public class BaseRuntimeException extends RuntimeException
{

   /**
    * <code>serialVersionUID</code> indicates/is used for.
    */
   private static final long serialVersionUID = 1L;

   private final Throwable error;

   public BaseRuntimeException(String message, Throwable error)
   {
      super(message);
      this.error = error;
   }

   public Throwable getError()
   {
      return error;
   }
}
