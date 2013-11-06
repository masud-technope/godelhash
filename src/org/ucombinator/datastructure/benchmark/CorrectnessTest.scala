package org.ucombinator.datastructure.benchmark
import org.ucombinator.datastructure.godelhash.PrimeSet
import scala.collection.immutable.TreeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import org.ucombinator.datastructure.utils.Utils
//import org.ucombinator.datastructure.utils.BigInt

object CorrectnessTest extends Utils {
 implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = (new PrimeHashable {
   
    val primeHash = bi
  })
  def main(args: Array[String]): Unit = {
    
    val lst = List(BigInt(1),BigInt(3),BigInt(5))
    
    val pset = PrimeSet(lst: _*)
    
    println(pset.comp)
    println(pset.contains(11))
    
    println(pset.contains(3))
  }

}