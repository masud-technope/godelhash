package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.godelhash.godelhash.PrimeSet
import scala.collection.immutable.TreeSet
import org.ucombinator.godelhash.utils.Utils

object PrimeSetTestDriver extends Utils{
  import Benchmark._

 /* implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt]{
  val primeHash: BigInt = i
  def compare(that: BigInt) = primeHash.toInt  - that.toInt
}*/

  def main (args : Array[String])  {
    
    val setSize = 100000

    // element space
   val members = measureTime ("generating primes: ") { Primes.primes.take(setSize).toList }
  

    val ranmembers = take_rands(10, setSize, members)
      val sortedSet = measureTime ("adding to sorted set: ") {  
     scala.collection.immutable.TreeSet[BigInt]() ++   ranmembers }
    
    val primeSet  =    measureTime ("adding to prime set: ") { 
      
    	PrimeSet(ranmembers: _*) 
    	} 
    
    
    
     val ranmembers2 = take_rands(5, setSize, members) //ranmembers.take(4)
       val sortedSet2 =  
     scala.collection.immutable.TreeSet[BigInt]() ++   ranmembers2 
    val primeSet2  = {
      
      PrimeSet(ranmembers2: _* )  
    }

    

    val its2 = 10000000
     ComparePrimeSet.compareInclusion(its2, primeSet, primeSet2, sortedSet, sortedSet2)
    
    
      val its = 1

     /// 
     ComparePrimeSet.compareUnion(its2, primeSet, primeSet2, sortedSet, sortedSet2)
  
    ComparePrimeSet.compareIntersect(its2, primeSet, primeSet2, sortedSet, sortedSet2)
     
    
    ComparePrimeSet.compareDiff(its2, primeSet, primeSet2, sortedSet, sortedSet2)
     
    
     ComparePrimeSet.compareContains(its, primeSet, primeSet2, sortedSet, sortedSet2, members)
     ComparePrimeSet.compareDelete(its, primeSet, primeSet2, sortedSet, sortedSet2, members) 
     
  }
}