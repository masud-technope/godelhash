package org.ucombinator.godelhash.benchmark
import org.ucombinator.godelhash.godelhash.PrimeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import scala.collection.immutable.SortedSet

object ComparePrimeSet {
 import Benchmark._

  implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt]{
  val primeHash: BigInt = i
  def compare(that: BigInt) = primeHash.toInt  - that.toInt
 }
 
  def compareInclusion(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt]) {
    
    measureTime ("sorted, set2 <= set1: ") { 
       repeat (n){
      sortedSet2 subsetOf sortedSet }
    }
    measureTime ("prime, set2 <= set1: ") { 
      repeat (n){
      primeSet2  isSubsetOf primeSet  
      }
      } 
  }
  
  def compareDiff(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt]) {
    measureTime ("sorted, set1 differ set2 : ") { 
       repeat (n){
        sortedSet diff sortedSet2}
    }
    measureTime ("prime  set1 differ  set2" )  { 
      repeat (n){
       primeSet diff  primeSet2
      }
      } 
  }
  
  def compareUnion(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt]) {
    
     measureTime ("sorted, set2 union set1: ") { 
       repeat (n){
      sortedSet2 union sortedSet }
    }
    measureTime ("prime, set2 union  set1: ") { 
      repeat (n){
      primeSet2  union primeSet  
      }
      } 
  }
  
  def compareIntersect(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt]) {
      measureTime ("sorted, set2 intersect set1: ") { 
       repeat (n){
      sortedSet2 intersect sortedSet }
    }
    measureTime ("prime, set2 intersect  set1: ") { 
      repeat (n){
      primeSet2  intersect primeSet  
      }
      } 
    
  }
  
  def compareContains(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt], members: List[BigInt]) {
     measureTime ("sortedSet.contains: ") {
      repeat (n) {
        for (m <- members) {
          sortedSet.contains(m)
        }
      }
    }

    measureTime ("primeSet.contains: ") {
      repeat (n) {
        for (m <- members) {
          primeSet.contains(m)
        }
      }
    }
  }
  
  def compareDelete(n : Int, primeSet: PrimeSet[BigInt], primeSet2: PrimeSet[BigInt], sortedSet: SortedSet[BigInt], sortedSet2: SortedSet[BigInt],members: List[BigInt]) {
     measureTime ("sortedSet.delete: ") {
      repeat (n) {
        for (m <- members) {
          sortedSet - m
        }
      }
    }

    measureTime ("primeSet.delete: ") {
      repeat (n) {
        for (m <- members) {
          primeSet - m
        }
      }
    }
    
  }
  
  
  
  
  
  
}