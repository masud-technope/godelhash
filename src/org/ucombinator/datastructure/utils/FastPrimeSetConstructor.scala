package org.ucombinator.datastructure.utils
import org.ucombinator.datastructure.godelhash.PrimeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

 class  PrimeSetConstructorThread( members: List[Long])  extends Thread{

  implicit def LongintToPH(i: Long) = new PrimeHashable with Ordered[Long]{
  val primeHash: BigInt = BigInt(i)
   def compare(that: Long) = primeHash.toInt  - that.toInt
  }
  var ps: PrimeSet[Long] = null
  
  override  def  run() { 
    	ps = PrimeSet(members: _*)
    	 
   }
}
