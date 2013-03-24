package tests
import org.ucombinator.godelhash.utils.FastParseInPrimesFile
import scala.collection.mutable.ArrayBuffer
import org.ucombinator.godelhash.benchmark.Benchmark
import org.ucombinator.godelhash.utils.Utils

object TestRand extends Utils{
  
 def main(args: Array[String]): Unit = {
   import Benchmark._
   /*val fastPrimesParser =  new FastParseInPrimesFile("primes1billions.txt", 10)
    var primes  = new ArrayBuffer[Long]()
 
     
    measureTime ("new Generating primes: "){
      primes = fastPrimesParser.readNumbers
    }
    
     val members = primes.take(10000).toList
        val len = primes.size
    println("number of generated primes generated is: ", len)
        
         val allTestSets1 = measureTime("generate the set: ") {
       genSetList(1000,  10000 , members, 10000 )  
     }*/
   val arrBuffer = new ArrayBuffer[List[Int]](5000)
   
   measureTime("test n rands ") {
     repeat(500) {
     val res = randWrapper(100000, 50000000) 
     arrBuffer += res
    // println("final length: " + res.length)
     //res.foreach(println)
     arrBuffer.toList
   }
   }
    
 }
}