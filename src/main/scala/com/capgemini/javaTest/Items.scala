package com.capgemini.javaTest

/**
  * Created by Bharathi Pairan on 13/03/2018.
  */
object Items {

    /**
      * Available store items are extended from this class to define the price in integer and the decimal precision
      * For example: price = 60 and precision = 2 would result in .60
      *
      * Decimal precision is stored separately to avoid floating point precision issues in JVM.
      *
      * @param price     price of the item
      * @param precision decimal precision on the price
      */

    sealed abstract class StoreItem(val price: Int, val precision: Int)

    case object Apple extends StoreItem(60, 2)

    case object Orange extends StoreItem(25, 2)

    case object Unknown extends StoreItem(0, 0)

    case class CheckoutItem(storeItem: StoreItem, quantity: Int) {
        def cost: Double = (storeItem.price * quantity) / Math.pow(10, storeItem.precision)
    }

}
