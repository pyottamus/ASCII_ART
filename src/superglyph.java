 

abstract class Superglyph{
  protected abstract String[] getGlyph();
  
  public void print(){
    for(String i : getGlyph()){
      System.out.println(i);
    }
  }



   
}


class Sun extends Superglyph{
  

  private static String[] glyph = {"    ████████    ",
                                   "  ▓██████████▓  ",
                                   " ██████████████ ",
                                   "▄██████████████▄",
                                   "▀██████████████▀",
                                   " ██████████████ ",
                                   "  ▓██████████▓  ",
                                   "    ████████    "
                                  };
  protected String[] getGlyph(){
    return glyph;
  }
}