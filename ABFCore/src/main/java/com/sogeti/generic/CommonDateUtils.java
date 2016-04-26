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

package com.sogeti.generic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonDateUtils
{

   private CommonDateUtils()
   {
   }

   private static final String inputDateFormat = "yyyy-MM-dd";
   private static final String outputDateFormat = "dd/MM/yyyy";

   public static String formatDate(String inputDate) throws ParseException
   {
      // formatting date in Java using SimpleDateFormat
      SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(inputDateFormat);
      Date d = DATE_FORMAT.parse(inputDate);
      DATE_FORMAT.applyPattern(outputDateFormat);
      return DATE_FORMAT.format(d);
   }

}
