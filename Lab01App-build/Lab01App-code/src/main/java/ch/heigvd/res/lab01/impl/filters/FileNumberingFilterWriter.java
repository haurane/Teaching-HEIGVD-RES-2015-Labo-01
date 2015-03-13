package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int line = 0;
  private int lastChar = '\0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String tmp = str.substring(off, off+len);
    String[] toWrite = Utils.getNextLine(tmp);
    
    if(line == 0)
        out.write((++line) + "\t");
    
    while(!toWrite[0].isEmpty()){
        out.write(toWrite[0] + (++line) + "\t");
        toWrite = Utils.getNextLine(toWrite[1]);
    }
    out.write( toWrite[1]);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      if (line == 0){
          out.write(Integer.toString(++line) + '\t');
      }
      
      if(lastChar == '\n' || (lastChar == '\r' && c != '\n'))
          out.write(Integer.toString(++line) + '\t');
      
      out.write(c);
      
      lastChar = c;
  }

}
