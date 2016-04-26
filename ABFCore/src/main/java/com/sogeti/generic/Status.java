/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: pawarraj
 ** Copyright: (c) 19-Jun-2015 Sogeti Nederland B.V. All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Sogeti Nederland B.V.            |  No part of this file may be reproduced  
 ** Distributed Software Engineering |  or transmitted in any form or by any        
 ** Lange Dreef 17                   |  means, electronic or mechanical, for the      
 ** 4131 NJ Vianen                   |  purpose, without the express written    
 ** The Netherlands                  |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 */
package com.sogeti.generic;

/**
 * Generic status for all REST call
 *
 * @version $Id:$
 * @author guptaros (c) 19-Jun-2015, Sogeti B.V.
 */
public class Status
{

   private int code;
   private String message;
   private String remark;

   /**
    * Get the remark.
    *
    * @return Returns the remark as a String.
    */
   public String getRemark()
   {
      return remark;
   }

   /**
    * Set the remark to the specified value.
    *
    * @param remark The remark to set.
    */
   public void setRemark(String remark)
   {
      this.remark = remark;
   }

   public Status()
   {
   }

   public Status(int code, String message)
   {
      this.code = code;
      this.message = message;
   }

   public Status(int code, String message, String remarks)
   {
      this.code = code;
      this.message = message;
      this.remark = remarks;
   }

   public int getCode()
   {
      return code;
   }

   public void setCode(int code)
   {
      this.code = code;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }
}
