package org.ucombinator.godelhash.otherrepresentation

import scala.collection.mutable.ArrayBuffer
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

 class ArraySet[A <% PrimeHashable with Ordered[A]] (arrCBuffer: ArrayBuffer[BigInt]) {
 
  val arrBuffer: ArrayBuffer[BigInt] = arrCBuffer
  
  def +(n : BigInt) {
    if(!this.isMember(n))
       arrBuffer += n
  }
  
  def -(n:BigInt){
    if(this.isMember(n))
    	arrBuffer -= n
  }
  
  def union(arrS2: ArraySet[A]) {
     arrS2.arrBuffer.foreach((e) => {
       if(! this.isMember(e))//arrBuffer.contains(e))
         arrBuffer += e
     })
  }
  
  def diff(arrS2: ArraySet[A]) {
     arrS2.arrBuffer.foreach((e) => {
       if(this.isMember(e))//.contains(e))
         arrBuffer -= e
     })
  }
  
   
  def subSetOf(arrS2: ArraySet[A] ) : Boolean= {
    var res = false
    
   val in = 
     arrS2.arrBuffer.filter((arrElem)=>{
      arrS2.isMember(arrElem)
    })
    
    in.length == arrS2.arrBuffer.length
  }
  
  def arraySetEqual(arrS2: ArraySet[A] ) : Boolean= {
    this.subSetOf(arrS2) && arrS2.subSetOf(this)
  }
  
  def isMember(n: BigInt) : Boolean = {
    /*
    val lst = arrBuffer.filter((ae) => {
      ae == n
    })
      lst.length  > 0*/
    
   /* var res = false
    arrBuffer.foreach((arr) => {
      if(arr == n)
        res = true
    })
    res*/
  
    
    arrBuffer.contains(n)
    
    
  }
  
  def intersect(arrS2: ArraySet[A] )  = {
    arrS2.arrBuffer.foreach((e) => {
       if(!arrBuffer.contains(e))
         arrBuffer += e
     })
  }
  
 
  
  
}