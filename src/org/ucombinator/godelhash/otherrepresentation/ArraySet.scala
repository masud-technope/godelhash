package org.ucombinator.godelhash.otherrepresentation

import scala.collection.mutable.ArrayBuffer
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

 class ArraySet[A <% PrimeHashable with Ordered[A]] (arrCBuffer: ArrayBuffer[Long]) {
 
  val arrBuffer: ArrayBuffer[Long] = arrCBuffer
  
  def +(n : Long) {
    if(!this.isMember(n))
       arrBuffer += n
  }
  
  def -(n:Long){
    if(this.isMember(n))
    	arrBuffer -= n
  }
  
  // thsi has to test whether duplicated data is input in so it is slow!
  def union(arrS2: ArraySet[A]) { 
     var x = 0
     while(x < arrS2.arrBuffer.size ) {
       val ele = arrS2.arrBuffer(x)
       if(!this.arrBuffer.contains(ele))
           this.arrBuffer += ele
       x += 1
     }
  }
  
  // direct;y using the --= ops
  def diff(arrS2: ArraySet[A]) {
     /*arrS2.arrBuffer.foreach((e) => {
       if(this.isMember(e))//.contains(e))
         arrBuffer -= e
     })*/
     
     this.arrBuffer --= arrS2.arrBuffer
  }
  
   
  def subSetOf(arrS2: ArraySet[A] ) : Boolean= {
   
    
    val res = arrS2.arrBuffer -- this.arrBuffer 
    
    res.isEmpty 
    
  }
  
  def arraySetEqual(arrS2: ArraySet[A] ) : Boolean= {
    this.subSetOf(arrS2) && arrS2.subSetOf(this)
    
     //this == arrS2
  }
  
  def isMember(n: BigInt) : Boolean = { 
    
    arrBuffer.contains(n)
    
    
  }
  
  def intersect(arrS2: ArraySet[A] )  = {
   var x = 0
   var commonElems = new ArrayBuffer[Long](this.arrBuffer.size)
   
     while(x < arrS2.arrBuffer.size ) {
       val ele = arrS2.arrBuffer(x)
       if(!this.arrBuffer.contains(ele))
            commonElems += ele
       x += 1
     }
   
   this.arrBuffer --= this.arrBuffer
   this.arrBuffer ++= commonElems
       
  }
  
 
  
  
}