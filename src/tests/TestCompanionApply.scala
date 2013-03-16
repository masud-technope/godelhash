package tests
import scala.collection.immutable.TreeSet
import scala.collection.immutable.SortedSet
 
 

object TestCompanionApply {
  
 
  
 implicit def BigIntintToPH(i: BigInt) = new PH with Ordered[BigInt]{
  val pH: BigInt = i
  def compare(that: BigInt) = pH.toInt  - that.toInt
}

  val members: List[BigInt] = List(2,3,5,7)
  
  /**
   *  error for the next line:
    type mismatch;  found   : <:<[Nothing,Nothing]  required: A => tests.PrimeHashable with Ordered[A]	

   */
  val ps1 = PS[BigInt]()
  
  
  /**
   * The two errors for the next line: 
   * 
     No implicit view available from Int => tests.PrimeHashable with Ordered[Int].
    
     not enough arguments for method apply: (implicit evidence$3: Int => tests.PrimeHashable with Ordered[Int])tests.PS[Int] in object PS. 
    Unspecified value parameter evidence$3. 
	 
   */
  val ps = PS( members: _*)
  
}

 class PS[A <% PH  ] 
(val comp : BigInt, val members : SortedSet[A]) {
  def + (a : A) : PS[A] = {
    val h = a.pH
    if (comp % h == 0) {
      this
    } else {
      new PS[A](comp * h, members + a)
    }
  }
 
 final def modop(a : BigInt, b : BigInt) = a % b
   def contains(h : PH) = (modop(comp, h.pH)) == 0
  override def hashCode() : Int = comp.hashCode()
  override def equals (o : Any) = o match {
    case a : PS[_] => a.comp == comp
  }

  override def toString = members.toString
}

trait PH {
  def pH : BigInt  
}


object PS {
  def apply[A <% PH with Ordered[A]] () =
    new PS(1, TreeSet[A]())
  
  def apply[A <% PH with Ordered[A]] (vals : A*) = 
    new PS[A](vals.foldLeft (BigInt(1)) ((ans,v) => ans * v.pH),TreeSet[A]() ++ vals.toList)
}

