package org.ucombinator.datastructure.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.datastructure.godelhash.PrimeSet
import scala.collection.immutable.TreeSet
import org.ucombinator.datastructure.utils.Utils

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
  

    val ranmembers = take_rands(10, setSize, members.map(_.toLong))
      val sortedSet = measureTime ("adding to sorted set: ") {  
     scala.collection.immutable.TreeSet[Long]() ++   ranmembers }
    
    val primeSet  =    measureTime ("adding to prime set: ") { 
      
    	PrimeSet(ranmembers: _*) 
    	} 
    
    
    
     val ranmembers2 = take_rands(5, setSize, members.map(_.toLong)) //ranmembers.take(4)
       val sortedSet2 =  
     scala.collection.immutable.TreeSet[Long]() ++   ranmembers2 
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
     
    
     ComparePrimeSet.compareContains(its, primeSet, primeSet2, sortedSet, sortedSet2, members.map(_.toLong))
     ComparePrimeSet.compareDelete(its, primeSet, primeSet2, sortedSet, sortedSet2, members.map(_.toLong)) 
     
  }
}