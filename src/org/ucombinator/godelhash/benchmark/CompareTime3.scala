package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.utils.Utils
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.godelhash.utils.FastParseInPrimesFile
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList

object CompareTime3 extends Utils{

  import Benchmark._

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
    val primeHash = bi
  })
   
  
  def timeParameters(density: Double, univerSize: Int, primes: ArrayBuffer[Long] ) {
   
              
    val setCardi =     (univerSize * density).toInt
    println("setCardi: ", setCardi)
    
    val numberofSets = 1000 // 1k
    // the key space
    val members = primes.take(univerSize).toList
    
    // test prim set
      val allTestSets1 = genSetList(setCardi,  univerSize , members, numberofSets )  
     
      val allTestSets2 = favorListSet(allTestSets1, setCardi/2)
      
      //gen arry set
       val memberList1 = allTestSets1.map(_.members.toList)
      val arrSet1 = genArraySet(memberList1)
      
      val memberList2 = allTestSets2.map(_.members.toList)
      val arrSet2 = genArraySet(memberList2)
      
      // prime set pairs
      val testSetpairs = allTestSets1.zip(allTestSets2)
      
      // array set pair
      val arrayTestPairs = arrSet1.zip(arrSet2)
      
     //    val (ps, as) = calcSpaceUsageVsArray(arrSet1,allTestSets1 ) 
   //   println("space usage for all prime set ", ps)
   //   
    //  println("space usage for all sorted set ", as)
       
     
      printOutPrimeSetStats(testSetpairs)
     printOutOrderedSetStats(testSetpairs)
     printOutArraySetStats(arrayTestPairs)
    
   
  }
  
  def sizeParameterize3(density: Double, univerSize: Int, primes: ArrayBuffer[Long] ) {
     val setCardi =     (univerSize * density).toInt
    println("setCardi: ", setCardi)
    
    val numberofSets = 1000 // 1k
    // the key space
   // println("total keyspace size: ", primes.length)
    val members = primes.take(univerSize).toList
    
    // test prim set
      val allTestSets1 = genSetList(setCardi,  univerSize , members, numberofSets )  
     
      val allTestSets2 = favorListSet(allTestSets1, setCardi/2)
      
      //gen arry set
       val memberList1 = allTestSets1.map(_.members.toList)
      val arrSet1 = genArraySet(memberList1)
      
      val memberList2 = allTestSets2.map(_.members.toList)
      val arrSet2 = genArraySet(memberList2)
      
      // prime set pairs
      val testSetpairs = allTestSets1.zip(allTestSets2)
      
      // array set pair
      val noBitsPrimeSet = noOfBitsToRepresentGodelHashSet(allTestSets1)
      val boBitsSortSet = noOfBitsToRepresentSortedHashSet(allTestSets1)
      val noBitsArraySet = noOfBitsToRepresentArraySet(arrSet1)
      
      
       println("\nspace usage for the three: \n")
       println("GodelHash:  "+ noBitsPrimeSet)
        println("ArraySet:  "+ noBitsArraySet)
         println("SortedSet:  "+ boBitsSortSet)
     
     
       
      
  }
  
  def main(args: Array[String]): Unit = {
   
    
     val fastPrimesParser =  new FastParseInPrimesFile("primes1billions.txt", 10)
    var primes  = new ArrayBuffer[Long]()
 
    measureTime ("new Generating primes: "){
      primes = fastPrimesParser.readNumbers
    }
    
        val len = primes.size
    println("number of generated primes generated is: ", len)
        
/*    timeParameters(0.1, 100, primes)
     println()
   timeParameters(0.3, 100, primes)
   println()
   timeParameters(0.5, 100, primes)
      println()
   timeParameters(0.75, 100, primes)*/
 
     println("\n------------------ Universe= 100")
    sizeParameterize3(0.1, 100, primes)
     println()
   sizeParameterize3(0.3, 100, primes)
   println()
   sizeParameterize3(0.5, 100, primes)
      println()
   sizeParameterize3(0.75, 100, primes)
   
     println("\n------------------ Universe= 1K")
    sizeParameterize3(0.1, 1000, primes)
     println()
   sizeParameterize3(0.3, 1000, primes)
   println()
   sizeParameterize3(0.5, 1000, primes)
      println()
   sizeParameterize3(0.75, 1000, primes)
   
     println("\n------------------ Universe= 10K")
    sizeParameterize3(0.1, 10000, primes)
     println()
   sizeParameterize3(0.3, 10000, primes)
   println()
   sizeParameterize3(0.5, 10000, primes)
      println()
   sizeParameterize3(0.75, 10000, primes)
   
       println("\n------------------ Universe= 100K")
    sizeParameterize3(0.1, 100000, primes)
     println()
   sizeParameterize3(0.3, 100000, primes)
   println()
   sizeParameterize3(0.5, 100000, primes)
      println()
   sizeParameterize3(0.75, 100000, primes)
   
      println("\n ------------------ Universe= 1M") 
    sizeParameterize3(0.1, 1000000, primes)
     println()
   sizeParameterize3(0.3, 1000000, primes)
   println()
   sizeParameterize3(0.5, 1000000, primes)
      println()
   sizeParameterize3(0.75, 1000000, primes)
   
    println("\n------------------ Universe= 10M")
    sizeParameterize3(0.1, 10000000, primes)
     println()
   sizeParameterize3(0.3, 10000000, primes)
   println()
   sizeParameterize3(0.5, 10000000, primes)
      println()
   sizeParameterize3(0.75, 10000000, primes)
   
   println("\n------------------ Universe= 50M")
       sizeParameterize3(0.1, 50000000, primes)
     println()
   sizeParameterize3(0.3, 50000000, primes)
   println()
   sizeParameterize3(0.5, 50000000, primes)
      println()
   sizeParameterize3(0.75, 50000000, primes)
        
  }
}