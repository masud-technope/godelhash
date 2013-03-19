package org.ucombinator.godelhash.benchmark
import scala.tools.nsc.io.File
import org.ucombinator.godelhash.utils.Utils

object SuperTest extends Utils{

   import Benchmark._
    var primes : List[Long]= List[Long]()
   
  def parseInPrimes   {
  val totalPrimes = "primes1billions.txt"
    var primeLines : List[String] = List[String]()
    measureTime("load file") {
    primeLines =  File(totalPrimes).lines.toList.filter(_ != "")
  }
   println("lines")
  
   measureTime(" toLong tye") {
     //val deduplicatePrimeLines = primeLines.toSet.toList
     primes  = primeLines.map(_.toLong)
   } 
  }
   
    //total num = 50,847,534 // number of primes under 1 billion
   
   
   val OneHundredK= 100000 // 100K
   val OneM  = 1000000 // 1M = 2^20
   val TenM  = 10000000 //10M = 2^23
   val Fifty  = 50000000 // 50M  = 2^28
   
   val SetCardi2 = 2
   val SetCardi4 = 4
   
   val SetCardi10 = 10
   val SetCardi5 = 5
   val SetCardi15 = 15
   val SetCardi20 = 20
   val SetCardi30 = 30
   val SetCardi40 = 40
   
   val SetCardi50 = 50
   
   val SetCardi100 = 100
   val SetCardi1K = 1000
   
   val SetCardi50K = 50000
   val SetCardi1M = OneM
   
    
   
  
   def testVariousUS(universe: Int, setSuperCardi: Int, subSetCardi: Int, numbersOfSetPairs: Int) {
     
     def primeNumbers = primes.take(universe).map(_.asInstanceOf[BigInt])
     
      val allTestSets1 = genSetList(setSuperCardi,  universe, primeNumbers, numbersOfSetPairs )  
     
      val allTestSets2 = favorListSet(allTestSets1, subSetCardi)//genSetList(15,  setSize, members, 10000 ) //favorListSet(allTestSets1, 15) //genSetList(4,  setSize, members, 1000 )   
      
      println("gen set pairs finished")
      
      val testSetpairs = allTestSets2.zip(allTestSets1)
      
      testSetpairs.foreach(println)
      
      measureTime ("------------ sorted  <=   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members subsetOf primeSet1.members
      }
     }
     
      measureTime ("------------ prime  <=   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 isSubsetOf primeSet1
      }
     }
      
       measureTime ("------------ sorted  union   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members union primeSet1.members
      }
     }
     
      measureTime ("------------ prime  union   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 union primeSet1
      }
     }
      
       measureTime ("------------ sorted  diff   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members diff primeSet1.members
      }
     }
     
      measureTime ("------------ prime  diff  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 diff primeSet1
      }
     }
      
       measureTime ("------------ sorted  intersect  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members intersect primeSet1.members
      }
     }
     
      measureTime ("------------ prime  intersect   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 intersect primeSet1
      }
     }
      
       measureTime ("------------ sorted  contains  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
        
         primeSet2.members contains primeSet1.members.head
      }
     }
     
      measureTime ("------------ prime  contains  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 contains primeSet1.members.head
      }
     }
      
       measureTime ("------------ sorted  delete   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members - primeSet1.members.head
      }
     }
     
      measureTime ("------------ prime  delete   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 - primeSet1.members.head
      }
     }
      
        measureTime ("------------ sorted  insert   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members + primeSet1.members.head
      }
     }
     
      measureTime ("------------ prime  insert   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 + primeSet1.members.head
      }
     }
      
        measureTime ("------------ sorted  equality   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members.equals(primeSet1.members) 
      }
     }
     
      measureTime ("------------ prime  equality   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.PSEqual(primeSet1) 
      }
     }
   }
  
   def main (args : Array[String])  {
    
     // sparseSet
     //testVariousUS(: Int, setSuperCardi: Int, subSetCardi: Int, numbersOfSetPairs: Int)
     //denseSet
      
   }
}