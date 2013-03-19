package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.utils.Utils
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes

object ManySetTest extends Utils {

  import Benchmark._

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
    val primeHash = bi
  })
  
  
  def main(args: Array[String]): Unit = {
       val setSize = 100000

    // element space
     val members = measureTime ("generating primes: ") { Primes.primes.take(setSize).toList }
       
       println("member Length: ", members.length)
     val allTestSets1 = genSetList(2,  setSize, members, 10000 )  
     
      val allTestSets2 = favorListSet(allTestSets1, 1)//genSetList(15,  setSize, members, 10000 ) //favorListSet(allTestSets1, 15) //genSetList(4,  setSize, members, 1000 )   
      
      println("set pairs gen finished")
      
      val testSetpairs = allTestSets2.zip(allTestSets1)
      
      //testSetpairs.foreach(println)
      println(testSetpairs.length)
      
      val allGodelRepresent1 = allTestSets1.map(_.comp)
      println("space usage for all prime set ")
      println(approximateSizeOfObjectS(allGodelRepresent1) /10000 )
      
      val allSortedRepresent1 = allTestSets1.map(_.members.toList)
      println("space usage for all sorted set ")
      println(approximateSizeOfObjectS(allSortedRepresent1) /10000 )
      
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

}