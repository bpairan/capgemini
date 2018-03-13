package com.capgemini.javaTest

import com.capgemini.javaTest.Items._

/**
  * Created by Bharathi Pairan on 13/03/2018.
  */
object CheckoutTill {


    /**
      * Assumes the input to be comma(,) separated string, which is converted to the equivalent case object to find the price
      * All StoreItem are then grouped based on decimal precision, summed and final value is divided by 10 pow precision value to obtain the actual cost
      * this guarantees floating point precision is avoided
      *
      * @param items comma separated items in available in the store
      * @return total cost of all items in the input, unknown items are ignored
      */
    def totalCost(items: String): Double = {
        Option(items).getOrElse("").split(",")
            .map(toStoreItem)
            .groupBy(storeItem => storeItem.precision)
            .mapValues(storeItems => storeItems.map(_.price).sum)
            .map { case (precision, priceSum) => priceSum / Math.pow(10, precision) }
            .sum
    }

    /**
      * Maps the string to one of the StoreItem
      * @param item item as string
      * @return sub type of StoreItem
      */
    def toStoreItem(item: String): StoreItem = item.trim match {
        case "Apple" => Apple
        case "Orange" => Orange
        case _ => Unknown
    }

}
