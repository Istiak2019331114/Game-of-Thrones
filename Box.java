public class Box{
   private int height;
   private int width;
   Box(int height, int width)
   {
   	this.height=height;
   	this.width = width;
   }
   void showArea()
   {
   	System.out.println("Area is"+height*width);
   }

}