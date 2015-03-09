package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
	  String output = str.toUpperCase();
	  super.write(output, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] output = new char[cbuf.length];
    for(int i = 0; i<cbuf.length; i++){
    	output[i] = Character.toUpperCase(cbuf[i]);
    }
    super.write(output, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int output = Character.toUpperCase(c);
    super.write(output);
  }

}
