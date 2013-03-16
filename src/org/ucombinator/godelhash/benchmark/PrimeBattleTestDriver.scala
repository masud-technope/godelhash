package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.godelhash.godelhash.MutablePrimeSet

object PrimeBattleTestDriver  {

  import Benchmark._

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
    val primeHash = bi
  })

  def main (args : Array[String])  {
    
    val setSize = 100000

    val members = measureTime ("generating primes: ") { Primes.primes.take(setSize).toList }
    val members2 = members.take(setSize/2)

    // println("members: " + members) ;

    val sortedSet = measureTime ("adding to sorted set: ") {  
      scala.collection.immutable.TreeSet[BigInt]() ++   members }

    val sortedSet2 = { scala.collection.immutable.TreeSet[BigInt]() ++ members }

    val primeSet = new MutablePrimeSet 

    val primeSet2 = new MutablePrimeSet
    
    primeSet2 += (members2)

    measureTime ("adding primes to set: ") { primeSet += members }

    val its = 1

    measureTime ("sortedSet.contains: ") {
      repeat (its) {
        for (m <- members) {
          sortedSet.contains(m)
        }
      }
    }

    measureTime ("primeSet.contains: ") {
      repeat (its) {
        for (m <- members) {
          primeSet.contains(m)
        }
      }
    }



    measureTime ("prime, set2 <= set1: ") { 
      repeat(10){
        primeSet2 <= primeSet  } 
      }

    measureTime ("sorted, set2 <= set1: ") { 
      repeat(10){
      sortedSet2 subsetOf sortedSet }
    }
    
  }
}