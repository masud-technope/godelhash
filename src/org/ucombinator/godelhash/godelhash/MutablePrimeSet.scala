package org.ucombinator.godelhash.godelhash
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

class MutablePrimeSet {
  private var setCode : BigInt = 1

  final def modop(a : BigInt, b : BigInt) = a % b // FastBigMath.fastmod(a, b)

  implicit def primeFromBigInt(bi : BigInt) : PrimeHashable = 
	  (new PrimeHashable {
		  val primeHash = bi
	  })


  def contains(h : PrimeHashable) = (modop(setCode, h.primeHash)) == 0

  def <= (set2 : MutablePrimeSet) : Boolean = 
    modop(setCode, set2.setCode) == 0

  def += (h : PrimeHashable) {
    if (modop(setCode, h.primeHash) != 0)
      setCode *= h.primeHash 
  }

  def +=[A <% PrimeHashable] (ps : Iterable[A]) {
    var sub : List[BigInt] = List(1)
    var sub2 : List[BigInt] = List()

    var even = true 

    var cur : BigInt = 1

    for (p <- ps) {
      if (even) {
        cur = p.primeHash
      } else {
        cur *= p.primeHash
        sub = cur :: sub
      }
      even = !even
    }
    
    if (!even) {
      sub = cur :: sub
    }

    while (sub.length > 1) {
      // println("sub.length: " + sub.length)
      even = true
      for (n <- sub) {
        if (even) {
          cur = n
        } else {
          cur *= n
          sub2 = cur :: sub2
        }
        even = !even
      }
      if (!even) {
        sub2 = cur :: sub2
      }
      sub = sub2
      sub2 = List()
    }

    setCode *= sub.head
  }
}