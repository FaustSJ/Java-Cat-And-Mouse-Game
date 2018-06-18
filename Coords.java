// Represents coordinates in a Matrix-like fashion: r is the row
// index, 0 is at the top, c is the column index, 0 is at the left. 
package thingsim;
public class Coords {
  // These the fields for Coords, don't modify them
  final int r,c;
  
  // Constructor, 
  public Coords(int ir, int ic){
    this.r=ir; 
    this.c=ic;
  }
  
  // Are coordinates equal
  public boolean equals(Coords c){
    if (c==null || !getClass().equals(c.getClass())) return false;
    return this.r==c.r && this.c==c.c;
  }
  
  // City block distance between two coordinates
  public int distance(Coords c){
    return Math.abs(this.r - c.r) + Math.abs(this.c - c.c);
  }
  
  // Convenience to create an array of coordinates from an array of
  // integers
  // Do not modify
  public static Coords[] ofInts(int a[]){
    int n = a.length / 2;
    Coords c[] = new Coords[n];
    for(int i=0; i<a.length; i+=2){
      c[i/2] = new Coords(a[i],a[i+1]);
    }
    return c;
  }
  
  public String toString(){
   return "("+r+","+c+")"; 
  }
}

