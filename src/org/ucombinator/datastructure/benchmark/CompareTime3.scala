package org.ucombinator.datastructure.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.datastructure.utils.Utils
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.datastructure.utils.FastParseInPrimesFile
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList
import scala.collection.immutable.HashSet
//import org.ucombinator.datastructure.utils.BigInt


object CompareTime3 extends Utils{

  import Benchmark._

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
    val primeHash = bi
  })

  def timeParameters(density: Double, univerSize: Int, primes: ArrayBuffer[Long], dsType: String) {

    val setCardi = (univerSize * density).toInt
    println("setCardi: ", setCardi)

    val numberofSets = 
      if(density <= 0.00002) 10000 else 100
      //100 // 100 times
    // the key space
    val members = primes.take(univerSize).toList

    val allTestSets1 = measureTime("generate the test set") {
      genSetList(setCardi, univerSize, members, numberofSets)
    }

    val allTestSets2 = allTestSets1
      // genSetList(setCardi, univerSize, members, numberofSets)
      //allTestSets1//favorListSet(allTestSets1, setCardi/2)

    //gen arry set
    val memberList1 = allTestSets1.map(_.members.toList)
    val arrSet1 = genArraySet(memberList1)

    //f u, they should better not be the same, but it will fixed in the test
    val memberList2 = allTestSets2.map(_.members.toList)
    val arrSet2 =  arrSet1 //genArraySet(memberList2)
 
    println("size: elem1: ", memberList1.length)
    println("size: elem2: ", memberList2.length)
    
    // prime set pairs
    val testSetpairs = allTestSets1.zip(allTestSets2)
    val arrayTestPairs = arrSet1.zip(arrSet2)
    if (dsType == "--primeset") {
      printOutPrimeSetStats(testSetpairs)
      return
    }

    if (dsType == "--hashset") {
      val hashset1 = genHashSet(memberList1)
      val hashset2 = hashset1
      val hashsetPair = hashset1.zip(hashset2)
      printOutHashSetStats(hashsetPair)
      return
    } 
    if(dsType == "--sorted-arrayset"){
       val sortedArray1 = genSortedArraySet(memberList1)
       val sortedArray2 = genSortedArraySet(memberList2)
       val testPairs = sortedArray1.zip(sortedArray2)
      printOutSortedArraySetStats(testPairs)
      return
    }
    
    if(dsType == "--unsorted-arrayset"){
      printOutArraySetStats(arrSet1.zip(arrSet2))
      return
    }
    
    if(dsType == "--sorted-treeset") {
      printOutOrderedSetStats(testSetpairs)
      return 
    }
    //printOutPrimeSetStats
  }
  
  def sizeParameterize3(density: Double, univerSize: Int, primes: ArrayBuffer[Long] ) {
     val setCardi =     (univerSize * density).toInt
    println("setCardi: ", setCardi)   
    
    val numberofSets = 100// 1k
    // the key space
   // println("total keyspace size: ", primes.length)
    val members = primes.take(univerSize).toList
    
    // test prim set
      val allTestSets1 = measureTime("generate the set: ") {
       genSetList(setCardi,  univerSize , members, numberofSets )  
     }
     
   
  
       val  allTestSets2 = measureTime("second set series: ") {allTestSets1}// favorListSet(allTestSets1, setCardi/2)}
      
      //gen arry set
       val memberList1 =  measureTime("memberList1 ") {  allTestSets1.map(_.members.toList)}
      val arrSet1 =  measureTime("gas ") { genArraySet(memberList1)}
      
      val memberList2 = measureTime("map ") {allTestSets2.map(_.members.toList)}
      val arrSet2 =  measureTime("genas2 ") { arrSet1} //genArraySet(memberList2)}
      
      // prime set pairs
      val testSetpairs = measureTime("zip ") { allTestSets1.zip(allTestSets2)}
      
      // array set pair
     // val noBitsPrimeSet = measureTime("size1 ") { noOfBitsToRepresentGodelHashSet(allTestSets1)}
     // val boBitsSortSet = measureTime("size2 ") { noOfBitsToRepresentSortedHashSet(allTestSets1)}
      val noBitsArraySet = measureTime("size3 ") {noOfBitsToRepresentArraySet(arrSet1)}
      
      
       println("\nspace usage for the three: \n")
      // println("GodelHash:  "+ noBitsPrimeSet)
        println("ArraySet:  "+ noBitsArraySet)
       //  println("SortedSet:  "+ boBitsSortSet) 
      
  }
  
  def main(args: Array[String]): Unit = { 
    
     val fastPrimesParser =  new FastParseInPrimesFile("primes1billions.txt", 10)
    var primes  = new ArrayBuffer[Long]()
 
    measureTime ("new Generating primes: "){
      primes = fastPrimesParser.readNumbers
    }
     
    println(args(2))
    println(args(3))
    
    if (args(0) == "--time"){
     if(args(1) == "--primeset"){
       if(args(2) == "0.00003" && args(3) == "100000") {
        println("--- sparse")
         timeParameters(0.00400, 100000, primes, "--primeset")
        // println("0.0005-> 0.0004s")
       // timeParameters(0.00003, 100000, primes, "--primeset")
        return
       }
      if(args(2) == "0.1" && args(3) == "100") {
           println("-----100")    
           timeParameters(0.1, 100, primes, "--primeset")
           println()
           return
      }
      
      if(args(2) == "0.3" && args(3) == "100") {
           println("-----100, 0.3")    
           timeParameters(0.3, 100, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100") {
           println("-----100, 0.5")    
           timeParameters(0.5, 100, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100") {
           println("-----100, 0.75")    
           timeParameters(0.75, 100, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "1000") {
           println("-----1000, 0.1")    
           timeParameters(0.1, 1000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "1000") {
           println("-----1000, 0.3")    
           timeParameters(0.1, 1000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "1000") {
           println("-----1000, 0.5")    
           timeParameters(0.5, 1000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "1000") {
           println("-----1000, 0.75")    
           timeParameters(0.75, 1000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "10000") {
           println("-----10000, 0.1")    
           timeParameters(0.1, 10000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "10000") {
           println("-----10000, 0.3")    
           timeParameters(0.3, 10000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "10000") {
           println("-----10000, 0.5")    
           timeParameters(0.5, 10000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "10000") {
           println("-----10000, 0.75")    
           timeParameters(0.75, 10000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "100000") {
           println("-----100000, 0.1")    
           timeParameters(0.1, 100000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100000") {
           println("-----100000, 0.3")    
           timeParameters(0.3, 100000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100000") {
           println("-----100000")    
           timeParameters(0.5, 100000, primes, "--primeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100000" ) {
           println("-----100000, 0.75")    
           timeParameters(0.75, 100000, primes, "--primeset")
           println()
      }
     }
     else if(args(1) == "--unsorted-arrayset"){
       if(args(2) == "0.00003" && args(3) == "100000") {
        println("--- sparse")
        timeParameters(0.00400, 100000, primes, "--unsorted-arrayset")
        return
       }
       if(args(2) == "0.1" && args(3) == "100") {
           println("-----100")    
           timeParameters(0.1, 100, primes, "--unsorted-arrayset")
           println()
           return
      }
      
      if(args(2) == "0.3" && args(3) == "100") {
           println("-----100, 0.3")    
           timeParameters(0.3, 100, primes, "--unsorted-arrayset")
           println()
           return
      }
      
      if(args(2) == "0.5" && args(3) == "100") {
           println("-----100, 0.5")    
           timeParameters(0.5, 100, primes, "--unsorted-arrayset")
           println()
           return
      }
      
      if(args(2) == "0.75" && args(3) == "100") {
           println("-----100, 0.75")    
           timeParameters(0.75, 100, primes, "--unsorted-arrayset")
           println()
           return
      }
      
      if(args(2) == "0.1" && args(3) == "1000") {
           println("-----1000, 0.1")    
           timeParameters(0.1, 1000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "1000") {
           println("-----1000, 0.3")    
           timeParameters(0.1, 1000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "1000") {
           println("-----1000, 0.5")    
           timeParameters(0.5, 1000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "1000") {
           println("-----1000, 0.75")    
           timeParameters(0.75, 1000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "10000") {
           println("-----10000, 0.1")    
           timeParameters(0.1, 10000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "10000") {
           println("-----10000, 0.3")    
           timeParameters(0.3, 10000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "10000") {
           println("-----10000, 0.5")    
           timeParameters(0.5, 10000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "10000") {
           println("-----10000, 0.75")    
           timeParameters(0.75, 10000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "100000") {
           println("-----100000, 0.1")    
           timeParameters(0.1, 100000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100000") {
           println("-----100000, 0.3")    
           timeParameters(0.3, 100000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100000") {
           println("-----100000")    
           timeParameters(0.5, 100000, primes, "--unsorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100000" ) {
           println("-----100000, 0.75")    
           timeParameters(0.75, 100000, primes, "--unsorted-arrayset")
           println()
      }
     }
     else if(args(1) == "--sorted-arrayset"){
        if(args(2) == "0.00003" && args(3) == "100000") {
        println("--- sparse")
        timeParameters(0.004000, 100000, primes, "--sorted-arrayset")
        return
       }
       if(args(2) == "0.1" && args(3) == "100") {
           println("-----100")    
           timeParameters(0.1, 100, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100") {
           println("-----100, 0.3")    
           timeParameters(0.3, 100, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100") {
           println("-----100, 0.5")    
           timeParameters(0.5, 100, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100") {
           println("-----100, 0.75")    
           timeParameters(0.75, 100, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "1000") {
           println("-----1000, 0.1")    
           timeParameters(0.1, 1000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "1000") {
           println("-----1000, 0.3")    
           timeParameters(0.1, 1000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "1000") {
           println("-----1000, 0.5")    
           timeParameters(0.5, 1000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "1000") {
           println("-----1000, 0.75")    
           timeParameters(0.75, 1000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "10000") {
           println("-----10000, 0.1")    
           timeParameters(0.1, 10000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "10000") {
           println("-----10000, 0.3")    
           timeParameters(0.3, 10000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "10000") {
           println("-----10000, 0.5")    
           timeParameters(0.5, 10000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "10000") {
           println("-----10000, 0.75")    
           timeParameters(0.75, 10000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "100000") {
           println("-----100000, 0.1")    
           timeParameters(0.1, 100000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100000") {
           println("-----100000, 0.3")    
           timeParameters(0.3, 100000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100000") {
           println("-----100000")    
           timeParameters(0.5, 100000, primes, "--sorted-arrayset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100000" ) {
           println("-----100000, 0.75")    
           timeParameters(0.75, 100000, primes, "--sorted-arrayset")
           println()
           return
      }
     }
     else  if(args(1) == "--sorted-treeset"){ 
        if(args(2) == "0.00003" && args(3) == "100000") {
        println("--- sparse")
        timeParameters(0.00400, 100000, primes, "--sorted-treeset")
        return
       }
       
      if(args(2) == "0.1" && args(3) == "100") {
           println("-----100")    
           timeParameters(0.1, 100, primes, "--sorted-treeset")
           println()
           return
      }
      
      if(args(2) == "0.3" && args(3) == "100") {
           println("-----100, 0.3")    
           timeParameters(0.3, 100, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100") {
           println("-----100, 0.5")    
           timeParameters(0.5, 100, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100") {
           println("-----100, 0.75")    
           timeParameters(0.75, 100, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "1000") {
           println("-----1000, 0.1")    
           timeParameters(0.1, 1000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "1000") {
           println("-----1000, 0.3")    
           timeParameters(0.1, 1000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "1000") {
           println("-----1000, 0.5")    
           timeParameters(0.5, 1000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "1000") {
           println("-----1000, 0.75")    
           timeParameters(0.75, 1000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "10000") {
           println("-----10000, 0.1")    
           timeParameters(0.1, 10000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "10000") {
           println("-----10000, 0.3")    
           timeParameters(0.3, 10000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "10000") {
           println("-----10000, 0.5")    
           timeParameters(0.5, 10000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "10000") {
           println("-----10000, 0.75")    
           timeParameters(0.75, 10000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "100000") {
           println("-----100000, 0.1")    
           timeParameters(0.1, 100000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100000") {
           println("-----100000, 0.3")    
           timeParameters(0.3, 100000, primes, "--sorted-treeset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100000") {
           println("-----100000")    
           timeParameters(0.5, 100000, primes, "--sorted-treeset")
           println()
           return
      }
      
      if(args(2) == "0.75" && args(3) == "100000" ) {
           println("-----100000, 0.75")    
           timeParameters(0.75, 100000, primes, "--sorted-treeset")
           println()
           return
      }
     }
     else  if(args(1) == "--hashset"){
        if(args(2) == "0.00003" && args(3) == "100000") {
        println("--- sparse")
        timeParameters(0.00400, 100000, primes, "--hashset")
        return
       }
      if(args(2) == "0.1" && args(3) == "100") {
           println("-----100")    
           timeParameters(0.1, 100, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100") {
           println("-----100, 0.3")    
           timeParameters(0.3, 100, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100") {
           println("-----100, 0.5")    
           timeParameters(0.5, 100, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100") {
           println("-----100, 0.75")    
           timeParameters(0.75, 100, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "1000") {
           println("-----1000, 0.1")    
           timeParameters(0.1, 1000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "1000") {
           println("-----1000, 0.3")    
           timeParameters(0.1, 1000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "1000") {
           println("-----1000, 0.5")    
           timeParameters(0.5, 1000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "1000") {
           println("-----1000, 0.75")    
           timeParameters(0.75, 1000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "10000") {
           println("-----10000, 0.1")    
           timeParameters(0.1, 10000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "10000") {
           println("-----10000, 0.3")    
           timeParameters(0.3, 10000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "10000") {
           println("-----10000, 0.5")    
           timeParameters(0.5, 10000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "10000") {
           println("-----10000, 0.75")    
           timeParameters(0.75, 10000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.1" && args(3) == "100000") {
           println("-----100000, 0.1")    
           timeParameters(0.1, 100000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.3" && args(3) == "100000") {
           println("-----100000, 0.3")    
           timeParameters(0.3, 100000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.5" && args(3) == "100000") {
           println("-----100000")    
           timeParameters(0.5, 100000, primes, "--hashset")
           println()
      }
      
      if(args(2) == "0.75" && args(3) == "100000" ) {
           println("-----100000, 0.75")    
           timeParameters(0.75, 100000, primes, "--hashset")
           println()
      }
     }
      
      
    }
        val len = primes.size
  // println("number of generated primes generated is: ", len)
     //println("-----100")    
  //  timeParameters(0.1, 100, primes)
     println()
  // timeParameters(0.3, 100, primes)
   println()
  // timeParameters(0.5, 100, primes)
      println()
   //timeParameters(0.75, 100, primes)
   
   // println("-----1000")    
//   timeParameters(0.1, 1000, primes)
//     println()
//   timeParameters(0.3, 1000, primes)
//   println()
//  timeParameters(0.5, 1000, primes)
//      println()
//  timeParameters(0.75, 1000, primes)
   
    // println("-----10000")    
  // timeParameters(0.1, 10000, primes)
     println()
  // timeParameters(0.3, 10000, primes)
   println()
  //timeParameters(0.5, 10000, primes)
      println()
  // timeParameters(0.75, 10000, primes)
   
  
    //  println("-----100000") 
  // timeParameters(0.1, 100000, primes, "--primeset")
     println()
  //timeParameters(0.3, 100000, primes)
   println()
   //timeParameters(0.5, 100000, primes)
      println()
  // timeParameters(0.75, 100000, primes)
 
      
      
      
      
      
 /*        println("\n------------------ Universe= 10")
    sizeParameterize3(0.1, 10, primes)
     println()
   sizeParameterize3(0.3, 10, primes)
   println()
   sizeParameterize3(0.5, 10, primes)
      println()
   sizeParameterize3(0.75, 10, primes)
        
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
   sizeParameterize3(0.75, 1000, primes) */
   
    /* println("\n------------------ Universe= 10K")
        measureTime("sp1:") {
           sizeParameterize3(0.1, 10000, primes)
     println()
        }
        measureTime("sp2:") {
           sizeParameterize3(0.3, 10000, primes)
   println()
        }
        measureTime("sp3:") {
           sizeParameterize3(0.5, 10000, primes)
      println()
        }*/
      /*  measureTime("sp4:") {
          sizeParameterize3(0.85, 10000, primes) 
        }*/
   
  
  
   
   /*
       println("\n------------------ Universe= 100K")
    sizeParameterize3(0.1, 100000, primes)
     println()
  sizeParameterize3(0.3, 100000, primes)
   println()
  sizeParameterize3(0.5, 100000, primes)
      println()
   sizeParameterize3(0.75, 100000, primes)*/
   
   /*   println("\n ------------------ Universe= 1M") 
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
   
  /* println("\n------------------ Universe= 50M")*/
       sizeParameterize3(0.1, 50000000, primes)
     println()
   sizeParameterize3(0.3, 50000000, primes)
   println()
   sizeParameterize3(0.5, 50000000, primes)
      println()
   sizeParameterize3(0.75, 50000000, primes)*/
        
  }
}