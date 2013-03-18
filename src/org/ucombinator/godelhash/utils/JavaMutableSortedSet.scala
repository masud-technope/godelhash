package org.ucombinator.godelhash.utils

import java.util.Set;
import java.util.TreeSet;


object JavaMutableSortedSet {
 def    union(setA: TreeSet[String] , setB: TreeSet[String] ) : TreeSet[String] =  {
   val tmp  = new TreeSet[String](setA) 
    tmp.addAll(setB) 
      tmp 
  }

  def   intersection(setA: TreeSet[String] , setB: TreeSet[String] ) : TreeSet[String] ={
    val tmp = new TreeSet[String](); 
    for ( x <- setA.toArray()) {
      if (setB.contains(x))
        tmp.add(x.asInstanceOf[String]);
    }
     tmp;
  }

  def   difference(setA: TreeSet[String] , setB: TreeSet[String] ) : TreeSet[String] ={
   val tmp = new TreeSet[String](setA);
    tmp.removeAll(setB);
    tmp;
  }
 

  def isSubset(setA: TreeSet[String] , setB: TreeSet[String] ) : Boolean ={
      setB.containsAll(setA);
  }

  
}