package org.ucombinator.datastructure.benchmark
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.godelhash.mathmatics.numbertheory.Primes
import org.ucombinator.datastructure.godelhash.MutablePrimeSet
import org.ucombinator.datastructure.utils.Utils
import org.ucombinator.datastructure.utils.JavaMutableSortedSet

//import org.ucombinator.datastructure.utils.BigInt

object PrimeBattleTestDriver extends Utils {

  import Benchmark._

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
    val primeHash = bi
  })

  def main (args : Array[String])  {
    
    val setSize = 100000

    val members = measureTime ("generating primes: ") { Primes.primes.take(setSize).toList }
    val members2 = members.take(setSize/2)

    
    val memberss1 = take_rands(5, setSize, members.map(_.toLong)) 
    val memberss2 = memberss1.take(2)
    
 //   val sortedSet = measureTime ("adding to sorted set: ") {  
//      scala.collection.immutable.TreeSet[BigInt]() ++   memberss1 }
    //import org.ucombinator.datastructure.utils.BigInt
    val sortedSet2 = { scala.collection.immutable.SortedSet[BigInt]()  ++ memberss2 }

    import java.util.TreeSet
     val treeSet1 =  new TreeSet[String]()
       measureTime ("adding to sorted mutable set: ") {  
      
         memberss1.foreach((m) => treeSet1.add(m.toString))
       }
     val treeSet2  =  new TreeSet[String]()
       measureTime ("adding to sorted mutable set: ") {  
         memberss2.foreach((m) => treeSet1.add(m.toString))
       }
    
    val primeSet = new MutablePrimeSet 

    val primeSet2 = new MutablePrimeSet
    
    primeSet2 += (memberss2)

    measureTime ("adding primes to set: ") { primeSet += memberss1 }

    val its = 1

     val its2 = 10000000
    measureTime ("sortedSet.contains: ") {
      repeat (its2) {
        for (m <- memberss1) {
          treeSet1.contains(m.toString)
        }
      }
    }

    measureTime ("primeSet.contains: ") {
      repeat (its2) {
        for (m <- memberss1) {
          primeSet.contains(m)
        }
      }
    } 
    
    measureTime ("sortedSet.deletes: ") {
      repeat (its2) {
        for (m <- memberss1) {
          treeSet1.remove(m.toString)
        }
      }
    }

    measureTime ("primeSet.delete: ") {
      repeat (its2) {
        for (m <- memberss1) {
          primeSet -= m
        }
      }
    } 

   
    measureTime ("sorted, set2 <= set1: ") { 
      repeat(its2){
        JavaMutableSortedSet.isSubset(treeSet2, treeSet1)
       }
    }
    
    
    measureTime ("prime, set2 <= set1: ") { 
      repeat(its2){
        primeSet2 <= primeSet } 
      }
    
    
    measureTime ("sorted, set2 intersect set1: ") { 
      repeat(its2){
      JavaMutableSortedSet.intersection(treeSet2, treeSet1)  }
    }
    
    
    measureTime ("prime, set2 intersect set1: ") { 
      repeat(its2){
        primeSet2 intersect primeSet  } 
      }
    
     measureTime ("sorted, set2 diff set1: ") { 
      repeat(its2){
      JavaMutableSortedSet.difference(treeSet2, treeSet1) }
    }
    
    
    measureTime ("prime, set2 diff set1: ") { 
      repeat(its2){
        primeSet2 diff primeSet  } 
      }
    
      
     measureTime ("sorted, set2 union set1: ") { 
      repeat(its2){
      JavaMutableSortedSet.union(treeSet2, treeSet1)}
    } 
     
    measureTime ("prime, set2 union set1: ") { 
      repeat(its2){
        primeSet2 union primeSet  } 
      } 
    
  }
}