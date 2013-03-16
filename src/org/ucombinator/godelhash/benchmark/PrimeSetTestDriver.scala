package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.godelhash.godelhash.PrimeSet
import scala.collection.immutable.TreeSet

object PrimeSetTestDriver {
  import Benchmark._

  implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt]{
  val primeHash: BigInt = i
  def compare(that: BigInt) = primeHash.toInt  - that.toInt
}

  def main (args : Array[String])  {
    
    val setSize = 100000

    val members = measureTime ("generating primes: ") { Primes.primes.take(setSize).toList }
    val members2 = members.take(setSize/2)

    // println("members: " + members) ;
    //   println("members2: " + members2) ;
    val sortedSet = measureTime ("adding to sorted set: ") {  
      scala.collection.immutable.TreeSet[BigInt]() ++   members }

    val sortedSet2 = { scala.collection.immutable.TreeSet[BigInt]() ++ members }

    val primeSet  =    measureTime ("adding to prime set: ") {  PrimeSet(members: _*) }
    
    val primeSet2  = PrimeSet(members2: _* )  


    val members3 = measureTime ("generating primes: ") { Primes.primes.take(10).toList }
    val members4 = measureTime ("generating primes: ") { Primes.primes.take(3).toList }

     val sortedSet3 = measureTime ("adding to sorted set: ") {  
      scala.collection.immutable.TreeSet[BigInt]() ++   members3 }

    val sortedSet4 = { scala.collection.immutable.TreeSet[BigInt]() ++ members4 }

    val primeSet3  =    measureTime ("adding to prime set: ") {  PrimeSet(members3: _*) }
    
    val primeSet4  = PrimeSet(members4: _* )  
    

   // measureTime ("adding primes to set: ") { primeSet += members }

  
    

    val its2 = 1000000
     ComparePrimeSet.compareInclusion(its2, primeSet3, primeSet4, sortedSet3, sortedSet4)
    
    
      val its = 1

     ///
      
     ComparePrimeSet.compareUnion(its2, primeSet3, primeSet4, sortedSet3, sortedSet4)
  
    ComparePrimeSet.compareIntersect(its2, primeSet3, primeSet4, sortedSet3, sortedSet4)
     
    
    ComparePrimeSet.compareDiff(its2, primeSet3, primeSet4, sortedSet3, sortedSet4)
     
    
     ComparePrimeSet.compareContains(its, primeSet3, primeSet4, sortedSet3, sortedSet4, members3)
     ComparePrimeSet.compareDelete(its, primeSet3, primeSet4, sortedSet3, sortedSet4, members3)
     
    
     
    

     
  }
}