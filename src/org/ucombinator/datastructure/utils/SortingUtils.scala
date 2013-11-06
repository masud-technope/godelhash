package org.ucombinator.datastructure.utils
import scala.collection.mutable.ArrayBuffer

/**
 * basically just some simple helpers for sorted array
 */
object SortingUtils {

  
  
  /** 
   * merge two sorted arrays
   */
  
  def merge(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){
	    println(i, j)
	    if(arr1(i) < arr2(j)) {
	      newArrBuffer += arr1(i)
	      i += 1
	     
	      }
	    else if(arr1(i) > arr2(j)) { 
	      newArrBuffer += arr2 (j) 
	      j+= 1
	     
	    }
	    else if(arr1(i) == arr2(j)){
	      newArrBuffer += arr1(i)
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length) { 
	      var p = j
	      while(p < arr2.length){
	    	  newArrBuffer += arr2(p)
	    	  p += 1 
	      }
	      return newArrBuffer
	    }
	    
	    if(j >= arr2.length) {
	       var p = i
	      while(p < arr1.length){
	    	  newArrBuffer += arr1(p)
	    	  p += 1
	      }
	      return newArrBuffer
	    }
	     
	    }
	    return newArrBuffer
	  }
	  
	
  def main(args: Array[String]): Unit = {}

}