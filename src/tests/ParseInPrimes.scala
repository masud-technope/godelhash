package tests
  import tools.nsc.io.File
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.FileInputStream
import java.nio.channels.FileChannel.MapMode._
import java.nio.ByteBuffer
import java.io.RandomAccessFile
import org.ucombinator.datastructure.benchmark.Benchmark
import java.io.ObjectOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream

object ParseInPrimes {
   import  Benchmark._
  def offsetTable : List[Int] = List[Int]()
  
  def buildIndexInfo(buf: ByteBuffer) {
    val  pos = buf.position();
    //Set position to zero
    buf.position(0);
    
    
      //buf.position(pos);
  }
   
   
   
  
  def  showBufferData (
          buf: ByteBuffer ,  name: String){
    //Displays byte buffer contents
    
    //Save position
    val  pos = buf.position();
    //Set position to zero
    buf.position(0);
    System.out.println(
            "Data for " + name);
    while(buf.hasRemaining()){
        val cb = buf.get()
        val text= String.valueOf(Character.toChars(cb)) // change every byte to text
        // 66 53 ->6 53-> 6
      System.out.print(
        text
          
                       + " ");
    }//end while loop
    System.out.println();//new line
    //Restore position and return
    //buf.position(pos);
  } 
   
 
  def main (args : Array[String])  {
  
  val totalPrimes = "primes1billions.txt"
    var primeLines : List[String] = List[String]()
    measureTime("load file") {
    primeLines =  File(totalPrimes).lines.toList.filter(_ != "")
  }
   println("lines")
   var primes : List[Long]= List[Long]()
   measureTime(" toLong tye") {
     //val deduplicatePrimeLines = primeLines.toSet.toList
     primes  = primeLines.map(_.toLong)
   }
   
/*     measureTime(" seri: ") {
   val obs = new ObjectOutputStream(new FileOutputStream("seri.txt"))
   obs.writeObject(primes)
   obs.close()
     }
   
   var primeIn =  new Object()
   
    measureTime(" deseri: ") {
   val ibs = new ObjectInputStream(new FileInputStream("seri.txt"))
   primeIn =  ibs.readObject()
   ibs.close()
  }*/
   
// val newPrimes =  primes.asInstanceOf[List[Long]]
   
   
     println(primes.take(100))
    
    
    /*val source = scala.io.Source.fromFile(totalPrimes)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    
    byteArray.foreach(println)*/
    
   /* val bis = new BufferedInputStream(new FileInputStream(totalPrimes))
    val bArray = Stream.continually(bis.read).takeWhile(-1 !=).map(_.toByte).toArray
    bArray.foreach(println)*/
    
    
/*    val file = new File(totalPrimes)
val fileSize = file.length
println("fileSize " + fileSize)
val stream = new RandomAccessFile(file, "r")
val buffer = stream.getChannel.map(READ_ONLY, 0, fileSize)
 //val charBuffer = buffer.asCharBuffer()
// 0 to 2 foreach((n) => println(buffer.get(n)))
//println(charBuffer.get(2))

val s =  buffer.asCharBuffer()

showBufferData(buffer, "sdf")

println(buffer.get(1))*/
    
}


}