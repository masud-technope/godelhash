package org.ucombinator.godelhash.utils
import org.ucombinator.godelhash.godelhash.PrimeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

trait Utils {
implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt]{
  val primeHash: BigInt = i
  def compare(that: BigInt) = primeHash.toInt  - that.toInt
}
  def n_rands(nunmberToGen : Int, upperBound: Int)  = {
	 var r = new scala.util.Random
 	 val res = 1 to nunmberToGen map { _ =>  
 	   var tmp = r.nextInt(upperBound) 
 	   var flag = (tmp == 0)
 	   
 	   while(flag){
 	    // println("flag" + tmp.toString, flag)
 	      tmp =   r.nextInt(upperBound)
 	      flag = (tmp ==0)
 	   } 
 		 
       tmp 
	 } 
	 res.toList
  }
  
  def take_rands(number: Int, upb: Int, primeList: List[BigInt]): List[BigInt] = {
    val ranIndexList = n_rands(number, upb)
   // println("another n rands finished")
    val res: List[BigInt] = ranIndexList.map((index)=> primeList(index))
    
    res
  }
  
  def genSetList( setCardi: Int, upb: Int, primeList: List[BigInt], listSetNo: Int ) : List[PrimeSet[BigInt]] = {
     
    val indexRands = n_rands(listSetNo, setCardi)
    
   // println("n rands finished")
    var res = List[PrimeSet[BigInt]]()
    
    indexRands.map((indexRand) => {
      val members = take_rands(indexRand, upb, primeList) 
      val ps = PrimeSet(members: _*)
      ps 
    }) 
  }
  
  def favorListSet(lsp: List[PrimeSet[BigInt]], cardi: Int) : List[PrimeSet[BigInt]] = {
    lsp.map((ps) => {
      val mms = ps.members.toList.take(cardi)
       PrimeSet(mms: _*)
    })
    
  }
}