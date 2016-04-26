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

package com.sogeti.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * Class used to extract text data from doc or pdf file
 * 
 * @author bakoresw
 */
@Component
public class TextExtractor
{

   private static final Logger logger = Logger.getLogger(TextExtractor.class);

   /**
    * Retrieves the text from doc or pdf file
    * 
    * @param file
    * @return
    */
   public String retrieveText(byte[] resumeBytes)
   {

      String extractedText = "";
      InputStream input = null;
      OutputStream outputstream = new ByteArrayOutputStream();

      try
      {
         ParseContext context = new ParseContext();
         Detector detector = new DefaultDetector();
         Parser parser = new AutoDetectParser(detector);
         context.set(Parser.class, parser);
         Metadata metadata = new Metadata();
         outputstream = new ByteArrayOutputStream();

         input = TikaInputStream.get(resumeBytes);
         ContentHandler handler = new BodyContentHandler(outputstream);
         parser.parse(input, handler, metadata, context);

         extractedText = outputstream.toString();
      }
      catch (IOException e1)
      {
         logger.error("Exception in TextExtractor.IOException() -" + e1);
      }
      catch (SAXException e1)
      {
         logger.error("Exception in TextExtractor.SAXException() -" + e1);
      }
      catch (TikaException e1)
      {
         logger.error("Exception in TextExtractor.TikaException() -" + e1);
      }

      finally
      {
         try
         {
            input.close();
            outputstream.close();
         }
         catch (IOException e)
         {
            logger.error("Exception in TextExtractor IOException -" + e);
         }
      }

      return extractedText;
   }
}
