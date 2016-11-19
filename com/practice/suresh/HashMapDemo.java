package com.practice.suresh;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapDemo{
 
 
public static void main(String args[]){

HashMap<Integer,Float> molasisMap=new HashMap<Integer,Float>();
molasisMap.put(new Integer(10),new Float(7.550f));

Float Volume=(Float)molasisMap.get(10);
System.out.println("Volume="+Volume);
}
}
